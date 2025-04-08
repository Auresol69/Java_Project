package BUS;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;

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
        if (tk != null && tk.getPassword().equals(password)) {
            return tk;
        }
        return null;
        }
    }