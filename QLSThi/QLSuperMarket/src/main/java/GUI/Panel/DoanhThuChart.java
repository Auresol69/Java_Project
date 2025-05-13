package GUI.Panel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;
import java.awt.*;

public class DoanhThuChart extends JFrame {

    public DoanhThuChart(String title) {
        super(title);

        // Tạo dataset
        CategoryDataset dataset = createDataset();

        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh Thu và Vốn theo Thời Gian", // tiêu đề
                "Thời gian", // nhãn trục X
                "Giá trị (VND)", // nhãn trục Y
                dataset, // dữ liệu
                PlotOrientation.VERTICAL, // hướng biểu đồ
                true, // hiển thị chú giải
                true, // hiển thị thông tin tooltip
                false // không hiển thị URL
        );

        // Tạo panel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    // Tạo dataset
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Dữ liệu doanh thu
        dataset.addValue(50, "Doanh thu", "1/1/2020");
        dataset.addValue(30, "Doanh thu", "2/1/2020");  
        dataset.addValue(40, "Doanh thu", "3/1/2020");
        dataset.addValue(55, "Doanh thu", "4/1/2020");
        dataset.addValue(60, "Doanh thu", "5/1/2020");
        dataset.addValue(35, "Doanh thu", "6/1/2020");
        dataset.addValue(45, "Doanh thu", "7/1/2020");
        dataset.addValue(50, "Doanh thu", "8/1/2020");
        dataset.addValue(20, "Doanh thu", "9/1/2020");

        // Dữ liệu tiền vốn
        dataset.addValue(10, "Tiền vốn", "1/1/2020");
        dataset.addValue(8, "Tiền vốn", "2/1/2020");
        dataset.addValue(5, "Tiền vốn", "3/1/2020");
        dataset.addValue(12, "Tiền vốn", "4/1/2020");
        dataset.addValue(15, "Tiền vốn", "5/1/2020");
        dataset.addValue(7, "Tiền vốn", "6/1/2020");
        dataset.addValue(10, "Tiền vốn", "7/1/2020");
        dataset.addValue(9, "Tiền vốn", "8/1/2020");
        dataset.addValue(3, "Tiền vốn", "9/1/2020");

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DoanhThuChart chart = new DoanhThuChart("Doanh Thu và Vốn theo Thời Gian");
            chart.setSize(800, 600);
            chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chart.setVisible(true);
        });
    }
}
