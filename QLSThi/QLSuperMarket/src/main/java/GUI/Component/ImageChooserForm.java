package GUI.Component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ImageChooserForm extends JPanel implements ActionListener {
    private JLabel lblImage;
    private JButton btnChooseImage;
    private String imagePath = "IMG/layers.png";

    public ImageChooserForm() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(200, 250));
        // Label hiển thị ảnh
        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(70, 70));
        lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));

        // Nút chọn ảnh
        btnChooseImage = new JButton("Chọn ảnh");
        btnChooseImage.addActionListener(this);
        btnChooseImage.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Thêm các thành phần vào form
        this.add(lblImage);
        this.add(btnChooseImage);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        lblImage.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
    }
    public void setEnable(boolean enabled) {
        // Bật/tắt JLabel hiển thị ảnh
        btnChooseImage.setEnabled(enabled); // Bật/tắt nút chọn ảnh
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnChooseImage) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePath = selectedFile.getAbsolutePath();
                lblImage.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            }
        }
    }
}
