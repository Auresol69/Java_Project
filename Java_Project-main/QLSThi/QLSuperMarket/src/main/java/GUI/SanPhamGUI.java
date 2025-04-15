package GUI;
import  DAO.ProductTypeDAO;
import  DAO.SupplierDAO;
import BUS.SanPhamBUS;
import DTO.SanPhamDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class SanPhamGUI extends JFrame {
    private SanPhamBUS spBUS = new SanPhamBUS();
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaSP, txtTenSP, txtSoLuong, txtDonGia, txtMaLoai, txtMaNCC, txtImg;
    private JTextField txtSearchMaSP, txtSearchMaLoai, txtSearchMaNSX, txtSearchMin, txtSearchMax;

    public SanPhamGUI() {
        setTitle("Quản lý Sản phẩm");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel hiển thị bảng sản phẩm
        JPanel panelTable = new JPanel(new BorderLayout());
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Mã loại", "Mã NCC", "Hình ảnh"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panelTable.add(scrollPane, BorderLayout.CENTER);

        // Panel chức năng
        JPanel panelFunctions = new JPanel(new GridLayout(2, 1));

        // Panel nhập liệu
        JPanel panelInput = new JPanel(new GridLayout(7, 2));
        panelInput.add(new JLabel("Mã SP:"));
        txtMaSP = new JTextField();
        panelInput.add(txtMaSP);

        panelInput.add(new JLabel("Tên SP:"));
        txtTenSP = new JTextField();
        panelInput.add(txtTenSP);

        panelInput.add(new JLabel("Số lượng:"));
        txtSoLuong = new JTextField();
        panelInput.add(txtSoLuong);

        panelInput.add(new JLabel("Đơn giá:"));
        txtDonGia = new JTextField();
        panelInput.add(txtDonGia);

        panelInput.add(new JLabel("Mã loại:"));
        txtMaLoai = new JTextField();
        panelInput.add(txtMaLoai);

        panelInput.add(new JLabel("Mã NCC:"));
        txtMaNCC = new JTextField();
        panelInput.add(txtMaNCC);

        panelInput.add(new JLabel("Hình ảnh:"));
        txtImg = new JTextField();
        panelInput.add(txtImg);

        panelFunctions.add(panelInput);

        // Panel nút chức năng
        JPanel panelButtons = new JPanel(new GridLayout(2, 4));
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnSearch = new JButton("Tìm kiếm");
        JButton btnExport = new JButton("Xuất Excel");
        JButton btnImport = new JButton("Nhập Excel");
        JButton btnRefresh = new JButton("Làm mới");
        JButton btnView = new JButton("Xem thông tin");

        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnSearch);
        panelButtons.add(btnExport);
        panelButtons.add(btnImport);
        panelButtons.add(btnRefresh);
        panelButtons.add(btnView);

        panelFunctions.add(panelButtons);

        // Thêm các panel vào JFrame
        add(panelTable, BorderLayout.CENTER);
        add(panelFunctions, BorderLayout.SOUTH);

        // Load dữ liệu ban đầu
        loadTableData();

        // Sự kiện nút
        btnAdd.addActionListener(e -> addSanPham());
        btnEdit.addActionListener(e -> editSanPham());
        btnDelete.addActionListener(e -> deleteSanPham());
        btnSearch.addActionListener(e -> searchSanPham());
        btnExport.addActionListener(e -> exportExcel());
        btnImport.addActionListener(e -> importExcel());
        btnRefresh.addActionListener(e -> loadTableData());
        btnView.addActionListener(e -> viewSanPham());
    }

    private void loadTableData() {
        model.setRowCount(0);
        spBUS.listSP();
        ArrayList<SanPhamDTO> list = spBUS.getList();
        for (SanPhamDTO sp : list) {
            model.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getDonGia(), sp.getMaLoai(), sp.getMaNCC(), sp.getImg()});
        }
    }
    public boolean checkMaLoai(String maloai) {
        ProductTypeDAO productTypeDAO = new ProductTypeDAO();
        return productTypeDAO.exists(maloai); // Kiểm tra mã loại trong bảng producttype
    }
    public boolean checkMaNCC(String mancc) {
        SupplierDAO supplierDAO = new SupplierDAO();
        return supplierDAO.exists(mancc); // Kiểm tra mã nhà cung cấp trong bảng supplier
    }
    private void addSanPham() {
        try {
            // Lấy dữ liệu từ giao diện
            SanPhamDTO sp = new SanPhamDTO(
                txtMaSP.getText(),
                txtTenSP.getText(),
                Integer.parseInt(txtSoLuong.getText()),
                Double.parseDouble(txtDonGia.getText()),
                txtMaLoai.getText(),
                txtMaNCC.getText(),
                txtImg.getText()
            );
    
            // Gọi phương thức thêm sản phẩm từ BUS
            boolean isAdded = spBUS.addSP(sp);
    
            // Chỉ hiển thị thông báo nếu thêm thành công
            if (isAdded) {
                loadTableData(); // Tải lại dữ liệu bảng
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng và đơn giá phải là số hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void editSanPham() {
        try {
            // Lấy dữ liệu từ giao diện
            SanPhamDTO sp = new SanPhamDTO(
                txtMaSP.getText(),
                txtTenSP.getText(),
                Integer.parseInt(txtSoLuong.getText()),
                Double.parseDouble(txtDonGia.getText()),
                txtMaLoai.getText(),
                txtMaNCC.getText(),
                txtImg.getText()
            );
    
            // Gọi phương thức sửa sản phẩm từ BUS
            boolean isUpdated = spBUS.setSP(sp);
    
            // Chỉ hiển thị thông báo nếu sửa thành công
            if (isUpdated) {
                loadTableData(); // Tải lại dữ liệu bảng
                JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa sản phẩm thất bại!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng và đơn giá phải là số hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void deleteSanPham() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String maSP = model.getValueAt(selectedRow, 0).toString();
            spBUS.deleteSP(maSP);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa!");
        }
    }

    private void searchSanPham() {
        String masp = txtSearchMaSP.getText();
        String maloai = txtSearchMaLoai.getText();
        String mansx = txtSearchMaNSX.getText();
        int min = Integer.parseInt(txtSearchMin.getText());
        int max = Integer.parseInt(txtSearchMax.getText());

        ArrayList<SanPhamDTO> searchResults = spBUS.searchSP(masp, maloai, mansx, max, min);
        model.setRowCount(0);
        for (SanPhamDTO sp : searchResults) {
            model.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getDonGia(), sp.getMaLoai(), sp.getMaNCC(), sp.getImg()});
        }
    }

    private void exportExcel() {
        spBUS.ExportExcelDatabase();
        JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!");
    }

    private void importExcel() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            spBUS.ImportExcelDatabase(file);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Nhập file Excel thành công!");
        }
    }

    private void viewSanPham() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            txtMaSP.setText(model.getValueAt(selectedRow, 0).toString());
            txtTenSP.setText(model.getValueAt(selectedRow, 1).toString());
            txtSoLuong.setText(model.getValueAt(selectedRow, 2).toString());
            txtDonGia.setText(model.getValueAt(selectedRow, 3).toString());
            txtMaLoai.setText(model.getValueAt(selectedRow, 4).toString());
            txtMaNCC.setText(model.getValueAt(selectedRow, 5).toString());
            txtImg.setText(model.getValueAt(selectedRow, 6).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xem thông tin!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SanPhamGUI().setVisible(true);
        });
    }
}