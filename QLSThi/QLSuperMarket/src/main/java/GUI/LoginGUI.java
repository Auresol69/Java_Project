package GUI;

import javax.swing.*;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI_Component.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LoginGUI extends JFrame implements KeyListener {
    InputForm txtpassword,txtUsername;
    JPanel left, right;

    public LoginGUI() {
        init();
        setTitle("Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (d.getWidth() / 2), (int) (d.getHeight() * 2 / 3));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        txtUsername = new InputForm("Tên đăng nhập");
        txtpassword = new InputForm("Mật Khẩu", "password");

        
    }
    public void checkLogin() throws UnsupportedOperationException{
        String usernameCheck = txtUsername.getText();
        String passwordCheck = txtpassword.getPass();
        if (usernameCheck.equals("") || passwordCheck.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
         } //else {
        //     TaiKhoanDTO tk = TaiKhoanDAO.getInstance().selectByUser(usernameCheck);
        //     if (tk == null) {
        //         JOptionPane.showMessageDialog(this, "Tài khoản của bạn không tồn tại trên hệ thống", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        //     } else {
        //         if (tk.getTrangthai() == 0) {
        //             JOptionPane.showMessageDialog(this, "Tài khoản của bạn đang bị khóa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        //         } else {
        //             if (BCrypt.checkpw(passwordCheck, tk.getMatkhau())) {
        //                 try {
        //                     this.dispose();
        //                     Main main = new Main(tk);
        //                     main.setVisible(true);
        //                 } catch (UnsupportedLookAndFeelException ex) {
        //                     Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
        //                 }
        //             } else {
        //                 JOptionPane.showMessageDialog(this, "Mật khẩu không khớp", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        //             }
        //         }

        //     }
        // }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                checkLogin(); // Gọi hàm kiểm tra đăng nhập
            } catch (UnsupportedLookAndFeelException ex) {
                // Ghi log lỗi với thông tin chi tiết
                Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, "Lỗi giao diện không được hỗ trợ", ex);
            } catch (Exception ex) {
                // Xử lý các ngoại lệ khác nếu có
                Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, "Lỗi không xác định khi đăng nhập", ex);
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
