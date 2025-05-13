package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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
import javax.swing.table.TableColumnModel;

import BUS.NhomQuyenBUS;
import DTO.NhomQuyenDTO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Dialog.PhanQuyenDialog;
import GUI.QLSieuThi;
import helper.JTableExporter;

public class PhanQuyen extends JPanel implements ActionListener {

    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    JPanel main, functionBar;
    JPanel left, content;
    JTable tblNhomQuyen;
    JScrollPane scrollTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    QLSieuThi m;
    public NhomQuyenBUS nhomquyenBUS = new NhomQuyenBUS();
    public ArrayList<NhomQuyenDTO> listnhomquyen = nhomquyenBUS.getAll();

    Color BackgroundColor = new Color(255, 255, 255);

    private void initComponent() {
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        left = new JPanel();
        left.setPreferredSize(new Dimension(5, 0));
        this.add(left, BorderLayout.WEST);

        content = new JPanel();
        content.setPreferredSize(new Dimension(1100, 600));
        content.setLayout(new BorderLayout(0, 0));
        this.add(content,BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new JPanel();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new BorderLayout(0, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        functionBar.setBackground(BackgroundColor);

        String[] action = {"create", "update", "delete", "detail", "export"};
        mainFunction = new MainFunction(m.user.getPowerGroupId(), 3, action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction, BorderLayout.WEST);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ArrayList<NhomQuyenDTO> rs = nhomquyenBUS.search(search.txtSearchForm.getText());
                loadDataTalbe(rs);
            }
        });
        functionBar.add(search, BorderLayout.EAST);

        content.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new JPanel();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        content.add(main, BorderLayout.CENTER);

        tblNhomQuyen = new JTable();
        tblNhomQuyen.setDefaultEditor(Object.class, null);
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã nhóm quyền", "Tên nhóm quyền"};
        tblModel.setColumnIdentifiers(header);
        tblNhomQuyen.setModel(tblModel);
        scrollTable.setViewportView(tblNhomQuyen);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tblNhomQuyen.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(0).setPreferredWidth(2);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setPreferredWidth(300);
        tblNhomQuyen.setFocusable(false);
        tblNhomQuyen.getTableHeader().setReorderingAllowed(false); 
        main.add(scrollTable);
    }

    public PhanQuyen(QLSieuThi m) {
        this.m = m;
        initComponent();
        loadDataTalbe(listnhomquyen);
    }

    public void loadDataTalbe(ArrayList<NhomQuyenDTO> result) {
        tblModel.setRowCount(0);
        for (NhomQuyenDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getMaNhomQuyen(), ncc.getTenNhomQuyen()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            PhanQuyenDialog pq = new PhanQuyenDialog(nhomquyenBUS,this, owner, "Thêm nhóm quyền", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = this.getRowSelected();
            if (index >= 0) {
                PhanQuyenDialog nccDialog = new PhanQuyenDialog(nhomquyenBUS,this, owner, "Chỉnh sửa nhóm quyền", true, "update", listnhomquyen.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = this.getRowSelected();
            if (index >= 0) {
                PhanQuyenDialog nccDialog = new PhanQuyenDialog(nhomquyenBUS,this, owner, "Chi tiết nhóm quyền", true, "view", listnhomquyen.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = this.getRowSelected();
            if (index >= 0) {
                int input = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa nhóm quyền!", "Xóa nhóm quyền",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    nhomquyenBUS.delete(listnhomquyen.get(index));
                    loadDataTalbe(listnhomquyen);
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tblNhomQuyen);
            } catch (IOException ex) {
                Logger.getLogger(PhanQuyen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(e.getSource() == this.search.btnReset) {
            loadDataTalbe(listnhomquyen);
        }
    }

    public int getRowSelected() {
        int index = tblNhomQuyen.getSelectedRow();
        if (tblNhomQuyen.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhóm quyền");
            return -1;
        }
        return index;
    }
}
