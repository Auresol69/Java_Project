/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author ANH QUYÊN
 */
public class QLSieuThi extends JFrame implements MouseListener {
    public QLSieuThi(){
        
    }
        public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e)
        {
            
        }
        QLSieuThi ql = new QLSieuThi();
        
    }
     @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    
}
