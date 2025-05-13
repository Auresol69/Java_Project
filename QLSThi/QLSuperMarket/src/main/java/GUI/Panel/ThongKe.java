package GUI.Panel;

import java.awt.*;

import javax.swing.*;

import GUI.QLSieuThi;
import GUI.Component.ThongKeButton;

public class ThongKe extends JPanel { 
        public  JPanel jpanel1, jpanel2,jpanel3,jpanel4,jpanel5;
        String text = "Báo Cáo Doanh Thu";
        Color backgroundcolor = new Color(240, 247, 250);
        public QLSieuThi st;
        public ThongKe (QLSieuThi st){
            this.st = st;
            init();
        }
        public void init(){
            this.setBackground(backgroundcolor);
            this.setLayout(new BorderLayout()); 
            jpanel1 = new ThongKeButton(this,st);
            this.add(jpanel1 , BorderLayout.NORTH);
            jpanel2 = new JPanel();
            jpanel2.setPreferredSize(new Dimension(0,10));
            jpanel2.setBackground(backgroundcolor);
            this.add(jpanel2 , BorderLayout.SOUTH);
            jpanel3 = new JPanel();

            jpanel3.setPreferredSize(new Dimension(10,0));
            jpanel3.setBackground(backgroundcolor);
            this.add(jpanel3, BorderLayout.WEST);
            jpanel4 = new JPanel();

            jpanel4.setPreferredSize(new Dimension(10,0));
            jpanel4.setBackground(backgroundcolor);
            this.add(jpanel4, BorderLayout.EAST);
            jpanel5 = new ThongKeThoiGian(st);
            this.add(jpanel5 , BorderLayout.CENTER);
        }
}
