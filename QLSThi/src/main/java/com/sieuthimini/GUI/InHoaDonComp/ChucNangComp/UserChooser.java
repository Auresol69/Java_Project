package com.sieuthimini.GUI.InHoaDonComp.ChucNangComp;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sieuthimini.DAO.CustomerDAO;
import com.sieuthimini.DTO.CustomerDTO;
import com.sieuthimini.ExtendClasses.MessageBox;
import com.sieuthimini.GUI.InHoaDonComp.ChucNang;

public class UserChooser extends JDialog implements ActionListener {

    ChucNang chucNang;

    JTextField phoneField, addressField, nameField;

    JButton kiemtraButton, themButton;

    JPanel panel, panel2;

    private void setUpButton(JButton button, String text) {
        button.setText(text);

        button.setPreferredSize(new Dimension(100, 30));
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.addActionListener(this);

    }

    public UserChooser(JFrame parent, ChucNang chucNang) {
        super(parent, "Tìm kiếm khách hàng", true);
        this.chucNang = chucNang;

        this.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.add(new JLabel("Số điện thoại: "));
        phoneField = new JTextField(13);
        phoneField.setPreferredSize(new Dimension(150, 25));
        panel1.add(phoneField);
        panel.add(panel1);

        this.add(panel, BorderLayout.CENTER);

        panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setUpButton(kiemtraButton = new JButton(), "Kiểm tra");
        kiemtraButton.setPreferredSize(new Dimension(120, 30));
        panel2.add(kiemtraButton);
        this.add(panel2, BorderLayout.SOUTH);

        kiemtraButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') {
                    evt.consume();
                }
            }
        });

        this.pack();
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == kiemtraButton) {
            String phoneString = phoneField.getText();
            if (!phoneString.isEmpty() && phoneString.length() == 10) {
                CustomerDTO customerDTO = new CustomerDAO().getCustomerByPhone(phoneString);
                if (customerDTO != null) {
                    chucNang.setMaCustomer(customerDTO.getMacustomer());
                    MessageBox.showInfo("Thêm người dùng thành công");
                    chucNang.getCustomerField().setText(customerDTO.toString());
                    this.dispose();
                } else {
                    if (MessageBox.showConfirmDialog("Bạn có muốn thêm khách hàng không?",
                            "Không tìm thấy khách hàng") == JOptionPane.YES_OPTION) {
                        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                        panel3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                        panel3.add(new JLabel("Tên: "));
                        nameField = new JTextField(16);
                        nameField.setPreferredSize(new Dimension(150, 25));
                        panel3.add(nameField);
                        panel.add(panel3);

                        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                        panel4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                        panel4.add(new JLabel("Địa chỉ: "));
                        addressField = new JTextField(15);
                        addressField.setPreferredSize(new Dimension(150, 25));
                        panel4.add(addressField);
                        panel.add(panel4);

                        panel.revalidate();
                        panel.repaint();
                        this.pack();

                        panel2.remove(kiemtraButton);
                        themButton = new JButton();
                        setUpButton(themButton, "Thêm khách hàng");
                        themButton.setPreferredSize(new Dimension(120, 30));
                        panel2.add(themButton);
                    } else {
                        MessageBox.showInfo("Không thêm khách hàng");
                    }
                }
            } else {
                MessageBox.showError("Hãy nhập số điện thoại hợp lệ");
            }
        }
        if (e.getSource() == themButton) {
            String nameString = nameField.getText();
            String addressString = addressField.getText();
            String phoneString = phoneField.getText();

            if (new CustomerDAO().getCustomerByPhone(phoneString) != null) {
                MessageBox.showError("Số điện thoại đã tồn tại");
            } else {
                int customerID = new CustomerDAO().themCustomer(nameString, phoneString, addressString);
                chucNang.setMaCustomer(customerID);
                MessageBox.showInfo("Thêm khách hàng thành công");
                chucNang.getCustomerField().setText(new CustomerDAO().getCustomerByID(customerID).toString());
                this.dispose();
            }
        }
    }

}
