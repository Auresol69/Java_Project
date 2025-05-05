package com.sieuthimini.GUI.InHoaDonComp.SanPhamComp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sieuthimini.DTO.ProductDTO;
import com.sieuthimini.ExtendClasses.GetImagePNG;
import com.sieuthimini.GUI.InHoaDonComp.Table;

public class Order extends JPanel implements ActionListener {
    private JButton giamButton, tangButton, orderButton;
    private JTextField soluongField;
    ImageIcon icon;
    ProductDTO productDTO;
    private Table table;

    private void setUpButton(JButton button, String text, String imgName) {
        button.setText(text);

        if (!imgName.isEmpty()) {
            ImageIcon icon = new ImageIcon(new GetImagePNG().getImage(imgName, 60));
            if (icon != null) {
                button.setIcon(icon);
            }
        }

        button.setPreferredSize(new Dimension(60, 40));
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.addActionListener(this);
    }

    public Order(ProductDTO productDTO, Table table) {
        this.productDTO = productDTO;
        this.table = table;

        this.setLayout(new BorderLayout());
        this.add(new JLabel(icon = new ImageIcon(new GetImagePNG().getImage(productDTO.getImg(), 60))),
                BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(productDTO.getTensp()));
        panel.add(new JLabel(productDTO.getTenLoaiSanPham()));
        panel.add(new JLabel(productDTO.getDongiasanpham().toString() + "VND"));
        this.add(panel, BorderLayout.EAST);

        JPanel panel2 = new JPanel(new FlowLayout());
        giamButton = new JButton();
        setUpButton(giamButton, "-", "");
        panel2.add(giamButton);

        soluongField = new JTextField("1", 5);
        panel2.add(soluongField);

        tangButton = new JButton();
        setUpButton(tangButton, "+", "");
        panel2.add(tangButton);

        orderButton = new JButton();
        setUpButton(orderButton, "", "cart-plus-solid.png");
        panel2.add(orderButton);
        this.add(panel2, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == giamButton) {
            if (!soluongField.getText().isEmpty() && Integer.parseInt(soluongField.getText().toString()) >= 2) {
                Integer tmp = Integer.parseInt(soluongField.getText()) - 1;
                soluongField.setText(tmp.toString());
            }
        }
        if (e.getSource() == tangButton) {
            if (!soluongField.getText().isEmpty()) {
                Integer tmp = Integer.parseInt(soluongField.getText()) + 1;
                soluongField.setText(tmp.toString());
            }
        }
        if (e.getSource() == orderButton) {
            table.addSanPham(productDTO, Integer.parseInt(soluongField.getText()));
            soluongField.setText("1");
        }
    }
}
