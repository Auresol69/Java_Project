package GUI.BaoBao.GUI.PhieuComp.ChiTietPhieuNhapComp;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Info extends JPanel {
    private JLabel maphieuLabel, staffLabel, supplierLabel, timeLabel;
    private JTextField maphieuField, staffField, supplierField, timeField;

    public Info() {
        this.setLayout(new GridLayout(2, 4, 10, 10));

        maphieuLabel = new JLabel("Mã phiếu");
        staffLabel = new JLabel("Nhân viên");
        supplierLabel = new JLabel("Nhà cung cấp");
        timeLabel = new JLabel("Thời gian");

        maphieuField = new JTextField();
        maphieuField.setEditable(false);
        staffField = new JTextField();
        staffField.setEditable(false);
        supplierField = new JTextField();
        supplierField.setEditable(false);
        timeField = new JTextField();
        timeField.setEditable(false);

        this.add(maphieuLabel);
        this.add(staffLabel);
        this.add(supplierLabel);
        this.add(timeLabel);
        this.add(maphieuField);
        this.add(staffField);
        this.add(supplierField);
        this.add(timeField);
    }

    public JTextField getMaphieuField() {
        return maphieuField;
    }

    public void setMaphieuField(JTextField maphieuField) {
        this.maphieuField = maphieuField;
    }

    public JTextField getStaffField() {
        return staffField;
    }

    public void setStaffField(JTextField staffField) {
        this.staffField = staffField;
    }

    public JTextField getSupplierField() {
        return supplierField;
    }

    public void setSupplierField(JTextField supplierField) {
        this.supplierField = supplierField;
    }

    public JTextField getTimeField() {
        return timeField;
    }

    public void setTimeField(JTextField timeField) {
        this.timeField = timeField;
    }
}
