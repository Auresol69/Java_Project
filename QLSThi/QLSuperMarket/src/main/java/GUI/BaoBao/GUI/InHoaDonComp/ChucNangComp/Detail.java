package GUI.BaoBao.GUI.InHoaDonComp.ChucNangComp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;

import GUI.BaoBao.DTO.ProductDTO;
import GUI.BaoBao.ExtendClasses.GetImagePNG;
import GUI.BaoBao.ExtendClasses.MessageBox;
import GUI.BaoBao.GUI.InHoaDonComp.Table;

public class Detail extends JDialog implements ActionListener {

    ProductDTO productDTO;
    ImageIcon icon;

    JTextField field;

    JButton luuButton;

    Table table;

    private void setUpButton(JButton button, String text, String imgName) {
        button.setText(text);

        if (!imgName.isEmpty()) {
            ImageIcon icon = new ImageIcon(new GetImagePNG().getImage(imgName, 1));
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

    public Detail(JFrame parent, ProductDTO productDTO, Table table) {
        super(parent, "Thông tin sản phẩm", true);
        this.table = table;
        this.productDTO = productDTO;
        this.setLayout(new BorderLayout());

        this.setLayout(new BorderLayout());
        this.add(new JLabel(icon = new ImageIcon(new GetImagePNG().getImage(productDTO.getImg(), 60))),
                BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Tên: " + productDTO.getTensp()));
        panel.add(Box.createVerticalStrut(8));
        panel.add(new JLabel("Loại: " + productDTO.getTenLoaiSanPham()));
        panel.add(Box.createVerticalStrut(8));
        panel.add(new JLabel("Tồn kho: " + productDTO.getSoluong().toString()));
        panel.add(Box.createVerticalStrut(8));
        panel.add(new JLabel("Đơn giá: " + productDTO.getDongiasanpham().toString() + "VND"));
        this.add(panel, BorderLayout.EAST);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel2.add(new JLabel("Số lượng: "));
        field = new JTextField("1", 5);
        field.setPreferredSize(new Dimension(80, 25));
        field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') {
                    evt.consume();
                }
            }
        });
        panel2.add(field);
        setUpButton(luuButton = new JButton(), "Lưu", "");
        luuButton.setPreferredSize(new Dimension(80, 30));
        panel2.add(luuButton);
        this.add(panel2, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == luuButton) {
            if (Integer.parseInt(field.getText()) >= 0 && !field.getText().isEmpty()) {
                this.dispose();
                table.updateSoLuong(Integer.parseInt(field.getText()), table.getTable().getSelectedRow());
            } else {
                MessageBox.showError("Số lượng không hợp lệ");
                field.setText("1");
            }
        }
    }
}