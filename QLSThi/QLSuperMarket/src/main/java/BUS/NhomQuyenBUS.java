package BUS;

import DAO.ChiTietQuyenDAO;
import DAO.NhomQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.NhomQuyenDTO;

import java.util.ArrayList;

public class NhomQuyenBUS {

    private final NhomQuyenDAO nhomquyenDAO = new NhomQuyenDAO();
    private final ChiTietQuyenDAO chitietquyenDAO = new ChiTietQuyenDAO();
    private final ArrayList<NhomQuyenDTO> listNhomQuyen;

    public NhomQuyenBUS() {
        this.listNhomQuyen = nhomquyenDAO.selectAll();
    }

    public ArrayList<NhomQuyenDTO> getAll() {
        return this.listNhomQuyen;
    }

    public NhomQuyenDTO getByIndex(int index) {
        return this.listNhomQuyen.get(index);
    }

    public boolean add(String tenNhomQuyen, ArrayList<ChiTietQuyenDTO> ctQuyenList) {
        int AUTO_INCREMENT = nhomquyenDAO.getAutoIncrement();
        NhomQuyenDTO nq = new NhomQuyenDTO(tenNhomQuyen);
        int generatedId = nhomquyenDAO.insert(nq);
        if (generatedId != 0) {
            nq.setMaNhomQuyen(AUTO_INCREMENT);
            // Gán lại mã nhóm quyền cho các chi tiết quyền
            for (ChiTietQuyenDTO ct : ctQuyenList) {
                ct.setManhomquyen(AUTO_INCREMENT);
            }
            listNhomQuyen.add(nq);
            return addChiTietQuyen(ctQuyenList);
        }
        return false;
    }

    public boolean update(NhomQuyenDTO nhomquyen, ArrayList<ChiTietQuyenDTO> chiTietList, int index) {
        boolean success = nhomquyenDAO.update(nhomquyen) != 0;
        if (success) {
            // Gán lại mã nhóm quyền cho các chi tiết quyền
            for (ChiTietQuyenDTO ct : chiTietList) {
                ct.setManhomquyen(nhomquyen.getMaNhomQuyen());
            }
            removeChiTietQuyen(nhomquyen.getMaNhomQuyen());
            addChiTietQuyen(chiTietList);
            listNhomQuyen.set(index, nhomquyen);
        }
        return success;
    }

    public boolean delete(NhomQuyenDTO nq) {
        boolean success = nhomquyenDAO.delete((nq.getMaNhomQuyen())) != 0;
        if (success) {
            listNhomQuyen.remove(nq);
        }
        return success;
    }

    public ArrayList<ChiTietQuyenDTO> getChiTietQuyen(int maNhomQuyen) {
        return chitietquyenDAO.selectAll((maNhomQuyen));
    }

    public boolean addChiTietQuyen(ArrayList<ChiTietQuyenDTO> listCT) {
        return chitietquyenDAO.insert(listCT) != 0;
    }

    public boolean removeChiTietQuyen(int maNhomQuyen) {
        return chitietquyenDAO.delete((maNhomQuyen)) != 0;
    }

    public boolean checkPermission(int maNhomQuyen,int maFunc, int maPermission) {
        ArrayList<ChiTietQuyenDTO> ctQuyen = getChiTietQuyen(maNhomQuyen);
        for (ChiTietQuyenDTO ct : ctQuyen) {
            if (ct.getPermissionid() == maPermission && ct.getMachucnang() == maFunc) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<NhomQuyenDTO> search(String text) {
        ArrayList<NhomQuyenDTO> result = new ArrayList<>();
        for (NhomQuyenDTO nq : listNhomQuyen) {
            if (Integer.toString(nq.getMaNhomQuyen()).contains(text) ||
                nq.getTenNhomQuyen().toLowerCase().contains(text.toLowerCase())) {
                result.add(nq);
            }
        }
        return result;
    }
}
