package GUI.BaoBao.GUI.InHoaDonComp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.BaoBao.DAO.ProductDAO;
import GUI.BaoBao.ExtendClasses.GetImagePNG;
import GUI.BaoBao.ExtendClasses.MessageBox;
import GUI.BaoBao.ExtendClasses.QRScanner;

public class ChucNang extends JPanel implements ActionListener {

    private JButton inHoaDonButton, tienMatButton, chuỵenKhoanButton, hienThiSanPhamButton, quetMaButton;

    Table table;
    JFrame parent;

    private void setUpButton(JButton button, String text, String imgName) {
        button.setText(text);
        ImageIcon icon = new ImageIcon(new GetImagePNG().getImage(imgName, 60));
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setPreferredSize(new Dimension(80, 60));
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.addActionListener(this);

        this.add(button);
    }

    public ChucNang(Table table, JFrame parent) {
        this.table = table;
        this.parent = parent;

        this.setLayout(new BorderLayout());

        JPanel chucNangPanel = new JPanel(new GridLayout(0, 3, 5, 5));

        setUpButton(inHoaDonButton = new JButton(), "In hóa đơn", "cart-plus-solid.png");
        setUpButton(tienMatButton = new JButton(), "Tiền mặt", "");
        setUpButton(chuỵenKhoanButton = new JButton(), "Chuyển khoản", "");
        setUpButton(hienThiSanPhamButton = new JButton(), "Hiển thị sản phẩm", "");
        setUpButton(quetMaButton = new JButton(), "Quét mã", "");
        chucNangPanel.add(inHoaDonButton);
        chucNangPanel.add(tienMatButton);
        chucNangPanel.add(chuỵenKhoanButton);
        chucNangPanel.add(hienThiSanPhamButton);
        chucNangPanel.add(quetMaButton);
        this.add(chucNangPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quetMaButton) {
            String index = QRScanner.read(parent, "Quét mã QR sản phẩm");

            if (index == null) {
                MessageBox.showError("Quét QR không thành công!");
            } else {
                System.out.println(index);

                try {
                    int id = Integer.parseInt(index);

                    table.addSanPham(new ProductDAO().getProductById(id),
                            1);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Mã QR không hợp lệ: không phải số nguyên!");
                }
            }
        }
        if (e.getSource() == tienMatButton) {

        }
    }
}
