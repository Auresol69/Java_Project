package com.sieuthimini.GUI.InHoaDonComp;

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

import com.sieuthimini.BUS.PayByBUS;
import com.sieuthimini.DAO.BillDAO;
import com.sieuthimini.DAO.BillProductDAO;
import com.sieuthimini.DAO.ProductDAO;
import com.sieuthimini.DTO.CustomerDTO;
import com.sieuthimini.ExtendClasses.GetImagePNG;
import com.sieuthimini.ExtendClasses.MessageBox;
import com.sieuthimini.ExtendClasses.QRScanner;
import com.sieuthimini.GUI.InHoaDonComp.ChucNangComp.Detail;
import com.sieuthimini.GUI.InHoaDonComp.ChucNangComp.TienMat;

public class ChucNang extends JPanel implements ActionListener {

    private JButton inHoaDonButton, tienMatButton, chuỵenKhoanButton, hienThiSanPhamButton, quetMaButton, huyButton;

    Table table;
    JFrame parent;
    private Integer maPayBy = null;
    private Integer maCustomer = null;

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
        setUpButton(huyButton = new JButton(), "Hủy sản phẩm", "");
        chucNangPanel.add(inHoaDonButton);
        chucNangPanel.add(tienMatButton);
        chucNangPanel.add(chuỵenKhoanButton);
        chucNangPanel.add(hienThiSanPhamButton);
        chucNangPanel.add(quetMaButton);
        chucNangPanel.add(huyButton);
        this.add(chucNangPanel);

        // Add listener to comboBox selection changes to update maCustomer
        this.table.getComboBox().addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                CustomerDTO selectedCustomer = (CustomerDTO) e.getItem();
                if (selectedCustomer != null) {
                    maCustomer = selectedCustomer.getMacustomer();
                } else {
                    maCustomer = null;
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quetMaButton) {
            String index = QRScanner.read(parent, "Quét mã QR sản phẩm");

            if (index == null) {
                MessageBox.showError("Quét QR không thành công!");
            } else {

                try {
                    int id = Integer.parseInt(index);

                    table.addSanPham(new ProductDAO().getProductById(id),
                            1);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Mã QR không hợp lệ: không phải số nguyên!");
                }
            }
        }
        if (e.getSource() == hienThiSanPhamButton) {
            if (table.getTable().getSelectedRow() != -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(table.getTable().getSelectedRow(), 0).toString());
                new Detail(parent, new ProductDAO().getProductById(id), table);
            } else {
                MessageBox.showError("Vui lòng chọn 1 sản phẩm");
            }
        }
        if (e.getSource() == tienMatButton) {
            Integer tongTien = 0;
            if (table.getModel().getRowCount() >= 1) {
                for (int i = 0; i < table.getModel().getRowCount(); i++) {
                    tongTien += Integer.parseInt(table.getModel().getValueAt(i, 4).toString());
                }
                new TienMat(parent, this, tongTien);
                tienMatButton.setEnabled(false);
                chuỵenKhoanButton.setEnabled(false);
            } else {
                MessageBox.showError("Giỏ hàng đang trống");
            }
        }
        if (e.getSource() == chuỵenKhoanButton) {
            String index = QRScanner.read(parent, "Quét mã QR chuyển khoản");

            if (index == null) {
                MessageBox.showError("Quét QR không thành công!");
            } else {

                maPayBy = new PayByBUS().createPayBy("Chuyển khoản", index);
            }
        }
        if (e.getSource() == inHoaDonButton) {
            if (maPayBy == null || maPayBy == -1) {
                MessageBox.showError("Vui lòng chọn phương thức thanh toán");
            } else if (maCustomer == null || maCustomer == -1) {
                MessageBox.showError("Vui lòng chọn khách hàng");
            } else {
                Integer maBill = new BillDAO().createBill(maPayBy, maCustomer);
                for (int i = 0; i < table.getModel().getRowCount(); i++) {
                    new BillProductDAO().createBillProduct(maBill,
                            Integer.parseInt(table.getModel().getValueAt(i, 0).toString()),
                            Integer.parseInt(table.getModel().getValueAt(i, 2).toString()));
                }
                MessageBox.showInfo("In hóa đơn thành công");

                // Clear rows
                table.getModel().setRowCount(0);

                // Reset buttons
                tienMatButton.setEnabled(true);
                chuỵenKhoanButton.setEnabled(true);

                // Reset payment and customer IDs
                maPayBy = null;
                maCustomer = null;
            }
        }
        if (e.getSource() == huyButton) {
            int selectedRow = table.getTable().getSelectedRow();
            if (selectedRow == -1) {
                MessageBox.showError("Vui lòng chọn dòng để xóa.");
                return;
            }
            table.deleteSelectedRow();
        }
    }

    public Integer getMaPayBy() {
        return maPayBy;
    }

    public void setMaPayBy(Integer maPayBy) {
        this.maPayBy = maPayBy;
    }
}
