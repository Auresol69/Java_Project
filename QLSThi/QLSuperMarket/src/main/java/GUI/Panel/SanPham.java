package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Dialog.SanPhamDialog;
import GUI.QLSieuThi;
import helper.JTableExporter;

public class SanPham extends JPanel implements ActionListener, ItemListener {

    JPanel main, functionBar;
    JPanel left, content;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public SanPhamBUS sanphamBUS = new SanPhamBUS();
    public ArrayList<SanPhamDTO> listsp = sanphamBUS.getAll();
    SanPhamDTO sp = new SanPhamDTO();
    QLSieuThi m;
    Color BackgroundColor = new Color(255, 255, 255);

    private void initComponent() {
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Loại sản phẩm" };
        tblModel.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModel);
        tableSanPham.setFocusable(false);
        tableSanPham.getTableHeader().setReorderingAllowed(false); 
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < header.length; i++) {
            tableSanPham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        left = new JPanel();
        left.setPreferredSize(new Dimension(5, 0));
        this.add(left, BorderLayout.WEST);

        content = new JPanel();
        content.setPreferredSize(new Dimension(1100, 600));
        content.setLayout(new BorderLayout(0, 0));
        this.add(content, BorderLayout.CENTER);

        functionBar = new JPanel();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new BorderLayout(0, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        functionBar.setBackground(BackgroundColor);

        String[] action = {"create", "update", "delete", "detail", "export"};

        mainFunction = new MainFunction(m.user.getPowerGroupId(), 6, action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction, BorderLayout.WEST);

        search = new IntegratedSearch(new String[]{"Tất cả", "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm"});
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                listsp = sanphamBUS.search(txt, type);
                loadDataTable(listsp);
            }
        });

        search.btnReset.addActionListener((ActionEvent e) -> {
            search.txtSearchForm.setText("");
            listsp = sanphamBUS.getAll();
            loadDataTable(listsp);
        });
        functionBar.add(search, BorderLayout.EAST);

        content.add(functionBar, BorderLayout.NORTH);

        main = new JPanel();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        content.add(main, BorderLayout.CENTER);

        main.add(scrollTableSanPham);
    }

    public SanPham(QLSieuThi m) {
        this.m = m;
        initComponent();
        tableSanPham.setDefaultEditor(Object.class, null);
        loadDataTable(listsp);
    }

    public void loadDataTable(ArrayList<SanPhamDTO> result) {
        tblModel.setRowCount(0);
        for (SanPhamDTO sanPham : result) {
            tblModel.addRow(new Object[]{
                sanPham.getMasp(), sanPham.getTensp(), sanPham.getSoluong(), sanPham.getDongiasanpham(),
                sanPham.getMaloaisp()
            });
        }
    }

    public int getRowSelected() {
        int index = tableSanPham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
        }
        return index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            SanPhamDialog spDialog = new SanPhamDialog(this, owner, "Thêm sản phẩm", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                SanPhamDialog spDialog = new SanPhamDialog(this, owner, "Chỉnh sửa sản phẩm", true, "update", listsp.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa sản phẩm?", "Xóa sản phẩm",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    sanphamBUS.delete(listsp.get(index));
                    loadDataTable(listsp);
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                SanPhamDialog spDialog = new SanPhamDialog(this, owner, "Xem sản phẩm", true, "view", listsp.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableSanPham);
            } catch (IOException ex) {
                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        listsp = sanphamBUS.search(txt, type);
        loadDataTable(listsp);
    }
}
