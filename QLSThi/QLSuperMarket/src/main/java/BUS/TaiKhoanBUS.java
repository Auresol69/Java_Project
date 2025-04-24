package BUS;

import DAO.NhomQuyenDAO;
import DAO.TaiKhoanDAO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;
import helper.BCrypt;
public class TaiKhoanBUS {
    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private ArrayList<TaiKhoanDTO> dsTaiKhoan;

    public TaiKhoanBUS() {
        dsTaiKhoan = taiKhoanDAO.getAllTaiKhoan();
    }

    // Lấy danh sách tài khoản
    public ArrayList<TaiKhoanDTO> getDsTaiKhoan() {
        return dsTaiKhoan;
    }

    public NhomQuyenDTO getNhomQuyenDTO(int manhom) {
        NhomQuyenDAO nhomQuyenDAO = NhomQuyenDAO.getInstance();
        return nhomQuyenDAO.selectedByID(manhom);
    }

    // Tìm tài khoản theo username
    public TaiKhoanDTO getTaiKhoanTheoUsername(String username) {
        for (TaiKhoanDTO tk : dsTaiKhoan) {
            if (tk.getUsername().equalsIgnoreCase(username)) {
                return tk;
            }
        }
        return null;
    }

    // Kiểm tra đăng nhập
    public TaiKhoanDTO dangNhap(String username, String password) {
        TaiKhoanDTO tk = getTaiKhoanTheoUsername(username);
        
        if (tk != null && BCrypt.checkpw(password, tk.getPassword()) ) {
            return tk;
        }
        return null;
    }

    public ArrayList<TaiKhoanDTO> search(String txt, String type) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();

        switch (type) {
            case "Tất cả" -> {
                for (TaiKhoanDTO i : dsTaiKhoan) {
                    String maStaffStr = String.valueOf(i.getMaStaff());
                    if (maStaffStr.contains(txt) || i.getUsername().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Mã nhân viên" -> {
                for (TaiKhoanDTO i : dsTaiKhoan) {
                    String maStaffStr = String.valueOf(i.getMaStaff());
                    if (maStaffStr.contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Username" -> {
                for (TaiKhoanDTO i : dsTaiKhoan) {
                    if (i.getUsername().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

    // Thêm tài khoản
    public boolean addAcc(TaiKhoanDTO tk) {
        int result = taiKhoanDAO.insert(tk);
        if (result > 0) {
            dsTaiKhoan.add(tk);
            return true;
        }
        return false;
    }

    // Cập nhật tài khoản
    public boolean updateTaiKhoan(TaiKhoanDTO tk) {
        int result = taiKhoanDAO.updateTaiKhoan(tk);
        if (result > 0) {
            for (int i = 0; i < dsTaiKhoan.size(); i++) {
                if (dsTaiKhoan.get(i).getMaAccount() == tk.getMaAccount()) {
                    dsTaiKhoan.set(i, tk);
                    break;
                }
            }
            return true;
        }
        return false;
    }
}
