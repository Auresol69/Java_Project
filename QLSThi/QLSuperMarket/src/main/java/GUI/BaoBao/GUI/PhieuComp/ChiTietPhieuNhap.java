package GUI.BaoBao.GUI.PhieuComp;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;

import GUI.BaoBao.BUS.DetailEntryFormBUS;
import GUI.BaoBao.BUS.EntryFormBUS;
import GUI.BaoBao.DTO.DetailEntryFormDTO;
import GUI.BaoBao.DTO.EntryFormDTO;
import GUI.BaoBao.GUI.PhieuComp.ChiTietPhieuNhapComp.Button;
import GUI.BaoBao.GUI.PhieuComp.ChiTietPhieuNhapComp.Info;
import GUI.BaoBao.GUI.PhieuComp.ChiTietPhieuNhapComp.Title;
import GUI.BaoBao.GUI.PhieuComp.ChiTietPhieuNhapComp.Table;

public class ChiTietPhieuNhap extends JDialog {

    private Title title;
    private Info info;
    private Table table;
    private Button button;
    JTable entryformTable;

    public ChiTietPhieuNhap(Frame parent, JTable entryformTable) {
        super(parent, "Thông tin phiếu nhập", true);

        this.entryformTable = entryformTable;

        this.setLayout(new BorderLayout());

        title = new Title();
        info = new Info();
        table = new Table();
        button = new Button();

        int selectedRow = entryformTable.getSelectedRow();
        if (selectedRow >= 0) {
            table.getModel().setRowCount(0);
            int id = Integer.parseInt(entryformTable.getValueAt(selectedRow, 0).toString());
            ArrayList<DetailEntryFormDTO> data = new DetailEntryFormBUS().getDetailEntryFormByID(id);

            for (DetailEntryFormDTO detailEntryFormDTO : data) {
                table.getModel().addRow(new Object[] {
                        detailEntryFormDTO.getMasp(), detailEntryFormDTO.getTenSanPham(),
                        detailEntryFormDTO.getSoluongnhap(), detailEntryFormDTO.getDongianhap(),
                        detailEntryFormDTO.getNgayhethan()
                });
            }

            EntryFormDTO entryFormDTO = new EntryFormBUS().getEntryFormByID(id);
            info.getMaphieuField().setText(String.valueOf(entryFormDTO.getMaphieunhap()));
            info.getStaffField().setText(String.valueOf(entryFormDTO.getMaaccount()));
            info.getSupplierField().setText(String.valueOf(entryFormDTO.getMancc()));
            info.getTimeField().setText(entryFormDTO.getNgaynhap());
        }

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(info);
        centerPanel.add(table);

        this.add(title, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(parent);
    }
}
