package com.sieuthimini.GUI.InHoaDonComp.ChucNangComp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sieuthimini.BUS.PayByBUS;
import com.sieuthimini.ExtendClasses.MessageBox;
import com.sieuthimini.GUI.InHoaDonComp.ChucNang;

// Hiển thị tổng tiền.

// Nhập số tiền khách đưa.

// Tự động tính tiền thối lại.

// Nút “Xác nhận thanh toán”.

// Thông báo “Thanh toán tiền mặt thành công”.

public class TienMat extends JDialog implements ActionListener {

    JTextField tongField, nhanField, hoanField;
    JButton button;

    ChucNang chucNang;

    public TienMat(JFrame parent, ChucNang chucNang, Integer tongTien) {
        super(parent, "Thanh toán tiền mặt", true);

        this.chucNang = chucNang;

        this.setLayout(new BorderLayout());

        JPanel title = new JPanel(new FlowLayout());
        title.add(new JLabel("Welcome <3"));

        this.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(new JLabel("Tổng tiền: "));
        panel1.add(tongField = new JTextField(tongTien.toString()));
        panel1.add(new JLabel(" (VNĐ)"));
        tongField.setEditable(false);
        panel.add(panel1);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(new JLabel("Đã nhận: "));
        panel2.add(nhanField = new JTextField(15));
        panel2.add(new JLabel(" (VNĐ)"));
        panel.add(panel2);

        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.add(new JLabel("Hoàn lại: "));
        panel3.add(hoanField = new JTextField(15));
        panel3.add(new JLabel(" (VNĐ)"));
        tongField.setEditable(false);
        panel.add(panel3);

        this.add(panel, BorderLayout.CENTER);

        nhanField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String input = nhanField.getText();
                if (!input.isEmpty() && Integer.parseInt(input) >= Integer.parseInt(tongField.getText())) {
                    Integer conlai = Integer.parseInt(input) - Integer.parseInt(tongField.getText());

                    hoanField.setText(conlai.toString());
                } else {
                    MessageBox.showError("Hãy nhập số tiền hợp lệ");
                    nhanField.setText(tongTien.toString());
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        button = new JButton("Thanh toán");
        buttonPanel.add(button);

        panel.add(buttonPanel);

        button.addActionListener(this);

        this.pack();
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            this.dispose();
            try {
                String details = tongField.getText() + "&" + nhanField.getText() + "&" + hoanField.getText();
                Integer mapayBy = new PayByBUS().createPayBy("Tiền mặt", details);
                if (mapayBy != null && mapayBy != -1) {
                    chucNang.setMaPayBy(mapayBy);
                    MessageBox.showInfo("Thanh toán tiền mặt thành công");
                } else {
                    MessageBox.showError("Thanh toán thất bại");
                }
            } catch (NumberFormatException ex) {
                MessageBox.showError("Lỗi định dạng số: " + ex.getMessage());
            }
        }
    }
}
