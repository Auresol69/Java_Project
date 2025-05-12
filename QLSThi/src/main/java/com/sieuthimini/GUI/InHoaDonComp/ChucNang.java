package com.sieuthimini.GUI.InHoaDonComp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sieuthimini.BUS.PayByBUS;
import com.sieuthimini.DAO.BillDAO;
import com.sieuthimini.DAO.BillProductDAO;
import com.sieuthimini.DAO.ProductDAO;
import com.sieuthimini.DTO.ProductDTO;
import com.sieuthimini.ExtendClasses.GetImagePNG;
import com.sieuthimini.ExtendClasses.MessageBox;
import com.sieuthimini.ExtendClasses.QRScanner;
import com.sieuthimini.GUI.InHoaDonComp.ChucNangComp.Detail;
import com.sieuthimini.GUI.InHoaDonComp.ChucNangComp.HoaDon;
import com.sieuthimini.GUI.InHoaDonComp.ChucNangComp.TienMat;
import com.sieuthimini.GUI.InHoaDonComp.ChucNangComp.UserChooser;

public class ChucNang extends JPanel implements ActionListener {

    private JButton inHoaDonButton, tienMatButton, chuyenKhoanButton, hienThiSanPhamButton, quetMaButton, huyButton,
            userButton;

    Table table;
    JFrame parent;
    JTextField customerField;
    private Integer maPayBy = null;
    private Integer maCustomer = null;

    private void setUpButton(JButton button, String text, String imgName) {
        button.setText(text);
        ImageIcon icon = new ImageIcon(new GetImagePNG().getImage(imgName, 30));
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setPreferredSize(new Dimension(100, 80));
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

        setUpButton(inHoaDonButton = new JButton(), "In hóa đơn", "receipt-solid.png");
        setUpButton(tienMatButton = new JButton(), "Tiền mặt", "money-bill-1-solid.png");
        setUpButton(chuyenKhoanButton = new JButton(), "Chuyển khoản", "building-columns-solid.png");
        setUpButton(hienThiSanPhamButton = new JButton(), "Hiển thị sản phẩm", "eye-solid.png");
        setUpButton(quetMaButton = new JButton(), "Quét mã", "qrcode-solid.png");
        setUpButton(userButton = new JButton(), "Người mua", "user-solid.png");
        setUpButton(huyButton = new JButton(), "Hủy sản phẩm", "trash-solid.png");
        chucNangPanel.add(inHoaDonButton);
        chucNangPanel.add(tienMatButton);
        chucNangPanel.add(chuyenKhoanButton);
        chucNangPanel.add(hienThiSanPhamButton);
        chucNangPanel.add(quetMaButton);
        chucNangPanel.add(userButton);
        chucNangPanel.add(huyButton);

        JPanel customerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        customerPanel.add(new JLabel("Người mua:"));
        chucNangPanel.add(customerPanel, BorderLayout.EAST);

        JPanel customerFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        customerFieldPanel.add(customerField = new JTextField("Chưa có", 10));
        chucNangPanel.add(customerFieldPanel, BorderLayout.WEST);
        customerField.setEditable(false);
        customerField.setFocusable(false);

        this.add(chucNangPanel);

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

                    JDialog dialog = new JDialog(parent, "Nhập số lượng", true);
                    dialog.setLayout(new BorderLayout());
                    JPanel soLuongPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                    soLuongPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    soLuongPanel.add(new JLabel("Số lượng: "));
                    JTextField soLuongField = new JTextField(5);
                    soLuongField.setPreferredSize(new Dimension(80, 25));
                    soLuongPanel.add(soLuongField);
                    dialog.add(soLuongPanel, BorderLayout.CENTER);

                    JButton submiButton = new JButton("OK");
                    submiButton.setPreferredSize(new Dimension(80, 30));
                    submiButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!soLuongField.getText().isEmpty() && soLuongField.getText().matches("\\d+")) {
                                ProductDTO product = new ProductDAO().getProductById(id);
                                if (product != null) {
                                    table.addSanPham(product, Integer.parseInt(soLuongField.getText()));
                                    dialog.dispose();
                                } else {
                                    MessageBox.showError("Sản phẩm không tồn tại!");
                                }
                            } else {
                                MessageBox.showError("Số lượng phải là số nguyên dương!");
                            }
                        }
                    });
                    dialog.add(submiButton, BorderLayout.SOUTH);

                    dialog.pack();
                    dialog.setLocationRelativeTo(parent);
                    dialog.setVisible(true);

                    soLuongField.requestFocusInWindow();
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Mã QR không hợp lệ: không phải số nguyên!");
                }
            }
        }
        if (e.getSource() == hienThiSanPhamButton)

        {
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
            } else {
                MessageBox.showError("Giỏ hàng đang trống");
            }
        }
        if (e.getSource() == chuyenKhoanButton) {
            String index = QRScanner.read(parent, "Quét mã QR chuyển khoản");

            if (index == null) {
                MessageBox.showError("Quét QR không thành công!");
            } else {

                maPayBy = new PayByBUS().createPayBy("Chuyển khoản", index);
                MessageBox.showInfo("Chuyển khoản thành công");

                chuyenKhoanButton.setEnabled(false);
                tienMatButton.setEnabled(false);
            }
        }
        if (e.getSource() == inHoaDonButton) {
            if (maPayBy == null || maPayBy == -1) {
                MessageBox.showError("Vui lòng chọn phương thức thanh toán");
            } else if (maCustomer == null || maCustomer == -1) {
                MessageBox.showError("Vui lòng chọn khách hàng");

            } else if (table.getModel().getRowCount() < 1) {
                MessageBox.showError("Giỏ hàng bạn đang trống");
            } else {
                if (MessageBox.showConfirmDialog("Bạn có muốn in hóa đơn ngay không?",
                        "Đồng ý thanh toán") == JOptionPane.OK_OPTION) {
                    Integer maBill = new BillDAO().createBill(maCustomer, maPayBy);
                    for (int i = 0; i < table.getModel().getRowCount(); i++) {
                        new BillProductDAO().createBillProduct(maBill,
                                Integer.parseInt(table.getModel().getValueAt(i, 0).toString()),
                                Integer.parseInt(table.getModel().getValueAt(i, 2).toString()));
                    }
                    new HoaDon(parent, table.getTable(), table.getTongLabel(), maBill);

                    MessageBox.showInfo("In hóa đơn thành công");

                    // Clear rows
                    table.getModel().setRowCount(0);

                    // Reset buttons
                    tienMatButton.setEnabled(true);
                    chuyenKhoanButton.setEnabled(true);

                    // Reset payment and customer IDs
                    customerField.setText("Không có");
                    maPayBy = null;
                    maCustomer = null;
                } else {
                    MessageBox.showInfo("Hủy in hóa đơn");
                }
            }
        }
        if (e.getSource() == userButton) {
            new UserChooser(parent, this);
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

    public Integer getMaCustomer() {
        return maCustomer;
    }

    public void setMaCustomer(Integer maCustomer) {
        this.maCustomer = maCustomer;
    }

    public JButton getTienMatButton() {
        return tienMatButton;
    }

    public void setTienMatButton(JButton tienMatButton) {
        this.tienMatButton = tienMatButton;
    }

    public JButton getChuyenKhoanButton() {
        return chuyenKhoanButton;
    }

    public void setChuyenKhoanButton(JButton chuyenKhoanButton) {
        this.chuyenKhoanButton = chuyenKhoanButton;
    }

    public JTextField getCustomerField() {
        return customerField;
    }

    public void setCustomerField(JTextField customerField) {
        this.customerField = customerField;
    }

    public JButton getUserButton() {
        return userButton;
    }

    public void setUserButton(JButton userButton) {
        this.userButton = userButton;
    }
}