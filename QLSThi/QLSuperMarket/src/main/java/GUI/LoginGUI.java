package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import GUI.Component.*;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI.Model.QuenMatKhau;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LoginGUI extends JFrame implements KeyListener {
    InputForm txtpassword,txtUsername;
    JPanel pnlMain, pnlLogIn;
    JLabel lblImage, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7;
    Color FontColor = new Color(96, 125, 139);
    public LoginGUI() {
        init();
    }

    public void init() {
        txtUsername = new InputForm("Tên đăng nhập");
        txtpassword = new InputForm("Mật Khẩu", "password");
        this.setSize(new Dimension(1000, 500));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Đăng nhập");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame jf = this;

        imgIntro();
        pnlMain = new JPanel();
        pnlMain.setBackground(Color.white);
        pnlMain.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlMain.setPreferredSize(new Dimension(500, 740));
        pnlMain.setLayout(new FlowLayout(1, 0, 10));

        lbl3 = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
        lbl3.setFont(new Font(FlatRobotoFont.FAMILY_SEMIBOLD, Font.BOLD, 20));
        pnlMain.add(lbl3);

        JPanel paneldn = new JPanel();
        paneldn.setBackground(Color.BLACK);
        paneldn.setPreferredSize(new Dimension(400, 200));
        paneldn.setLayout(new GridLayout(2, 1));

        paneldn.add(txtUsername);
        paneldn.add(txtpassword);

        // txtUsername.getInputValue().addKeyListener(this);
        // txtpassword.getPass().addKeyListener(this);

        pnlMain.add(paneldn);

        lbl6 = new JLabel("ĐĂNG NHẬP");
        lbl6.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lbl6.setForeground(Color.white);

        pnlLogIn = new JPanel();
        pnlLogIn.putClientProperty( FlatClientProperties.STYLE, "arc: 99" );
        pnlLogIn.setBackground(Color.BLACK);
        pnlLogIn.setPreferredSize(new Dimension(380, 45));
        pnlLogIn.setLayout(new FlowLayout(1, 0, 15));
        pnlLogIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                pnlLogInMouseEntered(evt);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                try {
                    pnlLogInMousePressed(evt);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                pnlLogInMouseExited(evt);
            }
        });
        pnlLogIn.add(lbl6);
        pnlLogIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                pnlLogInMouseEntered(evt);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                try {
                    pnlLogInMousePressed(evt);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                pnlLogInMouseExited(evt);
            }
        });
        lbl7 = new JLabel("Quên mật khẩu", JLabel.RIGHT);
        lbl7.setPreferredSize(new Dimension(380, 50));
        lbl7.setFont(new Font(FlatRobotoFont.FAMILY, Font.ITALIC, 18));
        lbl7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                QuenMatKhau qmk = new QuenMatKhau(jf, true);
                qmk.setVisible(true);
            }
        });
        pnlMain.add(lbl7);

        pnlMain.add(pnlLogIn);

        this.add(pnlMain, BorderLayout.EAST);

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
    private void pnlLogInMousePressed(java.awt.event.MouseEvent evt) throws UnsupportedLookAndFeelException {
        checkLogin();
    }

    private void pnlLogInMouseEntered(java.awt.event.MouseEvent evt) {
        pnlLogIn.setBackground(FontColor);
        pnlLogIn.setForeground(Color.black);
    }

    private void pnlLogInMouseExited(java.awt.event.MouseEvent evt) {
        pnlLogIn.setBackground(Color.BLACK);
        pnlLogIn.setForeground(Color.white);
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
    public static void main(String[] args) {
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
    }

    public void imgIntro() {
        JPanel bo = new JPanel();
        bo.setBorder(new EmptyBorder(3, 10, 5, 5));
        bo.setPreferredSize(new Dimension(500, 740));
        bo.setBackground(Color.white);
        this.add(bo, BorderLayout.WEST);

        lblImage = new JLabel();
//        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/phone2.jpg")));
        lblImage.setIcon(new FlatSVGIcon("./main/java/IMG/login-img.svg"));
        bo.add(lblImage);
    }
    
}
