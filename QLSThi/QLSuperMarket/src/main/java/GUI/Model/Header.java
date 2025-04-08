package GUI.Model;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Header extends JPanel {
    private JButton exit, minimize;
    private JLabel hello, nameMarket;
    private JFrame thisFrame;
    private int DEFAULT_WIDTH;

    public Header(JFrame parentFrame, int width) {
        this.thisFrame = parentFrame;
        this.DEFAULT_WIDTH = width;
        init();
    }

    private void init() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, 50)); // Tăng chiều cao
        // Khởi tạo các thành phần
        nameMarket = new JLabel("SIÊU THỊ MINI");
        nameMarket.setForeground(Color.white);
        nameMarket.setBackground(new Color(45, 52, 54));
        nameMarket.setPreferredSize(new Dimension(100, 50));
        nameMarket.setOpaque(true);
        nameMarket.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        hello = new JLabel("Xin chào: ");
        hello.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        minimize = createButton("QLSuperMarket\\src\\main\\java\\IMG\\icons8-minimize-window-50.png");
        exit = createButton("QLSuperMarket\\src\\main\\java\\IMG\\icons8-exit-30.png");
        hello.setBackground(new Color(45, 52, 54));
        hello.setForeground(Color.white);
        hello.setPreferredSize(new Dimension(100, 50));
        hello.setOpaque(true);
        exit.setBackground(new Color(45, 52, 54));
        minimize.setBackground(new Color(45, 52, 54));;
        this.setBackground(new Color(45, 52, 54));
        
        // Cấu hình nameMarket (bên trái)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(nameMarket, gbc);
        
        // Cấu hình Panel chứa các nút và hello (bên phải)
        JPanel buttonPanel = new JPanel(new BorderLayout()); // Thay vì FlowLayout
        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0)); // Vẫn giữ nút bên phải
        innerPanel.add(hello);
        innerPanel.add(minimize);
        innerPanel.add(exit);
        
        buttonPanel.add(innerPanel, BorderLayout.EAST);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(buttonPanel, gbc);
    }
    
    private JButton createButton(String imagePath) {
        JButton button = new JButton(resizeIcon(imagePath, 40, 40));
        // button.setBackground(Color.GREEN);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(50, 50));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                if (sourceButton == exit) {
                    sourceButton.setBackground(Color.RED);
                } else if (sourceButton == minimize) {
                    sourceButton.setBackground(new Color(70, 77, 79));
                }
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(45, 52, 54));
            }
            public void mouseClicked(MouseEvent e) {
                if (button == exit) {
                    System.exit(0);
                } else if (button == minimize && thisFrame != null) {
                    thisFrame.setState(JFrame.ICONIFIED);
                }
            }
        });
        return button;
    }
    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
