package GUI.Dialog;

import GUI.Component.HeaderTitle;
import GUI.Component.ImageChooserForm;
import GUI.Component.InputForm;
import GUI.Component.ButtonCustom;

import DAO.SanPhamDAO;
import GUI.Panel.SanPham; 
import DTO.SanPhamDTO; // Ensure this import matches the actual package of SanPhamDTO
import GUI.Component.NumericDocumentFilter;
import helper.Validation;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import org.apache.commons.codec.language.bm.Rule;

public class SanPhamDialog extends JDialog implements MouseListener {

    SanPham jpSP;
    private HeaderTitle titlePage;
    private JPanel pnlMain, pnlButtom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenSp,  loaiSp;
    private JTextField maSP;
    private ImageChooserForm anh;
    SanPhamDTO sp;

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpSP = jpSP;
        anh = new ImageChooserForm();
        tenSp = new InputForm("Tên Sản Phẩm");
        loaiSp = new InputForm("Loại Sản Phẩm");
        initComponents(title, type);
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, DTO.SanPhamDTO sp) {
        super(owner, title, modal);
        this.sp=sp;
        anh = new ImageChooserForm();
        maSP = new JTextField("");
        setMaSP(Integer.toString(sp.getMasp()));
        tenSp = new InputForm("Tên");
        setTenSP(sp.getTensp());
        
        loaiSp = new InputForm("Mã lọai");
        setLoaiSP(sp.getMaloaisp()); //
        
        
        anh.setImagePath(sp.getImg()); // Hiển thị ảnh từ sản phẩm
        this.jpSP = jpSP;
        initComponents(title, type);
    }

    

    
    

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 490));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBackground(Color.white);
        JPanel PanelAnh = new JPanel();
        PanelAnh.setBackground(Color.white);
        PanelAnh.setLayout(new FlowLayout());
        anh.setPreferredSize(new Dimension(100, 100));
        PanelAnh.setMaximumSize(new Dimension(100, 100)); // Giới hạn kích thước tối đa
        PanelAnh.add(anh);
        pnlMain.add(tenSp);
        pnlMain.add(loaiSp);
       
        pnlMain.add(PanelAnh);

        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm sản phẩm ", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);

        switch (type) {
            case "create" ->
                pnlButtom.add(btnThem);
            case "update" ->
                pnlButtom.add(btnCapNhat);
            case "view" -> {
                tenSp.setDisable();
                loaiSp.setDisable();
                anh.setEnable(false);
                
            }
            default ->
                throw new AssertionError();
        }
        pnlButtom.add(btnHuyBo);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnlMain, BorderLayout.CENTER);
        this.add(pnlButtom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setTenSP(String name) {
        tenSp.setText(name);
    }
    
    public String getTenSP() {
        return tenSp.getText();
    }
    
    public String getMaSP() {
        return maSP.getText();
    }
    
    public void setMaSP(String id) {
        this.maSP.setText(id);
    }
    
    // public String getSoLuong() {
    //     return Soluong.getText();
    // }
    private void setLoaiSP(int soluong2) {
        this.loaiSp.setText(String.valueOf(soluong2));}
    // public void setSoLuong(int value) {
    //     this.Soluong.setText(value);
    // }
    
    // public String getGiaSP() {
    //     return giaSp.getText();
    // }
    
    // public void setGiaSP(int value) {
    //     this.giaSp.setText(value);
    // }
    
    public String getLoaiSP() {
        return loaiSp.getText();
    }
    
    public void setLoaiSP(String value) {
        this.loaiSp.setText(value);
    }
    
    
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean Validation() {
        if (Validation.isEmpty(tenSp.getText())) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (Validation.isEmpty(loaiSp.getText())) {
            JOptionPane.showMessageDialog(this, "Loại sản phẩm không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
    
        // Kiểm tra mã loại sản phẩm
        int maloaisp = Integer.parseInt(loaiSp.getText());
        if (!SanPhamDAO.getInstance().isLoaiSPExists(maloaisp)) {
            JOptionPane.showMessageDialog(this, "Mã loại sản phẩm không tồn tại", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    
        return true;
    }
    @Override
public void mousePressed(MouseEvent e) {
    if (e.getSource() == btnThem && Validation()) {
        int id = SanPhamDAO.getInstance().getAutoIncrement();
        SanPhamDTO newSP = new SanPhamDTO(
                id,
                tenSp.getText(),
                
                Integer.parseInt(loaiSp.getText()),
                
                anh.getImagePath() // Đường dẫn ảnh từ ImageChooserForm
        );
        jpSP.sanphamBUS.add(newSP);
        jpSP.loadDataTable(jpSP.listsp);
        dispose();

    } else if (e.getSource() == btnHuyBo) {
        dispose();
    } else if (e.getSource() == btnCapNhat && Validation()) {
        sp.setTensp(tenSp.getText());
        
        sp.setMaloaisp(Integer.parseInt(loaiSp.getText()));
        
        sp.setImg(anh.getImagePath()); // Cập nhật đường dẫn ảnh
        jpSP.sanphamBUS.update(sp);
        jpSP.loadDataTable(jpSP.listsp);
        dispose();
    }
}

    public static boolean isPhoneNumber(String str) {
        // Loại bỏ khoảng trắng và dấu ngoặc đơn nếu có
        str = str.replaceAll("\\s+", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "");

        // Kiểm tra xem chuỗi có phải là một số điện thoại hợp lệ hay không
        if (str.matches("\\d{10}")) { // Kiểm tra số điện thoại 10 chữ số
            return true;
        } else if (str.matches("\\d{3}-\\d{3}-\\d{4}")) { // Kiểm tra số điện thoại có dấu gạch ngang
            return true;
        } else if (str.matches("\\(\\d{3}\\)\\d{3}-\\d{4}")) { // Kiểm tra số điện thoại có dấu ngoặc đơn
            return true;
        } else {
            return false; // Trả về false nếu chuỗi không phải là số điện thoại hợp lệ
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
