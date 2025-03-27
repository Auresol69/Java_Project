/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author ANH QUYÊN
 */
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class LoginGUI extends JPanel {
    JTextField txtUsername, txtpassword;
    JPanel left , right;
    ImageIcon Slogan, logo;
    public LoginGUI(){
        init();
    }
    public void init(){
        
        this.setLayout(new GridLayout(1,2));
        right = new JPanel(new GridBagLayout());
        left = new JPanel();
        right.setBackground(new Color(45, 52, 54));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel slogan = new JLabel("<html><div style='text-align: center; width: 200px;'>Be part of our awesome team and have fun with us</div></html>");
        slogan.setFont(new Font("Montserrat", Font.BOLD, 18));
        slogan.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
        left.add(slogan);
        left.setBackground(new Color(238, 59, 59));
        left.setLayout(new GridLayout(2,1));
        JLabel sloganLabel = createImageLabel("QLSThi\\QLSuperMarket\\src\\main\\java\\IMG\\icons8-rocket-100.png", 200,200 ); 
        left.add(sloganLabel);
        
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
        right.add(passwordText, gbc);
        userText.setCaretColor(Color.WHITE);
        passwordText.setCaretColor(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth= 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Kéo giãn button theo chiều ngang
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBackground(new Color(41, 128, 185));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        // loginButton.setBorder(new RoundBorder(20)); // 20px bo góc
        right.add(loginButton, gbc);

        this.add(left);
        this.add(right);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                JPanel panel = new JPanel();
                if (username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(panel, "Đăng nhập thành công!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Đăng nhập");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new LoginGUI();
        frame.setSize((int) (d.getWidth()/2), (int) (d.getHeight()*2/3));
        frame.setLocation((int)(d.getWidth() - frame.getWidth())/2 , (int)(d.getHeight() - frame.getHeight())/2 -50);
        frame.add(panel);
        frame.setVisible(true);
    }
    public static JLabel createImageLabel(String imagePath, int width, int height) {
        // Đọc ảnh từ đường dẫn
        ImageIcon originalIcon = new ImageIcon(imagePath);

        // Kiểm tra nếu ảnh tồn tại
        if (originalIcon.getIconWidth() == -1) {
            System.out.println("Lỗi: Không thể tải ảnh từ " + imagePath);
            return new JLabel("Ảnh không tồn tại!");
        }

        // Resize ảnh
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Tạo JLabel chứa ảnh
        return new JLabel(resizedIcon);
    }
    class RoundButton extends JButton {
        private int radius;
    
        public RoundButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setContentAreaFilled(false); // Loại bỏ màu nền mặc định
            setFocusPainted(false); // Bỏ viền khi chọn
            setBorderPainted(false); // Ẩn viền
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            // Màu nền nút
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
    
            // Vẽ viền nếu có
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    
            super.paintComponent(g);
        }
    }
}
