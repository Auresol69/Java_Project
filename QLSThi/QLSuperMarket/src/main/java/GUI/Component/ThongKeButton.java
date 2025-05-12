package GUI.Component;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import GUI.QLSieuThi;
import GUI.Panel.ThongKe;
import GUI.Panel.ThongKeKhachHang;
import GUI.Panel.ThongKeThoiGian;
public class ThongKeButton  extends JPanel{
    String []header = {"Thời Gian", "Khách Hàng","Sản Phẩm"};
    private static ArrayList<JButton> buttons = new ArrayList<>();
    private static JButton selectedButton;
    public static JPanel jp1, jp2,center,jp3,jp4;
    public QLSieuThi st;
    public ThongKe m ;
    public static Color backgroundColor = new Color(240, 247, 250);
    public ThongKeButton (ThongKe m , QLSieuThi st){
        this.st = st;
        this.m = m;
        init();
    }
    public void init(){
        this.setLayout(new BorderLayout());
        jp1 = new JPanel();
        jp1.setPreferredSize(new Dimension(0,10));
        jp1.setBackground(backgroundColor);
        jp2 = new JPanel();
        jp2.setPreferredSize(new Dimension(0,10));
        jp2.setBackground(backgroundColor);
        jp3 = new JPanel();
        jp3.setPreferredSize(new Dimension(10,0));
        jp3.setBackground(backgroundColor);
        jp4 = new JPanel();
        jp4.setPreferredSize(new Dimension(10,0));
        jp4.setBackground(backgroundColor);
        this.add(jp1,BorderLayout.NORTH);
        this.add(jp2,BorderLayout.SOUTH);
        this.add(jp3,BorderLayout.EAST);
        this.add(jp4,BorderLayout.WEST);
        center = new JPanel();
        center.setLayout(new GridLayout(1, header.length,10,10));
        center.setBackground(backgroundColor);
        // this.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        for (String title : header){
            JButton button = new JButton(title);
            button.setFocusPainted(false);
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setBackground(backgroundColor);
            button.setForeground(Color.black);
            button.setPreferredSize(new Dimension(150,40));
            if(title.equals("Thời Gian")){
                button.setBackground(Color.WHITE);
                selectedButton = button;
            }
            buttons.add(button);
            center.add(button);
            
            button.addMouseListener(new MouseAdapter(){
                @Override 
                public void mouseEntered(MouseEvent e){
                    if(button != selectedButton)
                    button.setBackground(Color.LIGHT_GRAY);
                }
                @Override 
                public void mouseExited(MouseEvent e){
                    if(button != selectedButton)
                    button.setBackground(backgroundColor);
                }
                @Override 
                public void mouseClicked(MouseEvent e){
                    JButton btnClicked = (JButton) e.getSource();
                    for (JButton btn : buttons){
                        btn.setBackground(backgroundColor);
                    }
                    btnClicked.setBackground(Color.WHITE);
                    selectedButton = btnClicked; 
                    switchPanel(btnClicked.getText());
                }
            });
        }
        this.add(center , BorderLayout.CENTER);
    }
    public ArrayList<JButton> getButtons(){
        return buttons;
    }
    void switchPanel(String text){
        m.remove(m.jpanel5);
        switch (text){
            case "Thời Gian":
                m.jpanel5 = new ThongKeThoiGian(st);
                break;
            // case "Nhân Viên":
            //     m.jpanel5 = new ThongKeNhanVien(st);
            //     break;
            case "Khách Hàng":
                m.jpanel5 = new ThongKeKhachHang(st);
                break;
            // case "Sản Phẩm":
            //     m.jpanel5 = new ThongKeSanPham(st);
            //     break;
        }
        m.add(m.jpanel5, BorderLayout.CENTER);
        m.revalidate();
        m.repaint();
    }
    
}
