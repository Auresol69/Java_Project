package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JFrame {
    JTextField txtUsername, txtpassword;
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
        this.setLayout(new GridLayout(1, 2));
        right = new JPanel(new GridBagLayout());
        left = new JPanel();
        right.setBackground(new Color(45, 52, 54));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        left.setBackground(new Color(238, 59, 59));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel slogan = new JLabel("<html><div style='width: 200px; text-align:center;'><br>Be part of our awesome team and have fun with us</div></html>");
        slogan.setFont(new Font("Montserrat", Font.BOLD, 18));
        // slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
        slogan.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang

        JLabel sloganLabel = createImageLabel("src\\main\\java\\IMG\\icons8-rocket-100.png", 120, 120);
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // slogan.setHorizontalAlignment(SwingConstants.CENTER);
        //         // Tạo JLabel cho Icon với hiệu ứng Hover
        //         JLabel sloganLabel = createImageLabel("QLSuperMarket\\src\\main\\java\\IMG\\icons8-rocket-100.png", 120, 120);
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo BoxLayout
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
        left.add(Box.createVerticalGlue());
        left.add(sloganLabel);
        left.add(Box.createRigidArea(new Dimension(0, 10)));
        left.add(slogan);
        left.add(Box.createVerticalGlue());

        sloganLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sloganLabel.setIcon(new ImageIcon(new ImageIcon("src\\main\\java\\IMG\\icons8-rocket-100.png")
                        .getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sloganLabel.setIcon(new ImageIcon(new ImageIcon("src\\main\\java\\IMG\\icons8-rocket-100.png")
                        .getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
            }
        });

        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(241, 196, 15));
        gbc.gridwidth = 2;
        right.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel userLabel = new JLabel("Tên đăng nhập:");
        userLabel.setForeground(Color.WHITE);
        right.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField userText = new JTextField(15);
        userText.setFont(new Font("Arial", Font.PLAIN, 14));
        userText.setOpaque(false);
        userText.setForeground(Color.WHITE);
        userText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        right.add(userText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setForeground(Color.WHITE);
        right.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordText = new JPasswordField(15);
        passwordText.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordText.setOpaque(false);
        passwordText.setForeground(Color.WHITE);
        passwordText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        userText.setCaretColor(Color.WHITE);
        passwordText.setCaretColor(Color.WHITE);
        right.add(passwordText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBackground(new Color(41, 128, 185));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        right.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                if (username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.add(left);
        this.add(right);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
    public static JLabel createImageLabel(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        if (originalIcon.getIconWidth() == -1) {
            System.out.println("Lỗi: Không thể tải ảnh từ " + imagePath);
            return new JLabel("Ảnh không tồn tại!");
        }
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }
}
