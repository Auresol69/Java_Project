package GUI.BaoBao.GUI.PhieuComp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.BaoBao.BUS.AccountBUS;
import GUI.BaoBao.BUS.EntryFormBUS;
import GUI.BaoBao.BUS.SupplierBUS;
import GUI.BaoBao.DTO.AccountDTO;
import GUI.BaoBao.DTO.EntryFormDTO;
import GUI.BaoBao.DTO.SupplierDTO;
import com.toedter.calendar.JDateChooser;

public abstract class NhapLieu extends JPanel {

    JLabel supplierLabel, staffLabel, fromDateLabel, toDateLabel, fromMoneyLabel, toMoneyLabel;
    JTextField fromMoneyField, toMoneyField;
    JDateChooser fromDateChooser, toDateChooser;
    JComboBox<SupplierDTO> supplierComboBox;
    JComboBox<AccountDTO> staffComboBox;
    private Timer searchTimer;
    ChucNang chucNang;
    private Table table;

    private void setUpLabel(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(120, 50));
        // label.setFont(new Font(null));
        this.add(label);
    }

    private void setUpField(JTextField field) {
        field.setColumns(20);
        this.add(field);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void setUpComponent(JComponent comp) {
        comp.setFocusable(false);
        this.add(comp);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void setUpComboBox() {
        List<AccountDTO> accountDTOs = new AccountBUS().getNhanVien();
        for (AccountDTO accountDTO : accountDTOs)
            staffComboBox.addItem(accountDTO);
        staffComboBox.setSelectedIndex(-1);
        List<SupplierDTO> supplierDTOs = new SupplierBUS().getNhaCungCap();
        for (SupplierDTO supplierDTO : supplierDTOs)
            supplierComboBox.addItem(supplierDTO);
        supplierComboBox.setSelectedIndex(-1);
    }

    public NhapLieu(String text) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        setUpLabel(supplierLabel = new JLabel(text));
        setUpComponent(supplierComboBox = new JComboBox<>());

        setUpLabel(staffLabel = new JLabel("Nhân viên nhập:"));
        setUpComponent(staffComboBox = new JComboBox<>());

        setUpComboBox();

        setUpLabel(fromDateLabel = new JLabel("Từ ngày:"));
        setUpComponent(fromDateChooser = new JDateChooser());
        setUpLabel(toDateLabel = new JLabel("Đến ngày:"));
        setUpComponent(toDateChooser = new JDateChooser());
        setUpLabel(fromMoneyLabel = new JLabel("Từ giá trị:"));
        setUpField(fromMoneyField = new JTextField());
        setUpLabel(toMoneyLabel = new JLabel("Đến giá trị:"));
        setUpField(toMoneyField = new JTextField());

        searchTimer = new Timer(300, e -> search());
        searchTimer.setRepeats(false);

        fromDateChooser.addPropertyChangeListener("date", evt -> {
            if (searchTimer.isRunning())
                searchTimer.restart();
            else
                searchTimer.start();
        });

        toDateChooser.addPropertyChangeListener("date", evt -> {
            if (searchTimer.isRunning())
                searchTimer.restart();
            else
                searchTimer.start();
        });

        supplierComboBox.addActionListener(e -> {
            if (searchTimer.isRunning())
                searchTimer.restart();
            else
                searchTimer.start();
        });

        staffComboBox.addActionListener(e -> {
            if (searchTimer.isRunning())
                searchTimer.restart();
            else
                searchTimer.start();
        });

        toMoneyField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            private void restartSearchTimer() {
                if (searchTimer.isRunning())
                    searchTimer.restart();
                else
                    searchTimer.start();
            }
        });

        fromMoneyField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            private void restartSearchTimer() {
                if (searchTimer.isRunning())
                    searchTimer.restart();
                else
                    searchTimer.start();
            }
        });
    }

    public void triggerSearch() {
        search();
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Timer getSearchTimer() {
        return searchTimer;
    }

    public void setSearchTimer(Timer searchTimer) {
        this.searchTimer = searchTimer;
    }

    public ChucNang getChucNang() {
        return chucNang;
    }

    public void setChucNang(ChucNang chucNang) {
        this.chucNang = chucNang;
    }

    private void search() {
        List<EntryFormDTO> results = new EntryFormBUS().searchEntryForm((AccountDTO) staffComboBox.getSelectedItem(),
                (SupplierDTO) supplierComboBox.getSelectedItem(),
                fromDateChooser, toDateChooser, chucNang.getColumnSort().getSelectedItem().toString(),
                chucNang.getTimKiem().getText());

        double fromMoney = 0;
        double toMoney = Double.MAX_VALUE;

        try {
            if (!fromMoneyField.getText().isEmpty()) {
                fromMoney = Double.parseDouble(fromMoneyField.getText());
            }
        } catch (NumberFormatException e) {
        }

        try {
            if (!toMoneyField.getText().isEmpty()) {
                toMoney = Double.parseDouble(toMoneyField.getText());
            }
        } catch (NumberFormatException e) {
        }

        List<EntryFormDTO> filteredResults = new ArrayList<>();

        for (EntryFormDTO entryFormDTO : results) {
            if (entryFormDTO.getTongtien() >= fromMoney && entryFormDTO.getTongtien() <= toMoney) {
                filteredResults.add(entryFormDTO);
            }
        }

        table.hienThiEntryForm(filteredResults);

    }

    public void resetFilter() {
        supplierComboBox.setSelectedIndex(-1);
        staffComboBox.setSelectedIndex(-1);
        fromDateChooser.setDate(null);
        toDateChooser.setDate(null);
        fromMoneyField.setText("");
        toMoneyField.setText("");
        search();
    }
}