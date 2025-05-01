package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import BUS.LoaiBUS;
import BUS.NccBUS;

import java.util.ArrayList;

public class SanPhamBUS {
    private final SanPhamDAO spDAO = new SanPhamDAO();
    private final LoaiBUS loaiBUS = new LoaiBUS();
    private final NccBUS nccBUS = new NccBUS();
    public ArrayList<SanPhamDTO> listSanPham = new ArrayList<>();

    public SanPhamBUS() {
        listSanPham = spDAO.selectAll();
    }

    public ArrayList<SanPhamDTO> getAll() {
        return this.listSanPham;
    }

    public SanPhamDTO getByIndex(int index) {
        return this.listSanPham.get(index);
    }

    public int getIndexByMaSP(int maSP) {
        int i = 0;
        int vitri = -1;
        while (i < this.listSanPham.size() && vitri == -1) {
            if (listSanPham.get(i).getMasp() == maSP) vitri = i;
            else i++;
        }
        return vitri;
    }

    public boolean add(SanPhamDTO sp) {
        boolean check = spDAO.insert(sp) != 0;
        if (check) {
            this.listSanPham.add(sp);
        }
        return check;
    }

    public boolean delete(SanPhamDTO sp) {
        boolean check = spDAO.delete(sp.getMasp()) != 0;
        if (check) {
            this.listSanPham.remove(getIndexByMaSP(sp.getMasp()));
        }
        return check;
    }

    public boolean update(SanPhamDTO sp) {
        boolean check = spDAO.update(sp) != 0;
        if (check) {
            this.listSanPham.set(getIndexByMaSP(sp.getMasp()), sp);
        }
        return check;
    }

    public ArrayList<SanPhamDTO> search(String text, String type) {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (SanPhamDTO sp : this.listSanPham) {
                    if (Integer.toString(sp.getMasp()).toLowerCase().contains(text) ||
                        sp.getTensp().toLowerCase().contains(text) ||
                        Integer.toString(sp.getSoluong()).toLowerCase().contains(text) ||
                        Integer.toString(sp.getDongiasanpham()).toLowerCase().contains(text) ||
                        loaiBUS.getTenLoai(sp.getMaloaisp()).toLowerCase().contains(text)){
                        
                        result.add(sp);
                    }
                }
            }

            case "Mã Sản Phẩm" -> {
                for (SanPhamDTO sp : this.listSanPham) {
                    if (Integer.toString(sp.getMasp()).toLowerCase().contains(text)) {
                        result.add(sp);
                    }
                }
            }

            case "Tên Sản Phẩm" -> {
                for (SanPhamDTO sp : this.listSanPham) {
                    if (sp.getTensp().toLowerCase().contains(text)) {
                        result.add(sp);
                    }
                }
            }

            case "Loại Sản Phẩm" -> {
                for (SanPhamDTO sp : this.listSanPham) {
                    if (loaiBUS.getTenLoai(sp.getMaloaisp()).toLowerCase().contains(text)) {
                        result.add(sp);
                    }
                }
            }

            
        }
        return result;
    }

    public String getTenLoai(int maloaisp) {
        return loaiBUS.getTenLoai(maloaisp);
    }

    

    public ArrayList<String> getSanPhamDetails() {
        ArrayList<String> details = new ArrayList<>();
        for (SanPhamDTO sp : listSanPham) {
            String detail = "Sản phẩm: " + sp.getTensp() +
                            ", Loại: " + getTenLoai(sp.getMaloaisp()) ;
            details.add(detail);
        }
        return details;
    }
}