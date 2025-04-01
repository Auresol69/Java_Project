package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class KhachHangGUI extends JPanel {
    private ImageIcon resizeIcon(String path, int width, int height) {
        java.net.URL imgUrl = getClass().getClassLoader().getResource(path);
        if (imgUrl == null) {
            System.err.println("Không tìm thấy ảnh: " + path);
            return new ImageIcon(); // Trả về icon trống nếu không tìm thấy ảnh
        }
        ImageIcon icon = new ImageIcon(imgUrl);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    JButton btnThem = new JButton("Thêm", resizeIcon("IMG/add.png", 20, 20));
    JButton btnSua = new JButton("Sửa", resizeIcon("IMG/edit.png", 20, 20));
    JButton btnXoa = new JButton("Xóa", resizeIcon("IMG/delete.png", 20, 20));
    JLabel lblSearch = new JLabel(resizeIcon("IMG/search-icon.png", 20, 20));
    JTextField txtSearch = new JTextField(15);
    JComboBox<String> cbSearch = new JComboBox<>(new String[]{"Mã KH", "Họ", "Tên", "Địa Chỉ", "Số Điện Thoại"});
    JTable table;
    String[] labels = {"Mã KH", "Họ", "Tên", "Địa Chỉ", "Số Điện Thoại"};
    JTextField[] textFields = new JTextField[labels.length];

    public KhachHangGUI() {
        setLayout(new BorderLayout());
        customizeButton(btnThem, Color.GREEN);
        customizeButton(btnSua, Color.YELLOW);
        customizeButton(btnXoa, Color.RED);

        add(TopPanel(), BorderLayout.NORTH);
        add(BottomPanel(), BorderLayout.CENTER);
        addActionListener();
    }

    private void customizeButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(120, 35));
    }

    public JPanel TopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Dòng 1: Mã KH và Số điện thoại
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(new JLabel("Mã KH:"), gbc);
        textFields[0] = new JTextField(30);
        gbc.gridx = 1;
        leftPanel.add(textFields[0], gbc);

        gbc.gridx = 2;
        leftPanel.add(new JLabel("Số Điện Thoại:"), gbc);
        textFields[4] = new JTextField(30);
        gbc.gridx = 3;
        leftPanel.add(textFields[4], gbc);

        // Dòng 2: Họ KH và Tên KH
        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(new JLabel("Họ KH:"), gbc);
        textFields[1] = new JTextField(30);
        gbc.gridx = 1;
        leftPanel.add(textFields[1], gbc);

        gbc.gridx = 2;
        leftPanel.add(new JLabel("Tên KH:"), gbc);
        textFields[2] = new JTextField(30);
        gbc.gridx = 3;
        leftPanel.add(textFields[2], gbc);

        // Dòng 3: Địa chỉ (Chiếm toàn bộ chiều rộng)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        leftPanel.add(new JLabel("Địa Chỉ:"), gbc);
        textFields[3] = new JTextField(60);
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(textFields[3], gbc);
        gbc.gridwidth = 1;

        // Dòng 4: ComboBox tìm kiếm và thanh tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchPanel.add(cbSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(lblSearch);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        leftPanel.add(searchPanel, gbc);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(btnThem, gbc);

        gbc.gridy = 1;
        rightPanel.add(btnSua, gbc);

        gbc.gridy = 2;
        rightPanel.add(btnXoa, gbc);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);

        lblSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TimKiem();
            }
        });

        return topPanel;
    }

    public JPanel BottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel(new Object[][]{
            {"KH001", "Nguyen", "An", "Ha Noi", "0123456789"},
            {"KH002", "Tran", "Binh", "TP.HCM", "0987654321"},
            {"KH003", "Le", "Cao", "Da Nang", "0912345678"}
        }, labels);
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(750, 250));
        panel.add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                for (int i = 0; i < labels.length; i++) {
                    textFields[i].setText(table.getValueAt(selectedRow, i).toString());
                }
            }
        });
        return panel;
    }

    public void addActionListener() {
        btnThem.addActionListener(e -> themKhachHang());
        btnSua.addActionListener(e -> suaKhachHang());
        btnXoa.addActionListener(e -> xoaKhachHang());
    }

    public void TimKiem() {
        String search = txtSearch.getText().trim();
        int columnIndex = cbSearch.getSelectedIndex();
        if (search.isEmpty()) return;

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            String value = model.getValueAt(i, columnIndex).toString();
            if (value.equalsIgnoreCase(search)) {
                table.setRowSelectionInterval(i, i);
                return;
            }
        }
    }

    public void themKhachHang() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] newRow = new String[labels.length];
        for (int i = 0; i < labels.length; i++) {
            newRow[i] = textFields[i].getText().trim();
        }
        model.addRow(newRow);
        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
    }

    public void suaKhachHang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng để sửa!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < labels.length; i++) {
            model.setValueAt(textFields[i].getText().trim(), selectedRow, i);
        }
        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
    }

    public void xoaKhachHang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng để xóa!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
        JOptionPane.showMessageDialog(null, "Xóa thành công");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý khách hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new KhachHangGUI());
        frame.setVisible(true);
    }
}
