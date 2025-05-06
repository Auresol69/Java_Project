/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import GUI.Panel.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

import org.imgscalr.Scalr;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;


import DAO.ChiTietQuyenDAO;
import DAO.NhanVienDAO;
import DAO.NhomQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.NhanVienDTO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Model.ChucNang;
import GUI.Model.Header;
import GUI.BaoBao.*;
import GUI.BaoBao.GUI.InHoaDon;

public class QLSieuThi extends JFrame{
    private ArrayList<ChiTietQuyenDTO> listQuyen;
    NhomQuyenDTO nhomQuyenDTO;
    public NhanVienDTO nhanVienDTO;
    JPanel panelWest, panelNorth, panelSouth, panelEast, panelCenter;
    JButton exit;
    public TaiKhoanDTO user;
    private int DEFAULT_WIDTH, DEFAULT_HEIGHT;
    public static final List<ChucNang> DANH_SACH = Arrays.asList(
        new ChucNang(1, "Khách hàng", "../IMG/icons8-customer-48.png"),
        new ChucNang(2, "Nhân viên", "../IMG/icons8-employee-50.png"),
        new ChucNang(3, "Phân quyền", "../IMG/icons8-shield-48.png"),
        new ChucNang(4, "Nhà cung cấp", "../IMG/icons8-supplier-50.png"),
        new ChucNang(5, "Tài khoản", "../IMG/icons8-account-50.png"),
        new ChucNang(6, "Sản phẩm", "../IMG/icons8-product-50.png"),
        new ChucNang(10, "Phiếu Nhập", "../IMG/icons8-bill-48.png"),
        new ChucNang(7, "Thống kê", "../IMG/icons8-slice-50.png")
        // new ChucNang(9, "Sản phẩm hủy", "../IMG/icons8-trash-bin-50.png")
    );
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
        ImageIcon avtIcon ;
        switch (user.getPowerGroupId()) {
            case 1:
                avtIcon = new ImageIcon(getClass().getResource("/IMG/Employee/admin1.png"));
                break;
            default:
                avtIcon = new ImageIcon(getClass().getResource("/IMG/Employee/admin1.png"));
                break;
        }
        Image scaledImage = getScaledImage(avtIcon.getImage(), 200, 250);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Tạo JLabel chứa ảnh
        JLabel avt = new JLabel(scaledIcon);
        avt.setPreferredSize(new Dimension(200, 250));
        avt.setHorizontalAlignment(SwingConstants.CENTER);
        avt.setVerticalAlignment(SwingConstants.CENTER);
        avt.setOpaque(true);
        avt.setBackground(Color.WHITE);

        panelWest.setLayout(new BorderLayout());
        panelWest.add(avt, BorderLayout.NORTH);
        ArrayList<ChucNang> danhSachQuanLy = new ArrayList<>();
        // Thêm các mục vào danh sách
        JPanel panelLabelGroup = new JPanel();
        panelLabelGroup.setLayout(new BoxLayout(panelLabelGroup, BoxLayout.Y_AXIS));
        panelLabelGroup.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(panelLabelGroup);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(220,DEFAULT_HEIGHT - 1000));
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Xóa viền
        scrollPane.getVerticalScrollBar().setUnitIncrement(5);
        this.nhomQuyenDTO = NhomQuyenDAO.getInstance().selectedByID((user.getPowerGroupId()));
        this.nhanVienDTO = NhanVienDAO.getInstance().selectedByID((user.getMaStaff()));
        listQuyen = ChiTietQuyenDAO.getInstance().selectAll((user.getPowerGroupId()));  
        ChucNang cnnew = new ChucNang(0, "Bán Hàng", "../IMG/icons8-add-product-48.png");
        danhSachQuanLy.add(cnnew);
        for (ChucNang cn : DANH_SACH) {
            if (checkRole(cn.getMaChucNang())) {
                danhSachQuanLy.add(cn);
            } else {
                System.out.println("Không có quyền: " + cn.getTenChucNang());
            }
        }
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
        panelWest.add(scrollPane, BorderLayout.CENTER);
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
            header = new Header(this, DEFAULT_WIDTH, this.nhanVienDTO.getHoten());
        } else {
            header = new Header(this, DEFAULT_WIDTH);
        }
        panelNorth.add(header, BorderLayout.CENTER);
        panelCenter = new InHoaDon(this);
        this.add(panelCenter , BorderLayout.CENTER);
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
            case "Bán Hàng" :
                panelCenter = new InHoaDon(this);
                break ;
            case "Phiếu Nhập" :
                panelCenter = new Main(this);
                break;
            case "Khách hàng" : 
                panelCenter = new KhachHang(this);
                break ;
            case "Sản phẩm" : 
                panelCenter = new SanPham(this);
                break ;
            case "Nhân viên" : 
                panelCenter = new NhanVien(this);
                break ;
            case "Nhà cung cấp" : 
                panelCenter = new NhaCungCap(this);
                break ;
            case "Quản lý phiếu nhập" : 
                break ;
            case "Tài khoản" :
                panelCenter = new TaiKhoan(this); 
                break ;
            case "Phân quyền" :
                panelCenter = new PhanQuyen(this); 
                break ;
            case "Thống kê" :
                panelCenter = new ThongKe();
                break;
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
    public void dangXuat() {
        // Ví dụ: quay về form đăng nhập
        this.dispose(); // đóng form hiện tại
        new LoginGUI().setVisible(true); // mở lại form đăng nhập
    }
    public Image getScaledImage(Image srcImg, int width, int height) {
        if (srcImg instanceof BufferedImage) {
            return Scalr.resize((BufferedImage) srcImg, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, width, height);
        } else {
            // Chuyển đổi sang BufferedImage trước
            BufferedImage bimage = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            bimage.getGraphics().drawImage(srcImg, 0, 0, null);
            return Scalr.resize(bimage, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, width, height);
        }
    }
    public boolean checkRole (int s){
        boolean check = false;
        for (int i = 0 ; i < listQuyen.size(); i++){
            if (s == listQuyen.get(i).getMachucnang()){
                check = true;
                return check;
            }
        }
        return check;
    }
}
