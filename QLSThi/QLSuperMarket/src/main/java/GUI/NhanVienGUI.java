package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class NhanVienGUI extends JPanel {
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnXoa = new JButton("Xóa");
    JButton btnExport = new JButton("Xuất");
    JButton btnKhac = new JButton("Khác");
    JButton btnTuyChon = new JButton("Tùy chọn");
    JButton btnTimKiem = new JButton("Tìm kiếm");
    JTextField txtSearch = new JTextField(10);
    JTable table;
    String[] labels = {"Mã NV", "Họ Tên", "Năm Sinh", "Địa Chỉ", "Điện Thoại", "Hình Ảnh", "Email"};
    JLabel[] labelComponents = new JLabel[labels.length];
    JTextField[] textFields = new JTextField[labels.length];
    private JLabel imageLabel = new JLabel(); // đặt ngoài constructor
    public NhanVienGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        add(TopPanel(), gbc);
        gbc.gridy = 1;
        gbc.weighty = 0.6;
        add(BottomPanel(), gbc);

        addActionListener();

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting())
                chonNhanVien();
        });
    }

    public JPanel TopPanel() {
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.weightx = 0.2;
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/IMG/icons8-avatar-50.png"));
            if (icon.getImage() != null) {
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            }
        } catch (Exception e) {
            imageLabel.setText("Không có ảnh");
        }
        topPanel.add(imageLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.5;
        topPanel.add(createEmployeePanel(), gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.3;
        topPanel.add(CreateEmployeeSetting(), gbc);

        return topPanel;
    }

    public JPanel createEmployeePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            labelComponents[i] = new JLabel(labels[i] + ":");
            panel.add(labelComponents[i], gbc);

            gbc.gridx = 1;
            textFields[i] = new JTextField(20);
            panel.add(textFields[i], gbc);
            textFields[i].setEditable(false);
            textFields[i].setFocusable(false);    // Không cho focus
        }

        return panel;
    }

    public JPanel CreateEmployeeSetting() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(btnThem, gbc);
        gbc.gridx = 1;
        panel.add(btnSua, gbc);
        gbc.gridx = 2;
        panel.add(btnXoa, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(btnExport, gbc);
        gbc.gridx = 1;
        panel.add(btnKhac, gbc);
        gbc.gridx = 2;
        panel.add(btnTuyChon, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(txtSearch, gbc);
        gbc.gridx = 2; gbc.gridwidth = 1;
        panel.add(btnTimKiem, gbc);

        return panel;
    }

    public JPanel BottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        Object[][] data = {
            {"NV001", "Nguyễn Văn A", "1990", "Hà Nội", "0123456789", "avatar1.png", "a@gmail.com"},
            {"NV002", "Trần Thị B", "1995", "TP.HCM", "0987654321", "avatar2.png", "b@gmail.com"},
            {"NV003", "Lê Văn C", "1988", "Đà Nẵng", "0912345678", "avatar3.png", "c@gmail.com"},
        };
        DefaultTableModel model = new DefaultTableModel(data, labels);
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public void addActionListener() {
        btnThem.addActionListener(e -> themNhanVien());
        btnSua.addActionListener(e -> suaNhanVien());
        btnXoa.addActionListener(e -> xoaNhanVien());
        btnExport.addActionListener(e -> Export());
        btnTimKiem.addActionListener(e -> TimKiem());
    }

    public void suaNhanVien(){
        int selected = table.getSelectedRow();
        if (selected == -1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên để sửa!", "Thông báo" , JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0 ; i < labels.length; i ++){
            model.setValueAt(textFields[i].getText().trim(), selected, i);
        }
        JOptionPane.showMessageDialog(null, "Cập nhật thành công" , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public void xoaNhanVien(){
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(null,"Error! ", "NEWS", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
        JOptionPane.showMessageDialog(null,"Suuccessfull Deleted! ", "NEWS", JOptionPane.INFORMATION_MESSAGE);
    }

    public void TimKiem(){
        String search = txtSearch.getText().trim();
        if (search.isEmpty()) return;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0 ; i< table.getRowCount();i++){
            String maNV = model.getValueAt(i , 0 ).toString();
            if (maNV.equalsIgnoreCase(search)){
                table.setRowSelectionInterval(i ,i);
                return ;
            }
        }
    }
    public void Export() {
        JOptionPane.showMessageDialog(null, "Export function chưa được cài đặt");
    }

    public void chonNhanVien() {
        int selected = table.getSelectedRow();
        if (selected == -1) return;
    
        for (int i = 0; i < labels.length; i++) {
            Object value = table.getValueAt(selected, i);
            textFields[i].setText(value != null ? value.toString() : "");
        }
    
        // Load ảnh từ cột "Hình Ảnh" (index 5)
        String imageFileName = table.getValueAt(selected, 5).toString();
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(getClass().getResource("/IMG/" + imageFileName));
            if (icon.getIconWidth() == -1) throw new Exception(); // ảnh không tồn tại
        } catch (Exception e) {
            // Dùng ảnh mặc định nếu không có ảnh hoặc lỗi
            icon = new ImageIcon(getClass().getResource("/IMG/icons8-avatar-50.png"));
        }
    
        // Scale và hiển thị ảnh
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setText("");
    }

    public void themNhanVien() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] newRow = new String[labels.length];
        for (int i = 0; i < labels.length; i++) {
            newRow[i] = textFields[i].getText().trim();
        }
        model.addRow(newRow);
        JOptionPane.showMessageDialog(null, "Thêm Nhân Viên Thành Công");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý nhân viên");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new NhanVienGUI());
        frame.setVisible(true);
    }
}
