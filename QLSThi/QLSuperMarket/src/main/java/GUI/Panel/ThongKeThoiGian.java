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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import org.jfree.data.category.CategoryDataset;

import BUS.ThongKeBUS;
import DTO.ThongKeTheoTungNgay;
import DTO.ThongKeTheoThang;
import DTO.ThongKeKhachHangDTO;
import DTO.ThongKeTheoNam;
import GUI.QLSieuThi;
import DAO.ThongKeDAO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class ThongKeThoiGian extends JPanel {
    Color backgroundcolor = new Color(240, 247, 250);
    public JPanel ct, tongquan, filterPanel, detail;
    private JLabel lblDoanhThu, lblTongVon, lblTraHang, lblLoiNhuan;
    public ChartPanel chartPanel;
    private JTextField dateStart, dateEnd, tfCongTy,tfsrc;
    private JButton btnSearch ;
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private JScrollPane scrolltableThongKe;
    private JTable tableThongKe;
    private DefaultTableModel  tblModel;
    private String[] columnNames = {
        "Mã hóa đơn", "Tên khách hàng", "Ngày mua",
        "Sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền"
    };
    private Object[][] data = {
        {"HD001", "Nguyễn Văn A", "2025-05-01", "Áo thun", 2, 150000, 300000},
        {"HD002", "Trần Thị B", "2025-05-02", "Quần jeans", 1, 350000, 350000},
        {"HD003", "Lê Văn C", "2025-05-02", "Giày thể thao", 1, 700000, 700000}
    };
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
    public ArrayList<ThongKeTheoTungNgay> thongkeTheoNgay;
    public ArrayList<ThongKeTheoThang> thongkeTheoThang;
    Font font = new Font("Segoe UI", Font.BOLD, 14);
    public ThongKeThoiGian(QLSieuThi st){
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
        cbLoaiThoiGian = new JComboBox<>(new String[] {"Báo cáo theo ngày", "Báo cáo theo tháng"});
        cbLoaiThoiGian.setPreferredSize(new Dimension(160, 25));
        filterPanel.add(cbLoaiThoiGian, gbc);
        cbLoaiThoiGian.addActionListener(e -> {
            String selected = (String) cbLoaiThoiGian.getSelectedItem();
            capNhatLocTatCa(selected);
        });
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
        cbLocTatCa = new JComboBox<>(new String[] {"Tất cả", "Theo ngày mua","Theo tháng","Theo lợi nhuận","Theo doanh thu","Theo chi phí"});
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
        // Nút tìm kiếm
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
        tblModel = new DefaultTableModel(data, columnNames);
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
                "Doanh Thu và Vốn theo Thời Gian", // tiêu đề
                "Thời gian", // nhãn trục X
                "Giá trị (VND)", // nhãn trục Y
                dataset, // dữ liệu
                PlotOrientation.VERTICAL, // hướng biểu đồ
                true, // hiển thị chú giải
                true, // hiển thị thông tin tooltip
                false // không hiển thị URL
        );
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
        DefaultTableModel model ;
        if (loai.equals("Báo cáo theo ngày") && thongkeTheoNgay != null) {
            String[] colNames = {"Ngày", "Doanh thu", "Tiền vốn", "Lợi nhuận"};
             model = new DefaultTableModel(colNames, 0);
            for (ThongKeTheoTungNgay t : thongkeTheoNgay) {
                String ngay = customFormat.format(t.getDate());
                Object[] row = {
                    ngay,
                    String.format("%,d", t.getDoanhthu()),
                    String.format("%,d", t.getChiphi()),
                    String.format("%,d", t.getLoinhuan())
                };
                model.addRow(row);
            }
                    tableThongKe.setModel(model);
        } else if (loai.equals("Báo cáo theo tháng") && thongkeTheoThang != null) {
            String[] colNames = {"Tháng", "Doanh thu", "Tiền vốn", "Lợi nhuận"};
            model = new DefaultTableModel(colNames, 0);
            for (ThongKeTheoThang t : thongkeTheoThang) {
                int thang = t.getThang(); // dạng "yyyy-MM"
                Object[] row = {
                    thang,
                    String.format("%,d", t.getDoanhthu()),
                    String.format("%,d", t.getChiphi()),
                    String.format("%,d", t.getLoinhuan())
                };
                model.addRow(row);
            }
                    tableThongKe.setModel(model);
        } 

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
            SimpleDateFormat customFormat;
                if (loai.equals("Báo cáo theo ngày")) {
                    customFormat = new SimpleDateFormat("yyyy-MM-dd");
                    thongkeTheoNgay = thongkeBUS.getThongKeTheoTuNgayDenNgay(startStr, endStr);
                    Date startDate = customFormat.parse(startStr);
                    Date endDate = customFormat.parse(endStr); 
                    long diffMillis = endDate.getTime() - startDate.getTime();
                    long diffDays = TimeUnit.DAYS.convert(diffMillis,TimeUnit.MILLISECONDS);
                    if (diffDays < 7){
                        Calendar tempCal = Calendar.getInstance();
                        tempCal.setTime(endDate);
                        tempCal.add(Calendar.DATE,(int)(6 - diffDays));
                        endDate = tempCal.getTime();
                    }
                    cal.setTime(startDate);
                    while (!cal.getTime().after(endDate)) {
                        String dateLabel = customFormat.format(cal.getTime());
                        Date currentDate = cal.getTime();

                        ThongKeTheoTungNgay thongke = thongkeTheoNgay.stream()
                        .filter(t-> customFormat.format(t.getDate()).equals(dateLabel))
                        .findFirst()
                        .orElse(null);
                        if(thongke != null){
                            revenue = thongke.getDoanhthu();
                            cost = thongke.getChiphi();
                        }
                        dataset.addValue(revenue, "Doanh thu", dateLabel);
                        dataset.addValue(cost, "Tiền vốn", dateLabel);
        
                        // Tăng ngày lên 1
                        cal.add(Calendar.DATE, 1);
                    }
                    for (ThongKeTheoTungNgay t : thongkeTheoNgay) {
                        tongDoanhThu += t.getDoanhthu();
                        tongChiPhi += t.getChiphi();
                        tongLoiNhuan += t.getLoinhuan();
                    }
                    lblDoanhThu.setText(String.format("%,d", tongDoanhThu));
                    lblTongVon.setText(String.format("%,d", tongChiPhi));
                    lblTraHang.setText(String.format("%,d", tongTraHang));
                    lblLoiNhuan.setText(String.format("%,d", tongLoiNhuan));
                    updateChiTietTable();
                } else if (loai.equals("Báo cáo theo tháng")) {
                    customFormat = new SimpleDateFormat("yyyy-MM");
                    DateTimeFormatter ymFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
                    YearMonth startYM = YearMonth.parse(startStr,ymFormatter);
                    YearMonth endYM = YearMonth.parse(endStr,ymFormatter);

                    int monthBettween = (endYM.getYear() - startYM.getYear());
                    if(monthBettween < 11){
                        endYM = startYM.plusMonths(11);
                        endStr = endYM.toString();
                    }
                    thongkeTheoThang = thongkeBUS.getThongKeTheoThang( startStr, endStr);
                    Date startDate = customFormat.parse(startStr);
                    Date endDate = customFormat.parse(endStr); 
                    cal.setTime(startDate);
                    int nam = startYM.getYear() ;
                    int Thangcu = startYM.getMonthValue();
                    for (int i = 0 ; i < thongkeTheoThang.size(); i++){
                        ThongKeTheoThang tk = thongkeTheoThang.get(i);
                        if (tk.getThang() == 1 && Thangcu == 12){
                            nam += 1;
                        }
                        Thangcu = tk.getThang();
                        String thang = String.valueOf(tk.getThang())+"/"+String.valueOf(nam);
                        dataset.addValue(tk.getDoanhthu(),"Doanh thu", thang);
                        dataset.addValue(tk.getChiphi(),"Tiền vốn", thang);
                        tongDoanhThu += tk.getDoanhthu();
                        tongLoiNhuan += tk.getLoinhuan();
                        tongChiPhi += tk.getChiphi();
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
        String loai = getLoaiThoiGian();
        selectedStartDate = dateStartChooser.getDate();
        selectedEndDate = dateEndChooser.getDate();
        startStr = sdf.format(selectedStartDate);
        endStr = sdf.format(selectedEndDate);
        String keyword = tfsrc.getText().trim().toLowerCase();
        if (loai.equals("Báo cáo theo ngày")) {
            if (getLocTatCa().equals("Tất cả")) {
                ArrayList<ThongKeTheoTungNgay> tktmp = thongkeBUS.getThongKeTheoTuNgayDenNgay(startStr, endStr);
                thongkeTheoNgay = new ArrayList<>();
                for (ThongKeTheoTungNgay t : tktmp){
                    String dateStr = sdf.format(t.getDate());
                    if(dateStr.contains(keyword) || String.valueOf(t.getDoanhthu()).contains(keyword) || String.valueOf(t.getLoinhuan()).contains(keyword)){
                        thongkeTheoNgay.add(t);
                    }
                }
            }
            if (getLocTatCa().equals("Theo ngày mua")) {
                ArrayList<ThongKeTheoTungNgay> tktmp = thongkeBUS.getThongKeTheoTuNgayDenNgay(startStr, endStr);
                thongkeTheoNgay = new ArrayList<>();
                SimpleDateFormat spd = new SimpleDateFormat("dd");
                for (ThongKeTheoTungNgay t : tktmp){
                    String dateStr = spd.format(t.getDate());
                    if(dateStr.contains(keyword)){
                        thongkeTheoNgay.add(t);
                    }
                }
            }
            if (getLocTatCa().equals("Theo tháng")) {
                ArrayList<ThongKeTheoTungNgay> tktmp = thongkeBUS.getThongKeTheoTuNgayDenNgay(startStr, endStr);
                thongkeTheoNgay = new ArrayList<>();
                SimpleDateFormat spd = new SimpleDateFormat("MM");
                for (ThongKeTheoTungNgay t : tktmp){
                    String dateStr = spd.format(t.getDate());
                    if(dateStr.contains(keyword)){
                        thongkeTheoNgay.add(t);
                    }
                }
            }
            if (getLocTatCa().equals("Theo lợi nhuận")) {
                ArrayList<ThongKeTheoTungNgay> tktmp = thongkeBUS.getThongKeTheoTuNgayDenNgay(startStr, endStr);
                thongkeTheoNgay = new ArrayList<>();
                for (ThongKeTheoTungNgay t : tktmp){
                    if(String.valueOf(t.getLoinhuan()).contains(keyword)){
                        thongkeTheoNgay.add(t);
                    }
                }
            }
            if (getLocTatCa().equals("Theo doanh thu")) {
                ArrayList<ThongKeTheoTungNgay> tktmp = thongkeBUS.getThongKeTheoTuNgayDenNgay(startStr, endStr);
                thongkeTheoNgay = new ArrayList<>();
                for (ThongKeTheoTungNgay t : tktmp){
                    if(String.valueOf(t.getDoanhthu()).contains(keyword)){
                        thongkeTheoNgay.add(t);
                    }
                }
            }
            if (getLocTatCa().equals("Theo chi phí")) {
                ArrayList<ThongKeTheoTungNgay> tktmp = thongkeBUS.getThongKeTheoTuNgayDenNgay(startStr, endStr);
                thongkeTheoNgay = new ArrayList<>();
                for (ThongKeTheoTungNgay t : tktmp){
                    if(String.valueOf(t.getChiphi()).contains(keyword)){
                        thongkeTheoNgay.add(t);
                    }
                }
            }
        } else if(loai.equals("Báo cáo theo tháng")){
            SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
            startStr = sdfMonth.format(selectedStartDate);
            endStr = sdfMonth.format(selectedEndDate);
            if(getLocTatCa().equals("Theo tháng")){
                ArrayList<ThongKeTheoThang> tktmp = thongkeBUS.getThongKeTheoThang(startStr, endStr);
                thongkeTheoThang = new ArrayList<>();
                for (ThongKeTheoThang t : tktmp){
                    if(String.valueOf(t.getThang()).contains(keyword)){
                        thongkeTheoThang.add(t);
                    }
                }
            }
            if(getLocTatCa().equals("Tất cả")){
                ArrayList<ThongKeTheoThang> tktmp = thongkeBUS.getThongKeTheoThang(startStr, endStr);
                thongkeTheoThang = new ArrayList<>();
                for (ThongKeTheoThang t : tktmp){
                    if(String.valueOf(t.getThang()).contains(keyword) || String.valueOf(t.getDoanhthu()).contains(keyword) || String.valueOf(t.getLoinhuan()).contains(keyword)){
                        thongkeTheoThang.add(t);
                    }
                }
            }
            if(getLocTatCa().equals("Theo lợi nhuận")){
                ArrayList<ThongKeTheoThang> tktmp = thongkeBUS.getThongKeTheoThang(startStr, endStr);
                thongkeTheoThang = new ArrayList<>();
                for (ThongKeTheoThang t : tktmp){
                    if(String.valueOf(t.getLoinhuan()).contains(keyword)){
                        thongkeTheoThang.add(t);
                    }
                }
            }
            if(getLocTatCa().equals("Theo doanh thu")){
                ArrayList<ThongKeTheoThang> tktmp = thongkeBUS.getThongKeTheoThang(startStr, endStr);
                thongkeTheoThang = new ArrayList<>();
                for (ThongKeTheoThang t : tktmp){
                    if(String.valueOf(t.getDoanhthu()).contains(keyword)){
                        thongkeTheoThang.add(t);
                    }
                }
            }
            if(getLocTatCa().equals("Theo chi phí")){
                ArrayList<ThongKeTheoThang> tktmp = thongkeBUS.getThongKeTheoThang(startStr, endStr);
                thongkeTheoThang = new ArrayList<>();
                for (ThongKeTheoThang t : tktmp){
                    if(String.valueOf(t.getChiphi()).contains(keyword)){
                        thongkeTheoThang.add(t);
                    }
                }
            }
        }
        updateChiTietTable();
    }
    private void capNhatLocTatCa(String loaiBaoCao) {
        cbLocTatCa.removeAllItems(); // Xóa hết các mục hiện tại

        cbLocTatCa.addItem("Tất cả");

        if (loaiBaoCao.equals("Báo cáo theo ngày")) {
            cbLocTatCa.addItem("Theo ngày mua");
        }

        cbLocTatCa.addItem("Theo tháng");
        cbLocTatCa.addItem("Theo lợi nhuận");
        cbLocTatCa.addItem("Theo doanh thu");
        cbLocTatCa.addItem("Theo chi phí");
    }
}
