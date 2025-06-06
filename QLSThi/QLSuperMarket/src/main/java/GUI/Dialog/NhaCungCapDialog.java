
package GUI.Dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.NumericDocumentFilter;
import GUI.Panel.NhaCungCap;
import helper.Validation;

public class NhaCungCapDialog extends JDialog implements ActionListener {

   private NhaCungCap jpNcc;
   private JPanel pnmain, pnbottom;
   private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
   private InputForm tenNcc;
   private InputForm diachi;
   private InputForm sofax;
   private InputForm sodienthoai;
   private NhaCungCapDTO nccDTO;

   public NhaCungCapDialog(NhaCungCap jpNcc, JFrame owner, String title, boolean modal, String type) {
       super(owner, title, modal);
       this.jpNcc = jpNcc;
       initComponents(title, type);
   }

   public NhaCungCapDialog(NhaCungCap jpNcc, JFrame owner, String title, boolean modal, String type, NhaCungCapDTO nccdto) {
       super(owner, title, modal);
       this.jpNcc = jpNcc;
       this.nccDTO = nccdto;
       initComponents(title, type);
   }

   public void initComponents(String title, String type) {
       this.setSize(new Dimension(900, 360));
       this.setLayout(new BorderLayout(0, 0));
       pnmain = new JPanel(new GridLayout(2, 2, 20, 0));
       pnmain.setBackground(Color.white);
       tenNcc = new InputForm("Tên nhà cung cấp");
       diachi = new InputForm("Địa chỉ");
       sofax = new InputForm("Số fax");
       sofax.getTxtForm().addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c)) {
                e.consume(); // chặn ký tự không phải số
            }
        }
    });
       sodienthoai = new InputForm("Số điện thoại");
       sodienthoai.getTxtForm().addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c)) {
                e.consume(); // chặn ký tự không phải số
            }
        }
    });

       pnmain.add(tenNcc);
       pnmain.add(diachi);
       pnmain.add(sodienthoai);
       pnmain.add(sofax);

       pnbottom = new JPanel(new FlowLayout());
       pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
       pnbottom.setBackground(Color.white);
       btnThem = new ButtonCustom("Thêm đơn vị", "success", 14);
       btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
       btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

       //Add MouseListener btn
       btnThem.addActionListener(this);
       btnCapNhat.addActionListener(this);
       btnHuyBo.addActionListener(this);

       switch (type) {
           case "create" ->
               pnbottom.add(btnThem);
           case "update" -> {
               pnbottom.add(btnCapNhat);
               initInfo();
           }
           case "view" -> {
               initInfo();
               initView();
           }
           default ->
               throw new AssertionError();
       }
       pnbottom.add(btnHuyBo);
       this.add(pnmain, BorderLayout.CENTER);
       this.add(pnbottom, BorderLayout.SOUTH);
       this.setLocationRelativeTo(null);
       this.setVisible(true);
   }

   public void initInfo() {
       tenNcc.setText(nccDTO.getTenncc());
       diachi.setText(nccDTO.getDiachi());
       sofax.setText(nccDTO.getSofax());
       sodienthoai.setText(nccDTO.getSdt());
   }

   public void initView() {
       tenNcc.setEditable(false);
       diachi.setEditable(false);
       sofax.setEditable(false);
       sodienthoai.setEditable(false);

   }
   public boolean Validation(){
        if (Validation.isEmpty(tenNcc.getText())) {
           JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else  if (Validation.isEmpty(diachi.getText())) {
           JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else if (Validation.isEmpty(sofax.getText()) || !Validation.isFax(sofax.getText())) {
           JOptionPane.showMessageDialog(this, "Số Fax không được rỗng và phải đúng cú pháp", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else if (Validation.isEmpty(sodienthoai.getText()) || !Validation.isNumber(sodienthoai.getText()) && sodienthoai.getText().length()!=10) {
           JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng và phải là 10 ký tự số", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
           return false;
        }
         return true;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
       if (e.getSource() == btnThem && Validation()) {
           int mancc = NhaCungCapDAO.getInstance().getAutoIncrement();  
           jpNcc.nccBUS.add(new NhaCungCapDTO(mancc, tenNcc.getText(), diachi.getText(), sodienthoai.getText(), sofax.getText()));
           jpNcc.listncc = jpNcc.nccBUS.getAll();
           jpNcc.loadDataTable(jpNcc.listncc);
           dispose();

       } else if (e.getSource() == btnHuyBo) {
           dispose();
       } else if (e.getSource() == btnCapNhat && Validation()) {
           jpNcc.nccBUS.update(new NhaCungCapDTO(nccDTO.getMancc(), tenNcc.getText(), diachi.getText(), sodienthoai.getText(), sofax.getText()));
           jpNcc.loadDataTable(jpNcc.listncc);
           dispose();
       }
   }
}

