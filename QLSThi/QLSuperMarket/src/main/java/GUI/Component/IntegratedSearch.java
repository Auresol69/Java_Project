package GUI.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

public class IntegratedSearch extends JPanel {

    public JComboBox<String> cbxChoose;
    public JButton btnReset;
    public JTextField txtSearchForm;

    private void initComponent(String[] str) {
    this.setBackground(Color.WHITE);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
    topPanel.setBackground(Color.WHITE);

    cbxChoose = new JComboBox<>(str);
    cbxChoose.setPreferredSize(new Dimension(140, 30));
    cbxChoose.setFont(new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    cbxChoose.setFocusable(false);
    topPanel.add(cbxChoose);

    btnReset = new JButton();
    btnReset.setFont(new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14));
    btnReset.setIcon(new FlatSVGIcon("./IMG/reset.svg"));
    btnReset.setPreferredSize(new Dimension(50, 30));
    btnReset.addActionListener(this::btnResetActionPerformed);
    topPanel.add(btnReset);

    
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
    bottomPanel.setBackground(Color.WHITE);

    txtSearchForm = new JTextField();
    txtSearchForm.setPreferredSize(new Dimension(300, 30));
    txtSearchForm.setFont(new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    txtSearchForm.putClientProperty("JTextField.placeholderText", "Nhập nội dung tìm kiếm...");
    txtSearchForm.putClientProperty("JTextField.showClearButton", true);
    bottomPanel.add(txtSearchForm);

    
    this.add(topPanel);
    this.add(bottomPanel);
}


    public IntegratedSearch(String str[]) {
        initComponent(str);
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent e) {
        txtSearchForm.setText("");
        cbxChoose.setSelectedIndex(0);
    }
}
