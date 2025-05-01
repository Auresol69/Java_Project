package BUS;

import DAO.NccDAO;
import DTO.NccDTO;

import java.util.ArrayList;

public class NccBUS {
    private final NccDAO nccDAO = new NccDAO();
    public ArrayList<NccDTO> listNcc = new ArrayList<>();

    public NccBUS() {
        listNcc = nccDAO.selectAll();
    }

    public ArrayList<NccDTO> getAll() {
        return this.listNcc;
    }

    public NccDTO getByIndex(int index) {
        return this.listNcc.get(index);
    }

    public int getIndexByMaNcc(int maNcc) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNcc.size() && vitri == -1) {
            if (listNcc.get(i).getMancc() == maNcc) vitri = i;
            else i++;
        }
        return vitri;
    }

    public boolean add(NccDTO ncc) {
        boolean check = nccDAO.insert(ncc) != 0;
        if (check) {
            this.listNcc.add(ncc);
        }
        return check;
    }

    public boolean delete(NccDTO ncc) {
        boolean check = nccDAO.delete(ncc.getMancc()) != 0;
        if (check) {
            this.listNcc.remove(getIndexByMaNcc(ncc.getMancc()));
        }
        return check;
    }

    public boolean update(NccDTO ncc) {
        boolean check = nccDAO.update(ncc) != 0;
        if (check) {
            this.listNcc.set(getIndexByMaNcc(ncc.getMancc()), ncc);
        }
        return check;
    }

    public ArrayList<NccDTO> search(String text, String type) {
        ArrayList<NccDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (NccDTO ncc : this.listNcc) {
                    if (Integer.toString(ncc.getMancc()).toLowerCase().contains(text) ||
                        ncc.getTencc().toLowerCase().contains(text) ||
                        ncc.getDiachi().toLowerCase().contains(text) ||
                        ncc.getDienthoai().toLowerCase().contains(text) ||
                        ncc.getSofax().toLowerCase().contains(text)) {
                        result.add(ncc);
                    }
                }
            }

            case "Mã Nhà Cung Cấp" -> {
                for (NccDTO ncc : this.listNcc) {
                    if (Integer.toString(ncc.getMancc()).toLowerCase().contains(text)) {
                        result.add(ncc);
                    }
                }
            }

            case "Tên Nhà Cung Cấp" -> {
                for (NccDTO ncc : this.listNcc) {
                    if (ncc.getTencc().toLowerCase().contains(text)) {
                        result.add(ncc);
                    }
                }
            }

            case "Địa chỉ" -> {
                for (NccDTO ncc : this.listNcc) {
                    if (ncc.getDiachi().toLowerCase().contains(text)) {
                        result.add(ncc);
                    }
                }
            }

            case "Số điện thoại" -> {
                for (NccDTO ncc : this.listNcc) {
                    if (ncc.getDienthoai().toLowerCase().contains(text)) {
                        result.add(ncc);
                    }
                }
            }

            case "Số fax" -> {
                for (NccDTO ncc : this.listNcc) {
                    if (ncc.getSofax().toLowerCase().contains(text)) {
                        result.add(ncc);
                    }
                }
            }
        }
        return result;
    }

    public String getTenNcc(int maNcc) {
        String name = "";
        for (NccDTO ncc : this.listNcc) {
            if (ncc.getMancc() == maNcc) {
                name = ncc.getTencc();
            }
        }
        return name;
    }

    public String[] getArrTenNcc() {
        int size = listNcc.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNcc.get(i).getTencc();
        }
        return result;
    }

    public NccDTO selectNcc(int maNcc) {
        return nccDAO.selectedByID(maNcc);
    }
}