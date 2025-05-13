package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.NhanVienBUS;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.QLSieuThi;

public final class NhanVien extends JPanel {

    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    NhanVienBUS nvBus = new NhanVienBUS(this);
    JPanel main, functionBar;
    JPanel left, content;
    JTable tableNhanVien;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    public IntegratedSearch search;
    QLSieuThi m;
    ArrayList<DTO.NhanVienDTO> listnv = nvBus.getAll();

    Color BackgroundColor = new Color(255, 255, 255);
    private DefaultTableModel tblModel;

    private void initComponent() {
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        left = new JPanel();
        left.setPreferredSize(new Dimension(5, 0));
        this.add(left, BorderLayout.WEST);

        content = new JPanel();
        content.setPreferredSize(new Dimension(1100, 600));
        content.setLayout(new BorderLayout(0, 0));
        this.add(content, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new JPanel();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        functionBar.setBackground(BackgroundColor);
        content.add(functionBar, BorderLayout.NORTH);

        String[] action = {"create", "update", "delete", "detail", "export"};
        mainFunction = new MainFunction((m.user.getPowerGroupId()), 2, action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(nvBus);
        }
        functionBar.add(mainFunction);
        search = new IntegratedSearch(new String[]{"Tất cả", "Họ tên" , "Mã Nhân Viên"});
        functionBar.add(search);
        search.btnReset.addActionListener(nvBus);
        search.cbxChoose.addActionListener(nvBus);
        search.txtSearchForm.getDocument().addDocumentListener(new NhanVienBUS(search.txtSearchForm, this));

        // main là phần ở dưới để thống kê bảng biểu
        main = new JPanel();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        content.add(main, BorderLayout.CENTER);

        tableNhanVien = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableNhanVien = new JTable();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã Nhân Viên", "Họ tên", "SĐT", "Địa chỉ","Trạng Thái"};

        tblModel.setColumnIdentifiers(header);
        tableNhanVien.setModel(tblModel);
        tableNhanVien.setFocusable(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tableNhanVien.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableNhanVien.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableNhanVien.getTableHeader().setReorderingAllowed(false); 
        scrollTableSanPham.setViewportView(tableNhanVien);
        main.add(scrollTableSanPham);
    }
    public NhanVien(QLSieuThi m) {
        this.m = m;
        initComponent();
        tableNhanVien.setDefaultEditor(Object.class, null);
        loadDataTalbe(listnv);
    }

    public int getRow() {
        return tableNhanVien.getSelectedRow();
    }
    public DTO.NhanVienDTO getNhanVien() {
        return listnv.get(tableNhanVien.getSelectedRow());
    }
    public void loadDataTalbe(ArrayList<DTO.NhanVienDTO> list) {
        listnv = list;
        tblModel.setRowCount(0);
        for (DTO.NhanVienDTO nhanVien : listnv) {
            tblModel.addRow(new Object[]{
                nhanVien.getMaNV(), nhanVien.getHoten(), nhanVien.getSdt(), nhanVien.getAddress(),nhanVien.getStatus() == 1 ? "Đang làm việc" : "Đã nghỉ việc"
            });
        }
    }
}
