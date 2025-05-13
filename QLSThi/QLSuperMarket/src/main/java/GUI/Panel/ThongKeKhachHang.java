package GUI.Panel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import org.jfree.data.category.CategoryDataset;

import BUS.ThongKeBUS;
import GUI.QLSieuThi;
import DAO.ThongKeDAO;
import DTO.KhachHangDTO;
import DTO.ThongKeKhachHangDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class ThongKeKhachHang extends JPanel implements ActionListener{
    Color backgroundcolor = new Color(240, 247, 250);
    public JPanel ct, tongquan, filterPanel, detail;
    private JLabel lblDoanhThu, lblTongVon, lblTraHang, lblLoiNhuan;
    public ChartPanel chartPanel;
    private JTextField dateStart, dateEnd, tfCongTy, tfsrc;
    private JButton btnSearch ;
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private JScrollPane scrolltableThongKe;
    private JTable tableThongKe;
    private DefaultTableModel  tblModel;
    public QLSieuThi st;
    public Date selectedStartDate , selectedEndDate; 
    public JTable tableThongKeTime;
    public JDateChooser dateStartChooser , dateEndChooser;
    private JComboBox<String> cbLoaiThoiGian, cbLocTatCa;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public String startStr , endStr;
    public JButton btntongquan = new JButton("Tổng Quan");
    public JButton btnChitiet = new JButton ("Chi Tiết");
    public CategoryDataset dataset ;
    public JFreeChart chart ;
    public ThongKeBUS thongkeBUS = new ThongKeBUS();
    public ArrayList<ThongKeKhachHangDTO> tkKH ;
    Font font = new Font("Segoe UI", Font.BOLD, 14);
    public ThongKeKhachHang(QLSieuThi st){
        this.st = st;
        init();
    }
    public void init(){
        this.setLayout( new GridLayout(0,1));
        filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Loại thời gian
        gbc.gridx = 0;
        gbc.gridy = 0;
        filterPanel.add(new JLabel("Loại thời gian"), gbc);
        gbc.gridx = 1;
        cbLoaiThoiGian = new JComboBox<>(new String[] {"Báo cáo theo ngày"});
        cbLoaiThoiGian.setPreferredSize(new Dimension(160, 25));
        filterPanel.add(cbLoaiThoiGian, gbc);
        // Công ty
        gbc.gridx = 2;
        filterPanel.add(new JLabel("Siêu thị"), gbc);
        gbc.gridx = 3;
        tfCongTy = new JTextField(15);
        tfCongTy.setText("Siêu thị Mini");
        tfCongTy.setEditable(false);
        filterPanel.add(tfCongTy, gbc);
        // Lọc tất cả
        gbc.gridx = 4;
        filterPanel.add(new JLabel("Lọc tất cả"), gbc);
        gbc.gridx = 5;
        cbLocTatCa = new JComboBox<>(new String[] {"Tất cả", "Theo mã", "Theo tên", "Theo ngày mua"});
        filterPanel.add(cbLocTatCa, gbc);
        // Ngày bắt đầu
        gbc.gridx = 0;
        gbc.gridy = 1;
        filterPanel.add(new JLabel("Ngày bắt đầu"), gbc);

        gbc.gridx = 1;
        dateStartChooser = new JDateChooser();
        dateStartChooser.setDateFormatString("dd/MM/yyyy"); // định dạng hiển thị
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, - 8);
        dateStartChooser.setDate(cal.getTime());
        JTextField textField = (JTextField) dateStartChooser.getDateEditor().getUiComponent();
        textField.setEditable(false);
        filterPanel.add(dateStartChooser, gbc);

        // Ngày kết thúc
        gbc.gridx = 2;
        filterPanel.add(new JLabel("Ngày kết thúc"), gbc);

        gbc.gridx = 3;
        dateEndChooser = new JDateChooser();
        dateEndChooser.setDateFormatString("dd/MM/yyyy");
        dateEndChooser.setDate(new Date());
        JTextField textFieldEnd = (JTextField) dateEndChooser.getDateEditor().getUiComponent();
        textFieldEnd.setEditable(false);
        filterPanel.add(dateEndChooser, gbc);
        
        gbc.gridx = 4;
        filterPanel.add(new JLabel("Tìm Kiếm"), gbc);

        gbc.gridx = 5;
        tfsrc = new JTextField(15);
        filterPanel.add(tfsrc, gbc);
        tfsrc.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSearch();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        btnSearch = new JButton("Tìm kiếm");
        btnSearch.setBackground(new Color(90, 75, 180));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);


        detail = new JPanel();
        detail.setLayout(new GridLayout(1,4,10,0));
        detail.setBackground(Color.WHITE);
        detail.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        lblDoanhThu = new JLabel("0");
        lblTongVon = new JLabel("0");
        lblTraHang = new JLabel("0");
        lblLoiNhuan = new JLabel("0");

        detail.add(createStatBox("Doanh thu", lblDoanhThu, backgroundcolor, "💲"));
        detail.add(createStatBox("Tổng vốn", lblTongVon, backgroundcolor, "💲"));
        detail.add(createStatBox("Trả hàng", lblTraHang, Color.GREEN, "↩"));
        detail.add(createStatBox("Lợi nhuận", lblLoiNhuan, backgroundcolor, "💵"));

        filterPanel.add(btnSearch, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        filterPanel.add(detail,gbc);
        add(filterPanel, BorderLayout.NORTH);

        tableThongKeTime = new JTable();
        scrolltableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        tongquan = new JPanel();
        tongquan.setLayout(new BorderLayout());
        tongquan.setBackground(backgroundcolor);
        btntongquan.setBackground(backgroundcolor); 
        btnChitiet.setBackground(backgroundcolor); 
        btntongquan.setForeground(Color.BLACK);
        btnChitiet.setForeground(Color.BLACK);
        Font font = new Font("Segoe UI", Font.BOLD, 14);
        btntongquan.setFont(font);
        btnChitiet.setFont(font);
        btntongquan.setFocusPainted(false);
        btnChitiet.setFocusPainted(false);
        btntongquan.addActionListener(e -> show("TongQuan"));
        btnChitiet.addActionListener(e -> show("Chitiet"));
        JPanel btn = new JPanel();
        btn.setLayout(new FlowLayout(FlowLayout.LEFT));
        btn.setBackground(backgroundcolor);
        btn.add(btntongquan);
        btn.add(btnChitiet);
        tongquan.add(btn, BorderLayout.NORTH);
        ct = new JPanel();
        ct.setLayout(new BorderLayout());
        tableThongKe = new JTable(tblModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tableThongKe.getColumnCount(); i++) {
            tableThongKe.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        scrolltableThongKe = new JScrollPane(tableThongKe);
        ct.add(scrolltableThongKe, BorderLayout.CENTER);
        selectedStartDate = dateStartChooser.getDate();
        selectedEndDate = dateEndChooser.getDate();
        startStr = sdf.format(selectedStartDate);
        endStr = sdf.format(selectedEndDate);
        dataset = createDataset( startStr, endStr);
        chart = ChartFactory.createBarChart(
            "Lợi nhuận cao nhất",       // Tiêu đề biểu đồ
            "Khách Hàng",                // Nhãn trục X (sẽ thành trục Y nếu nằm ngang)
            "Lợi nhuận",                // Nhãn trục Y (sẽ thành trục X nếu nằm ngang)
            dataset,                    // Dữ liệu
            PlotOrientation.HORIZONTAL, // 👉 CHUYỂN TỪ VERTICAL -> HORIZONTAL
            true,                       // Hiển thị chú thích
            true,                       // Tooltips
            false                       // URLs
        );
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Định dạng giá trị trục X (trục ngang - lợi nhuận) thành tiền VND
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));

        // Set màu xanh cho cột
        renderer.setSeriesPaint(0, new Color(30, 120, 210)); // Xanh dương

        // Font cho trục và tiêu đề
        Font axisFont = new Font("SansSerif", Font.PLAIN, 12);
        Font titleFont = new Font("SansSerif", Font.BOLD, 16);
        chart.getTitle().setFont(titleFont);
        plot.getDomainAxis().setTickLabelFont(axisFont); // Tên KH
        plot.getRangeAxis().setTickLabelFont(axisFont);  // Lợi nhuận

        // Hiển thị label trên mỗi cột
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font("SansSerif", Font.BOLD, 11));
        chartPanel = new ChartPanel(chart);
        mainPanel.add(ct,"Chitiet");
        mainPanel.add(chartPanel,"TongQuan");
        tongquan.add(mainPanel,BorderLayout.CENTER);
        add(tongquan,BorderLayout.SOUTH);
        show("TongQuan");
        btnSearch.addActionListener(e->loadThongKe());
        cbLoaiThoiGian.addActionListener(e->{
            String selected = (String) cbLoaiThoiGian.getSelectedItem();
            if("Báo cáo theo ngày".equals(selected)) {
                dateStartChooser.setDateFormatString("dd/MM/yyyy");
                dateEndChooser.setDateFormatString("dd/MM/yyyy");
            } else if ("Báo cáo theo tháng".equals(selected)){
                dateStartChooser.setDateFormatString("MM/yyyy");
                dateEndChooser.setDateFormatString("MM/yyyy");
            } else if("Báo cáo theo năm".equals(selected)){
                dateStartChooser.setDateFormatString("yyyy");
                dateEndChooser.setDateFormatString("yyyy");
            }
        });
    }
    private void loadThongKe(){
        String loai = getLoaiThoiGian();
        Date startDate = dateStartChooser.getDate();
        Date endDate = dateEndChooser.getDate();
        if(startDate != null || endDate != null){
            if( startDate.after(endDate)){
                JOptionPane.showMessageDialog(this,"Ngày kết thúc phải lớn hơn ngày bắt đầu","Lỗi",JOptionPane.ERROR_MESSAGE);
            } else {
                SimpleDateFormat customFormat;
                if (loai.equals("Báo cáo theo ngày")) {
                    customFormat = new SimpleDateFormat("yyyy-MM-dd");
                } else if (loai.equals("Báo cáo theo tháng")) {
                    customFormat = new SimpleDateFormat("yyyy-MM");
                } else {
                    customFormat = new SimpleDateFormat("yyyy");
                }
                startStr = customFormat.format(startDate);
                endStr = customFormat.format(endDate);
                CategoryDataset newDataset = createDataset(startStr, endStr);
                chart.getCategoryPlot().setDataset(newDataset); // chỉ cần cập nhật dataset
            }
        } else {
            JOptionPane.showMessageDialog(this,"Vui lòng chọn ngày tháng hợp lệ.","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private void show(String name) {
        cardLayout.show(mainPanel, name);
        if(name.equals("TongQuan")){
            btntongquan.setBackground(Color.WHITE);
            btnChitiet.setBackground(backgroundcolor);
        } else {
            btnChitiet.setBackground(Color.WHITE);
            btntongquan.setBackground(backgroundcolor);
        }
    }
    public String getStartDate() {
        return dateStart.getText();
    }

    public String getEndDate() {
        return dateEnd.getText();
    }

    public JButton getSearchButton() {
        return btnSearch;
    }
    public String getLoaiThoiGian() {
        return (String) cbLoaiThoiGian.getSelectedItem();
    }

    public String getCongTy() {
        return tfCongTy.getText();
    }

    public String getLocTatCa() {    
        return (String) cbLocTatCa.getSelectedItem();
    }     
    private void updateChiTietTable() {
        String loai = getLoaiThoiGian();
        SimpleDateFormat customFormat;
        
        // Xác định định dạng ngày theo loại thời gian
        if (loai.equals("Báo cáo theo ngày")) {
            customFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else if (loai.equals("Báo cáo theo tháng")) {
            customFormat = new SimpleDateFormat("yyyy-MM");
        } else {
            customFormat = new SimpleDateFormat("yyyy");
        }
        // Tạo model mới cho bảng
        String[] colNames = {"Ngày","Mã Khách Hàng","Tên Khách Hàng","Bán Hàng", "Tiền vốn","Doanh Thu", "Lợi nhuận"};
        DefaultTableModel model = new DefaultTableModel(colNames, 0);

        // Đổ dữ liệu theo loại báo cáo
        if (loai.equals("Báo cáo theo ngày") && tkKH != null) {
            for (ThongKeKhachHangDTO t :tkKH) {
                String ngay = customFormat.format(t.getNgay());
                Object[] row = {
                    ngay,
                    t.getMaKhachHang(),
                    t.getHoTen(),
                    String.format("%,d", t.getDoanhThu()),
                    String.format("%,d",0),
                    String.format("%,d", t.getDoanhThu()),
                    String.format("%,d", t.getLoiNhuan())
                };
                model.addRow(row);
            }
        }
            tableThongKe.setModel(model);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < tableThongKe.getColumnCount(); i++) {
                tableThongKe.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

    public JPanel createStatBox(String title, JLabel valueLabel, Color color, String iconText) {
        JPanel box = new JPanel();
        box.setBackground(color.WHITE);
        box.setLayout(new BorderLayout(10, 0));
        box.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    
        JLabel icon = new JLabel(iconText, SwingConstants.CENTER);
        icon.setOpaque(true);
        icon.setBackground(color);
        if (color.equals(Color.GREEN)) {
            icon.setForeground(Color.WHITE);
        }
        icon.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        icon.setPreferredSize(new Dimension(40, 40));
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setVerticalAlignment(SwingConstants.CENTER);
    
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        valueLabel.setForeground(color.GRAY);
    
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titleLabel.setForeground(Color.GRAY);
    
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(valueLabel);
        content.add(titleLabel);
    
        box.add(icon, BorderLayout.WEST);
        box.add(content, BorderLayout.CENTER);
    
        return box;
    }
    private CategoryDataset createDataset(String startStr, String endStr){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Calendar cal = Calendar.getInstance();
        int tongDoanhThu = 0;
        int tongChiPhi = 0;
        int tongTraHang = 0;
        int tongLoiNhuan = 0;
        int revenue = 0; // 30–60
        int cost = 0;     // 5–20
        try {
            String loai = getLoaiThoiGian();
                if (loai.equals("Báo cáo theo ngày")) {
                    // customFormat = new SimpleDateFormat("yyyy-MM-dd");
                    tkKH = thongkeBUS.getAllKH(startStr, endStr);
                    Map <String , Integer>tkTQ = thongkeBUS.thongKeTongQuat(tkKH);
                    for (ThongKeKhachHangDTO kh  : tkKH) {
                        tongDoanhThu += kh.getDoanhThu();
                        tongLoiNhuan += kh.getLoiNhuan();
                    }
                    for (Map.Entry<String,Integer> entry : tkTQ.entrySet() ){
                        String tenkh = entry.getKey();
                        int tongLN = entry.getValue();
                        dataset.addValue(tongLN, tenkh, "Lợi nhuận");
                    }
                    lblDoanhThu.setText(String.format("%,d", tongDoanhThu));
                    lblTongVon.setText(String.format("%,d", tongChiPhi));
                    lblTraHang.setText(String.format("%,d", tongTraHang));
                    lblLoiNhuan.setText(String.format("%,d", tongLoiNhuan));
                    updateChiTietTable();
                }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }
    
     private void updateSearch() {
        selectedStartDate = dateStartChooser.getDate();
        selectedEndDate = dateEndChooser.getDate();
        startStr = sdf.format(selectedStartDate);
        endStr = sdf.format(selectedEndDate);
        String keyword = tfsrc.getText().trim().toLowerCase();

        if (keyword.isEmpty() || keyword.equals("tất cả")) {
            ArrayList<ThongKeKhachHangDTO> all = thongkeBUS.getAllKH(startStr, endStr);
            tkKH = new ArrayList<>();
            for (ThongKeKhachHangDTO t : all) {
                if(t.getHoTen() == null || t.getMaKhachHang()==0) continue;
                if (String.valueOf(t.getMaKhachHang()).contains(keyword) || t.getHoTen().contains(keyword) ) {
                    tkKH.add(t);
                }
            }
        } else if (getLocTatCa().equals("Theo mã")) {
            ArrayList<ThongKeKhachHangDTO> all = thongkeBUS.getAllKH(startStr, endStr);
            tkKH = new ArrayList<>();
            for (ThongKeKhachHangDTO t : all) {
                if(t.getMaKhachHang()==0) continue;
                if (String.valueOf(t.getMaKhachHang()).contains(keyword)) {
                    tkKH.add(t);
                }
            }
        } else if (getLocTatCa().equals("Theo tên")) {
            ArrayList<ThongKeKhachHangDTO> all = thongkeBUS.getAllKH(startStr, endStr);
            tkKH = new ArrayList<>();
            for (ThongKeKhachHangDTO t : all) {
                if(t.getHoTen() == null || t.getMaKhachHang()==0) continue;
                if (t.getHoTen().contains(keyword) ) {
                    tkKH.add(t);
                }
            }
        }
        else if (getLocTatCa().equals("Theo ngày mua")) {
            ArrayList<ThongKeKhachHangDTO> all = thongkeBUS.getAllKH(startStr, endStr);
            tkKH = new ArrayList<>();
            for (ThongKeKhachHangDTO t : all) {
                String dateStr = sdf.format(t.getNgay());
                if (dateStr.contains(keyword) ) {
                    tkKH.add(t);
                }
            }
        }
        updateChiTietTable();
    }
     @Override
     public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
     }
}
