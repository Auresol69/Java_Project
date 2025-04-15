package GUI.Dialog;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.NumericDocumentFilter;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;


public class NhanVienDialog extends JDialog {

    private NhanVienBUS nv;
    private HeaderTitle titlePage;
    private JPanel main, bottom;
    private ButtonCustom btnAdd, btnEdit, btnExit;
    private InputForm name;
    private InputForm sdt;
    private InputForm diachi;
    private InputDate jcBd;
    private NhanVienDTO nhanVien;

    public NhanVienDialog(NhanVienBUS nv, JFrame owner, boolean modal, String title, String type) {
        super(owner, title, modal);
        this.nv = nv;
        init(title, type);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public NhanVienDialog(NhanVienBUS nv, JFrame owner, boolean modal, String title, String type, DTO.NhanVienDTO nhanVien) {
        super(owner, title, modal);
        this.nv = nv;
        this.nhanVien = nhanVien;
        init(title, type);
        name.setText(nhanVien.getHoten());
        sdt.setText(nhanVien.getSdt());
        diachi.setText(nhanVien.getAddress());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void init(String title, String type) {
        this.setSize(new Dimension(450, 590));
        this.setLayout(new BorderLayout(0, 0));

        titlePage = new HeaderTitle(title.toUpperCase());

        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        name = new InputForm("Họ và tên");
        sdt = new InputForm("Số điện thoại");
        PlainDocument phonex = (PlainDocument) sdt.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));
        diachi = new InputForm("Địa chỉ");
        JPanel jpanelG = new JPanel(new GridLayout(2, 1, 0, 2));
        jpanelG.setBackground(Color.white);
        jpanelG.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel jpaneljd = new JPanel();
        jpaneljd.setBorder(new EmptyBorder(10, 10, 10, 10));
        jpaneljd.setSize(new Dimension(500, 100));
        jpaneljd.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpaneljd.setBackground(Color.white);
        main.add(name);
        main.add(diachi);
        main.add(sdt);
        main.add(jpanelG);

        bottom = new JPanel(new FlowLayout());
        bottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        bottom.setBackground(Color.white);
        btnAdd = new ButtonCustom("Thêm người dùng", "success", 14);
        btnEdit = new ButtonCustom("Lưu thông tin", "success", 14);
        btnExit = new ButtonCustom("Hủy bỏ", "danger", 14);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (ValidationInput()) {
                            int manv = NhanVienDAO.getInstance().getAutoIncrement();
                            String txtName = name.getText();
                            String txtSdt = sdt.getText();
                            String txtDiachi = diachi.getText();
                            NhanVienDTO nV = new NhanVienDTO(manv, txtName, txtSdt, txtDiachi, 1);
                            NhanVienDAO.getInstance().insert(nV);
                            nv.insertNv(nV);
                            nv.loadTable();
                            dispose();
                        }
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (ValidationInput()) {
                            int manv = NhanVienDAO.getInstance().getAutoIncrement();
                            String txtName = name.getText();
                            String txtSdt = sdt.getText();
                            String txtDiachi = diachi.getText();
                            NhanVienDTO nV = new NhanVienDTO(nhanVien.getMaNV(), txtName, txtSdt, txtDiachi, 1);
                            NhanVienDAO.getInstance().update(nV);
                            System.out.println("Index:" + nv.getIndex());
                            nv.listNv.set(nv.getIndex(), nV);
                            nv.loadTable();
                            dispose();
                }
            }
        });

        switch (type) {
            case "create" ->
                bottom.add(btnAdd);
            case "update" ->
                bottom.add(btnEdit);
            case "detail" -> {
                name.setDisable();
                sdt.setDisable();
            }
            default ->
                throw new AssertionError();
        }

        bottom.add(btnExit);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

    }

    boolean ValidationInput() {
        if (Validation.isEmpty(name.getText())) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if(name.getText().length()<6){
            JOptionPane.showMessageDialog(this, "Tên nhân viên ít nhất 6 kí tự!");
            return false;
        } else if (Validation.isEmpty(sdt.getText()) && !Validation.isNumber(sdt.getText()) && sdt.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng và phải là 10 ký tự số", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if(Validation.isEmpty(diachi.getText())){
            JOptionPane.showMessageDialog(this, "Địa chỉ nhân viên không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
}
