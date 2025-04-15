/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import DTO.TaiKhoanDTO;
import GUI.Model.ChucNang;
import GUI.Model.Header;


public class QLSieuThi extends JFrame{
    JPanel panelWest, panelNorth, panelSouth, panelEast, panelCenter;
    JButton exit;
    public TaiKhoanDTO user;
    private int DEFAULT_WIDTH, DEFAULT_HEIGHT;
    public QLSieuThi(){
        init();
    }
     public QLSieuThi(TaiKhoanDTO user) throws UnsupportedLookAndFeelException {
        this.user = user;
        init();
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
        UIManager.put("Table.selectionForeground", new Color(0, 0, 0));
        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("Table.rowHeight", 40);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TableHeader.height", 40);
        UIManager.put("TableHeader.font", UIManager.getFont("h4.font"));
        UIManager.put("TableHeader.background", new Color(242, 242, 242));
        UIManager.put("TableHeader.separatorColor", new Color(242, 242, 242));
        UIManager.put("TableHeader.bottomSeparatorColor", new Color(242, 242, 242));
    }
    public void init(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        DEFAULT_HEIGHT = (int)(d.getHeight()*5/6);
        DEFAULT_WIDTH = (int)(d.getWidth()*5/6);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        panelWest = new JPanel();
        panelWest.setPreferredSize(new Dimension(200,0));
        
        
        panelWest.setLayout(new BorderLayout());
        panelWest.setBackground(Color.WHITE);
        ImageIcon avtIcon = new ImageIcon(getClass().getResource("/IMG/icons8-avatar-50.png"));
        Image avtIMG = avtIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
        // Tạo JLabel chứa ảnh
        JLabel avt = new JLabel(avtIcon);
        avt.setIcon(new ImageIcon(avtIMG ));
        avt.setPreferredSize(new Dimension(200, 120));
        avt.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa ảnh trong label
        avt.setVerticalAlignment(SwingConstants.CENTER);
        
        // Thêm vào panelWest ở vị trí BorderLayout.NORTH
        panelWest.setLayout(new BorderLayout());
        panelWest.add(avt, BorderLayout.NORTH);

        ArrayList<ChucNang> danhSachQuanLy = new ArrayList<>();
        // Thêm các mục vào danh sách
        JPanel panelLabelGroup = new JPanel();
        panelLabelGroup.setLayout(new BoxLayout(panelLabelGroup, BoxLayout.Y_AXIS));
        panelLabelGroup.setBackground(Color.WHITE);

        danhSachQuanLy.add(new ChucNang(1, "Quản lý khách hàng", "../IMG/icons8-customer-48.png"));
        danhSachQuanLy.add(new ChucNang(2, "Quản lý sản phẩm", "../IMG/icons8-product-50.png"));
        danhSachQuanLy.add(new ChucNang(3, "Quản lý nhân viên", "../IMG/icons8-employee-50.png"));
        danhSachQuanLy.add(new ChucNang(4, "Quản lý nhà cung cấp", "../IMG/icons8-supplier-50.png"));
        danhSachQuanLy.add(new ChucNang(5, "Quản lý phiếu nhập", "../IMG/icons8-receipt-50.png"));
        danhSachQuanLy.add(new ChucNang(6, "Tài Khoản", "../IMG/icons8-receipt-50.png"));
        danhSachQuanLy.add(new ChucNang(7, "Phân Quyền", "../IMG/icons8-receipt-50.png"));
        JLabel[] labels = new JLabel[danhSachQuanLy.size()];
        for (int i = 0; i < danhSachQuanLy.size(); i++) {
            String stringImg = danhSachQuanLy.get(i).geticon();
            String tenChucNang = danhSachQuanLy.get(i).getTenChucNang();
            labels[i] = new JLabel(tenChucNang);
            ImageIcon icon = new ImageIcon(getClass().getResource(stringImg));
            Image im = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // scale ảnh 30x30
            labels[i].setIcon(new ImageIcon(im));
            labels[i].setMaximumSize(new Dimension(200, 50));
            labels[i].setBackground(Color.white);
            labels[i].setForeground(Color.BLACK);
            labels[i].setOpaque(true);
            labels[i].setHorizontalAlignment(SwingConstants.LEFT);
            labels[i].setIconTextGap(15); // khoảng cách icon - text
            labels[i].setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // padding trong
            labels[i].setAlignmentX(Component.LEFT_ALIGNMENT); // canh trái khi dùng BoxLayout
            panelLabelGroup.add(labels[i]);
            labels[i].addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                MouseEntered(e);
            }
            public void mouseExited(MouseEvent e){
                MouseExited(e);
            }
            public void mouseClicked(MouseEvent e){
                JLabel ClickLabel = (JLabel) e.getSource();
                switchPanel(ClickLabel.getText());
            }
            });
        }
        panelWest.add(panelLabelGroup, BorderLayout.CENTER);
        JLabel lblDangXuat = new JLabel("Đăng Xuất");
        ImageIcon icon = new ImageIcon(getClass().getResource("../IMG/logout.png"));
        Image imLogut = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // scale ảnh 30x30
        lblDangXuat.setIcon(new ImageIcon(imLogut));
        lblDangXuat.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        lblDangXuat.setOpaque(true);
        lblDangXuat.setBackground(Color.WHITE);
        lblDangXuat.setForeground(Color.RED);
        lblDangXuat.setFont(new Font("Arial", Font.BOLD, 13));
        lblDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        lblDangXuat.setIconTextGap(15);
        lblDangXuat.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        lblDangXuat.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelWest.add(lblDangXuat , BorderLayout.SOUTH);
        lblDangXuat.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            MouseEntered(e); // Đổi màu nền
        }

        public void mouseExited(MouseEvent e) {
            lblDangXuat.setForeground(Color.red);
            lblDangXuat.setBackground(null);
        }

        public void mouseClicked(MouseEvent e) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Gọi hàm đăng xuất hoặc chuyển về màn hình đăng nhập
                dangXuat(); // Bạn tự định nghĩa hàm này
            }
        }
    });
//        panelWest.add(avt);
//        panelWest.add(btnQLSP);
//        panelWest.add(btnQLNV);
//        panelWest.add(btnQLKH);
//        panelWest.add(btnNX);
//        panelWest.add(btnNCC);
        panelNorth = new JPanel(new BorderLayout()); // Đổi từ FlowLayout sang BorderLayout
        panelNorth.setPreferredSize(new Dimension(0, 50));
        JPanel header ;
        if (user != null){
            header = new Header(this, DEFAULT_WIDTH, user.getUsername());
        } else {
            header = new Header(this, DEFAULT_WIDTH);
        }
        panelNorth.add(header, BorderLayout.CENTER);
        panelCenter = new JPanel();
        panelWest.setBackground(Color.white);
        this.add(panelNorth , BorderLayout.NORTH);
        this.add(panelWest, BorderLayout.WEST);
        
        this.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void switchPanel(String TenChucNang){
        getContentPane().remove(panelCenter);
        switch(TenChucNang){
            case "Quản lý khách hàng" : 
                
                break ;
            case "Quản lý sản phẩm" : 
                break ;
            case "Quản lý nhân viên" : 
                panelCenter = new NhanVienGUI();
                break ;
            case "Quản lý nhà cung cấp" : 
                break ;
            case "Quản lý phiếu nhập" : 
                break ;
        }; 
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    public void MouseEntered(MouseEvent e){
        JLabel ClickedLabel = (JLabel) e.getSource();
        ClickedLabel.setBackground(Color.gray);
        ClickedLabel.setForeground(Color.black); // đổi màu chữ thành đen
    }
    public void MouseExited(MouseEvent e) {
        JLabel ClickedLabel = (JLabel) e.getSource();
        ClickedLabel.setBackground(null); // trả về màu nền mặc định
        ClickedLabel.setForeground(null); // trả về màu chữ mặc định
    }   
    public static void main (String []args){
        new QLSieuThi();
    }
    public void dangXuat() {
        // Ví dụ: quay về form đăng nhập
        this.dispose(); // đóng form hiện tại
        new LoginGUI().setVisible(true); // mở lại form đăng nhập
    }
}
