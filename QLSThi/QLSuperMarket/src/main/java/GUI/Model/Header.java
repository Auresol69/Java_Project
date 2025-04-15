package GUI.Model;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Header extends JPanel {
    private JButton exit, minimize;
    private JLabel hello, nameMarket;
    private JFrame thisFrame;
    private int DEFAULT_WIDTH;
    private String username;

    public Header(JFrame parentFrame, int width , String username) {
        this.username = username;
        this.thisFrame = parentFrame;
        this.DEFAULT_WIDTH = width;
        init();
    }
    public Header(JFrame parentFrame, int width ) {
        this.thisFrame = parentFrame;
        this.DEFAULT_WIDTH = width;
        init();
    }
    private void init() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, 50)); // Tăng chiều cao
        this.setBackground(new Color(45, 52, 54));
        // Khởi tạo các thành phần
        nameMarket = new JLabel("SIÊU THỊ MINI");
        nameMarket.setForeground(Color.white);
        nameMarket.setBackground(new Color(45, 52, 54));
        nameMarket.setPreferredSize(new Dimension(100, 50));
        nameMarket.setOpaque(true);
        nameMarket.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        hello = new JLabel("Xin chào: " + username+"                    ");
        hello.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ đậm
        hello.setBackground(new Color(45, 52, 54));
        hello.setForeground(Color.white);
        hello.setOpaque(true);
        minimize = createButton("/IMG/layers.png");
        exit = createButton("/IMG/close.png");
    
        // Cấu hình nameMarket (bên trái)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(nameMarket, gbc);
    
        // Cấu hình Panel chứa các nút và hello (bên phải)
        JPanel buttonPanel = new JPanel(new BorderLayout()); // Thay vì FlowLayout
        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0, 0)); // Đảm bảo có khoảng cách giữa các nút
        innerPanel.setBackground(new Color(45, 52, 54));
        
        innerPanel.add(hello); // Thêm hello vào panel
        innerPanel.add(minimize);
        innerPanel.add(exit);
        
        buttonPanel.add(innerPanel, BorderLayout.EAST);
    
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(buttonPanel, gbc);
    }
    
    
    private JButton createButton(String imagePath) {
        JButton button = new JButton(resizeIcon(imagePath, 20, 20));
        button.setBackground(new Color(45, 52, 54));
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
        ImageIcon icon = new ImageIcon(getClass().getResource(path)); // Load từ resources
        Image img = icon.getImage();
    
        BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffered.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(img, 0, 0, width, height, null);
        g2d.dispose();
    
        return new ImageIcon(buffered);
    }
    
}
