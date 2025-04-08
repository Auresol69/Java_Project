package GUI.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuenMatKhau extends JFrame {
    private JTextField txtEmail;
    private JButton btnGuiMa, btnHuy;
    
    // Constructor
    public QuenMatKhau(JFrame parent, boolean modal) {
        // Thiết lập JFrame
        setTitle("Quên Mật Khẩu");
        setSize(400, 200); // Kích thước cửa sổ
        setLocationRelativeTo(parent); // Đặt cửa sổ hiển thị ở giữa màn hình hoặc trên JFrame parent
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng cửa sổ khi nhấn 'X'

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các phần tử

        // Tiêu đề
        JLabel lblTitle = new JLabel("Quên Mật Khẩu");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        // Label Email
        JLabel lblEmail = new JLabel("Nhập Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblEmail, gbc);

        // Textfield Email
        txtEmail = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtEmail, gbc);

        // Button Gửi Mã
        btnGuiMa = new JButton("Gửi Mã");
        btnGuiMa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                if (!email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mã xác nhận đã gửi đến " + email);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập email!");
                }
            }
        });

        // Button Hủy
        btnHuy = new JButton("Hủy");
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng cửa sổ khi nhấn Hủy
            }
        });

        // Các nút Gửi Mã và Hủy
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnGuiMa, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(btnHuy, gbc);
    }

    // Main method để thử nghiệm
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tạo JFrame cha để truyền vào constructor QuenMatKhau
                JFrame jf = new JFrame();
                jf.setSize(500, 400);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setVisible(true);

                // Tạo đối tượng QuenMatKhau và hiển thị
                QuenMatKhau qmk = new QuenMatKhau(jf, true);
                qmk.setVisible(true);
            }
        });
    }
}
