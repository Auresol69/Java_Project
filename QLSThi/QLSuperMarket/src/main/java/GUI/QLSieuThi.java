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
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.apache.batik.svggen.font.Font;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import DTO.TaiKhoanDTO;
import GUI.Model.ChucNang;
import GUI.Model.Header;
import GUI.Model.TaiKhoanGUI;


public class QLSieuThi extends JFrame{
    JPanel panelWest, panelNorth, panelSouth, panelEast, panelCenter;
    JButton exit;
    JLabel Logout;
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
        
        
        panelWest.setLayout(new BorderLayout()); // Đặt các nút vào một cột dọc
        ImageIcon avtIcon = new ImageIcon(getClass().getResource("/IMG/icons8-avatar-50.png"));
        Image img = avtIcon.getImage(); 
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); 
        avtIcon = new ImageIcon(scaledImg);

        JLabel avt = new JLabel(avtIcon);
//        avt.setPreferredSize(new Dimension(100, 100)); 
//        JLabel avt = new JLabel(new ImageIcon("../icons8-avatar-50.png"));
        avt.setPreferredSize(new Dimension(200,200));
        panelWest.add(avt, BorderLayout.NORTH);
        JButton btnQLSP = new JButton("Quản lý Sản Phẩm");
        btnQLSP.setBackground(Color.BLACK);
        btnQLSP.setContentAreaFilled(true); // Giữ màu nền của nút
        btnQLSP.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        JButton btnQLNV = new JButton("Quản lý nhân viên");
        btnQLNV.setBackground(Color.BLACK);
        btnQLNV.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        btnQLNV.setContentAreaFilled(true); // Giữ màu nền của nút
        JButton btnQLKH = new JButton ("Quản lý khách hàng ");
        btnQLKH.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        btnQLKH.setBackground(Color.BLACK);
        btnQLKH.setContentAreaFilled(true); // Giữ màu nền của nút
        JButton btnNX = new JButton("Nhập và Xuất");
        btnNX.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        btnNX.setBackground(Color.BLACK);
        btnNX.setContentAreaFilled(true); // Giữ màu nền của nút
        JButton btnNCC = new JButton ("Nhà cung cấp");
        btnNCC.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        btnNCC.setBackground(Color.BLACK);
        btnNCC.setBorderPainted(false);
        btnNCC.setContentAreaFilled(true); // Giữ màu nền của nút
        Dimension btnSize = new Dimension(200, 50);
        btnQLSP.setMaximumSize(btnSize);
        btnQLNV.setMaximumSize(btnSize);
        btnQLKH.setMaximumSize(btnSize);
        btnNCC.setMaximumSize(btnSize);
        btnNX.setMaximumSize(btnSize);
        ArrayList<ChucNang> danhSachQuanLy = new ArrayList<>();
        // Thêm các mục vào danh sách
        danhSachQuanLy.add(new ChucNang(1, "Quản lý khách hàng", "../IMG/icons8-customer-48.png"));
        danhSachQuanLy.add(new ChucNang(2, "Quản lý sản phẩm", "../IMG/icons8-product-50.png"));
        danhSachQuanLy.add(new ChucNang(3, "Quản lý nhân viên", "../IMG/icons8-employee-50.png"));
        danhSachQuanLy.add(new ChucNang(4, "Quản lý nhà cung cấp", "../IMG/icons8-supplier-50.png"));
        danhSachQuanLy.add(new ChucNang(5, "Quản lý phiếu nhập", "../IMG/icons8-receipt-50.png"));
        danhSachQuanLy.add(new ChucNang(6, "Tài Khoản", "../IMG/icons8-receipt-50.png"));
        danhSachQuanLy.add(new ChucNang(7, "Phân Quyền", "../IMG/icons8-receipt-50.png"));
        JPanel LabelPanel = new JPanel();
        LabelPanel.setLayout(new BoxLayout(LabelPanel, BoxLayout.Y_AXIS));
        LabelPanel.setBackground(Color.white);
        LabelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(LabelPanel);
        scrollPane.setPreferredSize(new Dimension(220, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); // Xoá viền nếu cần
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        JLabel[] labels = new JLabel[danhSachQuanLy.size()];
        for (int i = 0; i < danhSachQuanLy.size(); i++) {
            String stringImg = danhSachQuanLy.get(i).geticon();
            labels[i] = new JLabel(danhSachQuanLy.get(i).getTenChucNang());
            labels[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0,0));
            labels[i].setIcon(new ImageIcon(getClass().getResource(stringImg)));
            labels[i].setMaximumSize(new Dimension(200, 50));
            labels[i].setBackground(Color.WHITE);
            labels[i].setForeground(Color.BLACK);
            labels[i].setOpaque(true);
            LabelPanel.add(labels[i]);
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
        panelWest.add(scrollPane, BorderLayout.CENTER);
        Logout = new JLabel("Đăng Xuất");
        Logout.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); 
        Logout.setHorizontalAlignment(SwingConstants.LEFT);     // Căn trái toàn bộ nội dung
        Logout.setIconTextGap(10);                              // Khoảng cách giữa icon và chữ
        Logout.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon resizedIcon = resizeIcon("../IMG/logout.png", 50, 50);
        Logout.setIcon(resizedIcon);
        Logout.setMaximumSize(new Dimension(200, 50));
        Logout.setBackground(Color.WHITE);
        Logout.setForeground(Color.BLACK);
        Logout.setOpaque(true);
        Logout.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                MouseEntered(e);
            }
            public void mouseExited(MouseEvent e){
                MouseExited(e);
            }
            public void mousePressed(MouseEvent e){
                Logout(e);
            }
        });
        panelWest.add(Logout ,BorderLayout.SOUTH);
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
            case "Tài Khoản":
                panelCenter = new TaiKhoanGUI(this);
                break;
        }; 
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    public void MouseEntered(MouseEvent e){
        JLabel ClickedLabel = (JLabel) e.getSource();
        String labelname = ClickedLabel.getText();
        ClickedLabel.setBackground(Color.GRAY);
        ClickedLabel.setForeground(Color.WHITE);
    }
    public void MouseExited(MouseEvent e) {
    JLabel ClickedLabel = (JLabel) e.getSource();
    ClickedLabel.setBackground(null); // Trả về màu nền mặc định
    ClickedLabel.setForeground(Color.BLACK);
    }
    public void Logout(MouseEvent e){
         int input = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (input == 0) {
            LoginGUI login = new LoginGUI();
            this.dispose();
            login.setVisible(true);
        }
    }
    private ImageIcon resizeIcon(String path, int width, int height) {
          try {
        BufferedImage originalImage = ImageIO.read(getClass().getResource(path));
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
    }
    public static void main (String []args){
        new QLSieuThi();
    }
}
