package GUI.Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import GUI.QLSieuThi;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;

public class TaiKhoanGUI extends JPanel {
    PanelBorderRadius main , functionbar;
    JPanel pnlBorder1 , pnlBorder2, pnlBorder3, pnlBorder4,contentCenter;
    JTable tableTaiKhoan;
    MainFunction mainFunction;
    JScrollPane scrollTaiKhoan ;
    DefaultTableModel tblModel ;
    QLSieuThi m;
    Color BackgroundColor = new Color(240 , 274 , 250);
    public TaiKhoanGUI(QLSieuThi main){
        initComponent();
        m = main;
    }
    private void initComponent(){
        tableTaiKhoan = new JTable();
        tableTaiKhoan.setDefaultEditor(Object.class , null);
        scrollTaiKhoan = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String []{"MaNV", "Tên đăng nhập", "Nhóm quyền", "Trạng thái"};
        tblModel.setColumnIdentifiers(header);
        tableTaiKhoan.setModel(tblModel);
        tableTaiKhoan.setFocusable(false);
        scrollTaiKhoan.setViewportView(tableTaiKhoan);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tableTaiKhoan.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableTaiKhoan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableTaiKhoan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0,0));
        this.setOpaque(true);

        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension (0 , 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1 , BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension (0 , 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder1 , BorderLayout.NORTH);


        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension (0 , 10));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder1 , BorderLayout.EAST);


        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension (10 , 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder1 , BorderLayout.WEST);


        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension (10 , 0));
        contentCenter.setBackground(BackgroundColor);
        this.add(pnlBorder1 , BorderLayout.CENTER);

        functionbar = new PanelBorderRadius();
        functionbar.setPreferredSize(new Dimension());
        functionbar.setLayout(new GridLayout(1,2,50,0));
        functionbar.setBorder(new EmptyBorder(10,10,10,10));
        
        String[] action = {"create" , "update" , "delete" , "detail"};
        mainFunction = new MainFunction(m.user.getPowerGroupId(),"taikhoan", action );
    }
}
