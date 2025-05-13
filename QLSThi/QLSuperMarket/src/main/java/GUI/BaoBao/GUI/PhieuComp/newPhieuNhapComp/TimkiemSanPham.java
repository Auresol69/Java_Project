package GUI.BaoBao.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import GUI.BaoBao.DAO.TimkiemSanPhamDAO;
import GUI.BaoBao.BUS.ProductBUS;
import GUI.BaoBao.DAO.DataBase;
import GUI.BaoBao.DTO.ProductDTO;
import GUI.BaoBao.DTO.ProductTypeDTO;
import GUI.BaoBao.ExtendClasses.DeleteInput;
import GUI.BaoBao.ExtendClasses.ExcelImporter;
import GUI.BaoBao.ExtendClasses.MessageBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TimkiemSanPham extends JPanel implements ListSelectionListener, ActionListener {
    JTextField sortSanPham;
    JTable table;
    JScrollPane scrollPane;
    JButton addSanPham, nhapExcel;
    private Timer searchTimer;
    InputSanPham inputSanPham;
    TableSanPham tableSanPham;
    TongTien tongTien;

    private String[] columnNames = { "masp", "tensp", "soluong", "dongiasanpham", "maloaisp", "image" };
    private DefaultTableModel model;

    public TimkiemSanPham(InputSanPham inputSanPham, TableSanPham tableSanPham, TongTien tongTien) {
        this.inputSanPham = inputSanPham;
        this.tableSanPham = tableSanPham;
        this.tongTien = tongTien;

        this.setLayout(new BorderLayout());

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        loadDataToTable();

        if (table.getColumnCount() > 3) {
            table.removeColumn(table.getColumnModel().getColumn(3));
        }

        table.setAutoCreateRowSorter(true);

        // Đẩy dữ liệu vào inputSanPham
        table.getSelectionModel().addListSelectionListener(this);

        sortSanPham = new JTextField("Nhập id sản phẩm, tên sản phẩm...");
        sortSanPham.setForeground(Color.GRAY);

        // sortSanPham.addFocusListener(new FocusAdapter() {
        // @Override
        // public void focusGained(FocusEvent e) {
        // if (sortSanPham.getText().equals("Nhập id sản phẩm, tên sản phẩm...")) {
        // sortSanPham.setText("");
        // sortSanPham.setForeground(Color.BLACK);
        // }
        // }

        // @Override
        // public void focusLost(FocusEvent e) {
        // if (sortSanPham.getText().isEmpty()) {
        // sortSanPham.setText("Nhập id sản phẩm, tên sản phẩm...");
        // sortSanPham.setForeground(Color.GRAY);
        // }
        // }
        // });

        searchTimer = new Timer(300, e -> search());
        searchTimer.setRepeats(false);

        sortSanPham.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            private void restartSearchTimer() {
                if (searchTimer.isRunning())
                    searchTimer.restart();
                else
                    searchTimer.start();
            }
        });

        this.add(sortSanPham, BorderLayout.NORTH);
        this.add(getChucNang(), BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void loadDataToTable() {
        DataBase db = new DataBase();
        List<Object[]> data = db.selectQuery("SELECT * FROM product");
        model.setRowCount(0); // xoá dữ liệu cũ

        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    private void search() {
        String keyword = sortSanPham.getText().trim();
        List<ProductDTO> results = new TimkiemSanPhamDAO().searchSanPham(keyword);

        model.setRowCount(0); // xoá dữ liệu cũ

        for (ProductDTO sp : results) {
            model.addRow(new Object[] {
                    sp.getMasp(),
                    sp.getTensp(),
                    sp.getSoluong(),
                    sp.getDongiasanpham(),
                    sp.getMaloaisp() });
        }
    }

    public JPanel getChucNang() {
        JPanel chucNang = new JPanel(new FlowLayout());
        addSanPham = new JButton("Thêm sản phẩm");
        addSanPham.addActionListener(this);
        nhapExcel = new JButton("Nhập từ Excel");
        nhapExcel.addActionListener(this);
        chucNang.add(addSanPham);
        chucNang.add(nhapExcel);
        chucNang.setPreferredSize(new java.awt.Dimension(300, 50));
        return chucNang;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && e.getSource() == table.getSelectionModel()) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1 || selectedRow >= table.getRowCount())
                return;

            int modelRow = table.convertRowIndexToModel(selectedRow);

            if (!tableSanPham.isSanPhamIdExist(Integer.parseInt(model.getValueAt(selectedRow, 0).toString()))) {
                tableSanPham.getTable().getSelectionModel().removeListSelectionListener(tableSanPham);

                SwingUtilities.invokeLater(() -> {
                    tableSanPham.getTable().clearSelection();
                    tableSanPham.getTable().getSelectionModel().addListSelectionListener(tableSanPham);
                });

                if (selectedRow != -1) {
                    this.addSanPham.setEnabled(true);
                    this.nhapExcel.setEnabled(true);
                    inputSanPham.suaButton.setEnabled(false);
                    inputSanPham.xoaButton.setEnabled(false);

                    new DeleteInput(inputSanPham, tongTien).Delete();

                    inputSanPham.maSanPhamField.setText(model.getValueAt(modelRow, 0).toString());
                    inputSanPham.tenSanPhamField.setText(model.getValueAt(modelRow, 1).toString());
                    setSelectedLoaiSanPham(Integer.parseInt(model.getValueAt(modelRow, 4).toString()));
                }
            }
        }
    }

    public void setSelectedLoaiSanPham(int maLoaiDaChon) {
        for (int i = 0; i < inputSanPham.loaiSanPhamComboBox.getItemCount(); i++) {
            ProductTypeDTO item = inputSanPham.loaiSanPhamComboBox.getItemAt(i);
            if (item.getMaloaisp() == maLoaiDaChon) {
                inputSanPham.loaiSanPhamComboBox.setSelectedItem(item);
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addSanPham) {
            if (inputSanPham.maSanPhamField.getText().isBlank() ||
                    inputSanPham.tenSanPhamField.getText().isBlank() ||
                    inputSanPham.soluongSanPhamField.getText().isBlank() ||
                    inputSanPham.loaiSanPhamComboBox.getSelectedItem() == null ||
                    tongTien.gianhapField.getText().isBlank()) {
                MessageBox.showWarning("Vui lòng nhập đầy đủ thông tin sản phẩm!");
                return;
            } else {
                int masp = Integer.parseInt(inputSanPham.maSanPhamField.getText());
                String tensp = inputSanPham.tenSanPhamField.getText();
                Object selectedItem = inputSanPham.loaiSanPhamComboBox.getSelectedItem();
                if (selectedItem instanceof ProductTypeDTO) {
                    int maloaisp = ((ProductTypeDTO) selectedItem).getMaloaisp();
                    List<Object[]> data = new ProductBUS().checkSanPham(masp, tensp, maloaisp);
                    if (data.isEmpty()) {
                        MessageBox.showWarning("Không có thông tin về sản phẩm này!!");
                    } else {
                        for (Object[] row : data) {
                            tableSanPham.model.addRow(new Object[] {
                                    row[0],
                                    row[1],
                                    inputSanPham.soluongSanPhamField.getText(),
                                    inputSanPham.loaiSanPhamComboBox.getSelectedItem(),
                                    tongTien.gianhapField.getText() });
                        }
                        new DeleteInput(inputSanPham, tongTien).Delete();
                        tableSanPham.updateTongTien();
                    }
                }
            }
        }
        if (e.getSource() == nhapExcel) {
            tableSanPham.getModel().setRowCount(0);
            ExcelImporter.importExcelToJTable(tableSanPham.getTable());
        }
    }

    public JButton getAddSanPham() {
        return addSanPham;
    }

    public void setAddSanPham(JButton addSanPham) {
        this.addSanPham = addSanPham;
    }

    public JButton getNhapExcel() {
        return nhapExcel;
    }

    public void setNhapExcel(JButton nhapExcel) {
        this.nhapExcel = nhapExcel;
    }

    public JTextField getSortSanPham() {
        return sortSanPham;
    }

    public void setSortSanPham(JTextField sortSanPham) {
        this.sortSanPham = sortSanPham;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public Timer getSearchTimer() {
        return searchTimer;
    }

    public void setSearchTimer(Timer searchTimer) {
        this.searchTimer = searchTimer;
    }

    public InputSanPham getInputSanPham() {
        return inputSanPham;
    }

    public void setInputSanPham(InputSanPham inputSanPham) {
        this.inputSanPham = inputSanPham;
    }

    public TableSanPham getTableSanPham() {
        return tableSanPham;
    }

    public void setTableSanPham(TableSanPham tableSanPham) {
        this.tableSanPham = tableSanPham;
    }

    public TongTien getTongTien() {
        return tongTien;
    }

    public void setTongTien(TongTien tongTien) {
        this.tongTien = tongTien;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

}