package BUS;

import DAO.LoaiDAO;
import DTO.LoaiDTO;

import java.util.ArrayList;

public class LoaiBUS {
    private final LoaiDAO loaiDAO = new LoaiDAO();
    public ArrayList<LoaiDTO> listLoai = new ArrayList<>();

    public LoaiBUS() {
        listLoai = loaiDAO.selectAll();
    }

    public ArrayList<LoaiDTO> getAll() {
        return this.listLoai;
    }

    public LoaiDTO getByIndex(int index) {
        return this.listLoai.get(index);
    }

    public int getIndexByMaLoai(int maLoai) {
        int i = 0;
        int vitri = -1;
        while (i < this.listLoai.size() && vitri == -1) {
            if (listLoai.get(i).getMaloaisp() == maLoai) vitri = i;
            else i++;
        }
        return vitri;
    }

    public boolean add(LoaiDTO loai) {
        boolean check = loaiDAO.insert(loai) != 0;
        if (check) {
            this.listLoai.add(loai);
        }
        return check;
    }

    public boolean delete(LoaiDTO loai) {
        boolean check = loaiDAO.delete(loai.getMaloaisp()) != 0;
        if (check) {
            this.listLoai.remove(getIndexByMaLoai(loai.getMaloaisp()));
        }
        return check;
    }

    public boolean update(LoaiDTO loai) {
        boolean check = loaiDAO.update(loai) != 0;
        if (check) {
            this.listLoai.set(getIndexByMaLoai(loai.getMaloaisp()), loai);
        }
        return check;
    }

    public ArrayList<LoaiDTO> search(String text, String type) {
        ArrayList<LoaiDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (LoaiDTO loai : this.listLoai) {
                    if (Integer.toString(loai.getMaloaisp()).toLowerCase().contains(text) ||
                        loai.getTenloaisp().toLowerCase().contains(text)) {
                        result.add(loai);
                    }
                }
            }

            case "Mã Loại" -> {
                for (LoaiDTO loai : this.listLoai) {
                    if (Integer.toString(loai.getMaloaisp()).toLowerCase().contains(text)) {
                        result.add(loai);
                    }
                }
            }

            case "Tên Loại" -> {
                for (LoaiDTO loai : this.listLoai) {
                    if (loai.getTenloaisp().toLowerCase().contains(text)) {
                        result.add(loai);
                    }
                }
            }
        }
        return result;
    }

    public String getTenLoai(int maLoai) {
        String name = "";
        for (LoaiDTO loai : this.listLoai) {
            if (loai.getMaloaisp() == maLoai) {
                name = loai.getTenloaisp();
            }
        }
        return name;
    }

    public String[] getArrTenLoai() {
        int size = listLoai.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listLoai.get(i).getTenloaisp();
        }
        return result;
    }

    public LoaiDTO selectLoai(int maLoai) {
        return loaiDAO.selectedByID(maLoai);
    }
}