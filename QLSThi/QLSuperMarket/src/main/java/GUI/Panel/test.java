package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;

public class test extends JPanel implements ActionListener {
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    JPanel main, functionBar;
    JPanel left, content;
    JTable tblTest;
    JScrollPane scrollTable;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    Color BackgroundColor = new Color(255, 255, 255);

    private void initComponent() {
        this.setLayout(new BorderLayout(0,0));
        this.setOpaque(true);
        left = new JPanel();
        left.setPreferredSize(new Dimension(5, 0));
        this.add(left, BorderLayout.WEST);

        content = new JPanel();
        content.setPreferredSize(new Dimension(1100, 600));
        content.setLayout(new BorderLayout(0, 0));
        this.add(content,BorderLayout.CENTER);

        functionBar = new JPanel();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        functionBar.setBackground(BackgroundColor);

        content.add(functionBar, BorderLayout.NORTH);
main = new JPanel();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        content.add(main, BorderLayout.CENTER);

        tblTest = new JTable();
        tblTest.setDefaultEditor(Object.class, null);
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();

        String[] header = new String[]{"Mã tỉnh", "Tên tỉnh"};
        tblModel.setColumnIdentifiers(header);

        // Dữ liệu tỉnh
        String[][] tinhData = {
            {"01", "Hà Nội"},
            {"02", "Hồ Chí Minh"},
            {"03", "Đà Nẵng"},
            {"04", "Cần Thơ"},
            {"05", "Hải Phòng"}
        };

        for (String[] row : tinhData) {
            tblModel.addRow(row);
        }

        tblTest.setModel(tblModel);
        scrollTable.setViewportView(tblTest);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tblTest.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);

        tblTest.setFocusable(false);
        tblTest.getTableHeader().setReorderingAllowed(false);
        main.add(scrollTable);
    }

    public test() {
        initComponent();
    }
    public static void main(String[] args) {
     FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
        UIManager.put("Table.selectionForeground", new Color(0, 0, 0));
        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("Table.rowHeight", 40);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TableHeader.height", 40);
        UIManager.put("TableHeader.font", UIManager.getFont("h4.font"));
        UIManager.put("TableHeader.background", new Color(242, 242, 242));
        UIManager.put("TableHeader.separatorColor", new Color(242, 242, 242));
        UIManager.put("TableHeader.bottomSeparatorColor", new Color(242, 242, 242));
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Test Bảng Tỉnh");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(1200, 700);
            f.setLocationRelativeTo(null);
            f.setContentPane(new test());
            f.setVisible(true);
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
