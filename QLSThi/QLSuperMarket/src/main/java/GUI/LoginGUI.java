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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    JTextField txtUsername, txtpassword;

    public LoginGUI(){
        init();
    }
    public void init(){
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(45, 52, 54));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(241, 196, 15));
        gbc.gridwidth = 2;
        this.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel userLabel = new JLabel("Tên đăng nhập:");
        userLabel.setForeground(Color.WHITE);
        this.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField userText = new JTextField(15);
        userText.setFont(new Font("Arial", Font.PLAIN, 14));
        userText.setOpaque(false);
        userText.setForeground(Color.WHITE);
        userText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        this.add(userText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setForeground(Color.WHITE);
        this.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordText = new JPasswordField(15);
        passwordText.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordText.setOpaque(false);
        passwordText.setForeground(Color.WHITE);
        passwordText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        this.add(passwordText, gbc);
        userText.setCaretColor(Color.WHITE);
        passwordText.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBackground(new Color(41, 128, 185));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(0, 15, 5, 15));
        this.add(loginButton, gbc);

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
}