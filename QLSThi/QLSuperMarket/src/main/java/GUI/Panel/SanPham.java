package GUI.Panel;

import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import BUS.SanPhamBUS;
import DAO.KhachHangDAO;
import DAO.SanPhamDAO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;
import GUI.Dialog.SanPhamDialog;
import GUI.QLSieuThi;
import helper.JTableExporter;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.cj.result.Row;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPham extends JPanel implements ActionListener, ItemListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
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
    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Loại sản phẩm", };
        tblModel.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModel);
        tableSanPham.setFocusable(false);
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < header.length; i++) {
            tableSanPham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tableSanPham.setAutoCreateRowSorter(true);

        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "update", "delete", "detail", "import", "export"};

        mainFunction = new MainFunction(m.user.getPowerGroupId(), 6, action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

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
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        contentCenter.add(main, BorderLayout.CENTER);

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
        } else if (e.getSource() == mainFunction.btn.get("import")) {
            importExcel();
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableSanPham);
            } catch (IOException ex) {
                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void importExcel() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        ArrayList<SanPhamDTO> listExcel = new ArrayList<SanPhamDTO>();
        JFileChooser jf = new JFileChooser();
        int result = jf.showOpenDialog(null);
        jf.setDialogTitle("Open file");
        Workbook workbook = null;
        int k = 0;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = jf.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    int check = 1;
                    XSSFRow excelRow = excelSheet.getRow(row);
                    int id = SanPhamDAO.getInstance().getAutoIncrement();
                    String tensp = excelRow.getCell(0).getStringCellValue();
                    int soluong = excelRow.getCell(1).getRowIndex();
                    int dongia = excelRow.getCell(2).getRowIndex();
                    int maloaisp = excelRow.getCell(3).getRowIndex();
                    
                    if (Validation.isEmpty(tensp) || Validation.isEmpty(String.valueOf(soluong))
                            || Validation.isEmpty(String.valueOf(dongia)) || Validation.isEmpty(String.valueOf(maloaisp))) {
                        check = 0;
                    }
                    if (check == 1) {
                        sanphamBUS.add(new SanPhamDTO(id, tensp, soluong, dongia, maloaisp));
                    } else {
                        k += 1;
                    }
                }
                JOptionPane.showMessageDialog(this, "Nhập thành công");
            } catch (FileNotFoundException ex) {
                System.out.println("Lỗi đọc file");
            } catch (IOException ex) {
                System.out.println("Lỗi đọc file");
            }
        }
        if (k != 0) {
            JOptionPane.showMessageDialog(this, "Những dữ liệu không hợp lệ không được thêm vào");
        }
        loadDataTable(listsp);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        listsp = sanphamBUS.search(txt, type);
        loadDataTable(listsp);
    }
}
