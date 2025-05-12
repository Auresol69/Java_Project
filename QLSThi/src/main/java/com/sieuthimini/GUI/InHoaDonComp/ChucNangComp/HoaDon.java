package com.sieuthimini.GUI.InHoaDonComp.ChucNangComp;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.DAO.BillDAO;
import com.sieuthimini.DTO.BillDTO;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class HoaDon extends JDialog {
    JTable tb;
    DefaultTableModel model;

    public HoaDon(JFrame parent, JTable table, JLabel tongTien, Integer mabill) {
        super(parent, "Hóa đơn", true);

        BillDTO bill = new BillDAO().getBillByID(mabill);

        // Set dialog layout
        this.setLayout(new BorderLayout(10, 10));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Title label

        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 5));
        JLabel titleLabel = new JLabel("RECEIPT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        titlePanel.add(titleLabel);
        titlePanel.add(new JLabel(bill.getNgaymua(), SwingConstants.CENTER));
        this.add(titlePanel, BorderLayout.NORTH);

        // Table model and JTable
        model = new DefaultTableModel(0, 4);
        tb = new JTable(model);

        // Remove table borders and header for cleaner receipt look
        tb.setShowHorizontalLines(false); // Ẩn dòng kẻ ngang
        tb.setShowVerticalLines(false); // Ẩn dòng kẻ dọc
        tb.setBorder(null); // Xóa border của bảng
        tb.setTableHeader(null); // Ẩn tiêu đề cột
        tb.setFocusable(false); // Không cho focus (bo viền khi click)
        tb.setRowSelectionAllowed(false); // Không cho chọn dòng
        tb.setCellSelectionEnabled(false);

        // Set auto resize mode to adjust columns to fit content
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Set preferred widths for columns (adjust as needed)
        int[] columnWidths = { 150, 100, 100, 100 };
        for (int i = 0; i < columnWidths.length; i++) {
            if (i < tb.getColumnModel().getColumnCount()) {
                tb.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
            }
        }

        for (int i = 0; i < table.getRowCount(); i++) {
            model.addRow(new Object[] {
                    table.getValueAt(i, 1), table.getValueAt(i, 2), table.getValueAt(i, 3), table.getValueAt(i, 4)
            });
        }
        // Remove scroll pane, add table directly
        this.add(tb, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(6, 1, 0, 5));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel separator1 = new JLabel("===================================", SwingConstants.CENTER);
        JLabel totalLabel = new JLabel(tongTien.getText(), SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel separator2 = new JLabel("===================================", SwingConstants.CENTER);
        JLabel billLabel = new JLabel("Số hóa đơn: " + mabill, SwingConstants.CENTER);
        JLabel separator3 = new JLabel("-----------------------------------", SwingConstants.CENTER);
        JLabel thankYouLabel = new JLabel("THANK YOU!", SwingConstants.CENTER);
        thankYouLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        bottomPanel.add(separator1);
        bottomPanel.add(totalLabel);
        bottomPanel.add(separator2);
        bottomPanel.add(billLabel);
        bottomPanel.add(separator3);
        bottomPanel.add(thankYouLabel);

        this.add(bottomPanel, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.setVisible(true);

        // đoạn code cập nhật giao diện hoặc chạy sau khi UI đã render
        SwingUtilities.invokeLater(() -> {
            try {
                saveAsImage(mabill + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void saveAsImage(String filePath) throws IOException {
        // Capture the content pane instead of the whole dialog
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setDoubleBuffered(false);
        contentPane.validate();
        contentPane.repaint();

        BufferedImage image = new BufferedImage(contentPane.getWidth(), contentPane.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        contentPane.printAll(image.getGraphics());
        // Ensure parent directories exist
        File outputFile = new File(filePath);
        File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        ImageIO.write(image, getFileExtension(filePath), outputFile);
        contentPane.setDoubleBuffered(true);
    }

    private String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1).toLowerCase();
        }
        return "png"; // default to png if no extension found
    }
}
