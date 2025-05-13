package GUI.Panel;

// import BUS.NhaCungCapBUS;
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

import BUS.TaiKhoanBUS;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Dialog.ListNhanVien;
import GUI.Dialog.TaiKhoanDialog;
import GUI.QLSieuThi;
import helper.JTableExporter;

public class TaiKhoan extends JPanel implements ActionListener, ItemListener {

    JPanel main, functionBar;
    JPanel left, content;
    JTable tableTaiKhoan;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    Color BackgroundColor = new Color(255, 255, 255);
    DefaultTableModel tblModel;
    public TaiKhoanBUS taiKhoanBus = new TaiKhoanBUS();
    ArrayList<TaiKhoanDTO> listTk = taiKhoanBus.getDsTaiKhoan();

    private QLSieuThi m;

    public TaiKhoan(QLSieuThi m) {
        this.m = m;
        initComponent();
        loadTable(listTk);
    }

    private void initComponent() {
        tableTaiKhoan = new JTable();
        tableTaiKhoan.setDefaultEditor(Object.class, null);
        scrollTableSanPham = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"MaNV", "Tên đăng nhập","Nhóm quyền", "Trạng thái"};
        tblModel.setColumnIdentifiers(header);
        tableTaiKhoan.setModel(tblModel);
        tableTaiKhoan.setFocusable(false);
        scrollTableSanPham.setViewportView(tableTaiKhoan);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tableTaiKhoan.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableTaiKhoan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableTaiKhoan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableTaiKhoan.getTableHeader().setReorderingAllowed(false);
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

        String[] action = {"create", "update", "delete", "detail", "export"};
        mainFunction = new MainFunction(m.user.getPowerGroupId(), 5, action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }

        functionBar.add(mainFunction);
        search = new IntegratedSearch(new String[]{"Tất cả", "Mã nhân viên", "Username"});
        search.cbxChoose.addItemListener(this);
        functionBar.add(search);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                listTk = taiKhoanBus.search(txt, type);
                loadTable(listTk);
            }
        });
        content.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new JPanel();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        content.add(main, BorderLayout.CENTER);

        main.add(scrollTableSanPham);
    }

    public void loadTable(ArrayList<TaiKhoanDTO> list) {
        tblModel.setRowCount(0);
        for (TaiKhoanDTO taiKhoanDTO : list) {
            String trangthaiString = taiKhoanDTO.getTrangThai() ? "Hoạt động" : "Ngưng hoạt động";
            tblModel.addRow(new Object[]{
            taiKhoanDTO.getMaStaff(),
            taiKhoanDTO.getUsername(),
            taiKhoanBus.getNhomQuyenDTO(taiKhoanDTO.getPowerGroupId()).getTenNhomQuyen(),
            trangthaiString
        });
        }
    }

    public int getRowSelected() {
        int index = tableTaiKhoan.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản");
        }
        return index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            ListNhanVien listNV = new ListNhanVien(this, owner, "Chọn tài khoản", true);
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                TaiKhoanDialog add = new TaiKhoanDialog(this, owner, "Cập nhật tài khoản", true, "update", listTk.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa tài khoản :)!", "Xóa xóa tài khoản",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    TaiKhoanDAO.getInstance().deleteTaiKhoan(listTk.get(index).getMaStaff());
                    loadTable(taiKhoanBus.getDsTaiKhoan());
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                TaiKhoanDialog add = new TaiKhoanDialog(this, owner, "Thêm tài khoản", true, "view", listTk.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableTaiKhoan);
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        listTk = taiKhoanBus.search(txt, type);
        loadTable(listTk);
    }

    public int taoMaTuDong() {
        ArrayList<TaiKhoanDTO> danhSach = taiKhoanBus.getDsTaiKhoan();
        int maxId = 0;
        for (TaiKhoanDTO tk : danhSach) {
            if (tk.getMaAccount() > maxId) {
                maxId = tk.getMaAccount();
            }
        }
        return maxId + 1;
    }

}
