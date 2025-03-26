package GUI_model;

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
        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, 40)); // Tăng chiều cao
        
        // Khởi tạo các thành phần
        nameMarket = new JLabel("SIÊU THỊ MINI");
        nameMarket.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        hello = new JLabel("Xin chào");
        hello.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        minimize = createButton("QLSThi\\QLSuperMarket\\src\\main\\java\\IMG\\icons8-minimize-window-50.png");
        exit = createButton("QLSThi\\QLSuperMarket\\src\\main\\java\\IMG\\icons8-exit-30.png");
        
        this.setBackground(Color.GREEN);
        
        // Cấu hình nameMarket (bên trái)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 0);
        this.add(nameMarket, gbc);
        
        // Cấu hình Panel chứa các nút và hello (bên phải)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setBackground(Color.GREEN);
        buttonPanel.add(hello);
        buttonPanel.add(minimize);
        buttonPanel.add(exit);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 0, 5, 10);
        this.add(buttonPanel, gbc);
    }
    
    private JButton createButton(String imagePath) {
        JButton button = new JButton(resizeIcon(imagePath, 30, 30));
        button.setBackground(Color.GREEN);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(40, 40));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                if (sourceButton == exit) {
                    sourceButton.setBackground(Color.RED);
                } else if (sourceButton == minimize) {
                    sourceButton.setBackground(Color.YELLOW);
                }
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.GREEN);
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
