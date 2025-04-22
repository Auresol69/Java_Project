package GUI.Component;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

public class ButtonCustom extends JButton {

    public void initComponent(String type, String text, int fontsize, int width, int height) {
        this.setText(text);
        String color = null;
        switch (type) {
            case "success":
                color = "Actions.White";
                this.setForeground(new java.awt.Color(128, 128, 128));                break;
            case "danger":
                color = "Actions.Red";
                this.setForeground(new java.awt.Color(255,255,255));

                break;
            case "warning":
                color = "Actions.Yellow";
                this.setForeground(new java.awt.Color(255,255,255));

                break;
            case "excel":
                color = "Actions.Green";
                this.setForeground(new java.awt.Color(255,255,255));

                break;
            case "return":
                color = "Actions.Orange";
                this.setForeground(new java.awt.Color(255,255,255));

                break;
            case "ok":
                color = "Actions.Black";
                this.setForeground(new java.awt.Color(255,255,255));

                break;
            default:
                color = "Actions.White";
                this.setForeground(new java.awt.Color(255,255,255));
        }
        // this.putClientProperty("JButton.buttonType", "roundRect");
        this.putClientProperty("JButton.buttonType", "borderless");
        this.putClientProperty("JComponent.arc", 15); // hoặc 20 nếu thích tròn rõ hơn
        this.setBackground(javax.swing.UIManager.getDefaults().getColor(color));
        this.setBorder(BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200),1,true)); // Border màu xám nhạt
        this.setFont(new java.awt.Font(FlatRobotoFont.FAMILY, 0, fontsize));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(new Dimension(width, height));
    }

    public ButtonCustom(String text, String type, int fontsize) {
        initComponent(type, text, fontsize, 150, 40);
    }

    public ButtonCustom(String text, String type, int fontsize, int w, int h) {
        initComponent(type, text, fontsize, w, h);
    }

    public ButtonCustom(String text, String type, int fontsize, String linkIcon) {
        initComponent(type, text, fontsize, 150, 40);
        this.setIcon(new ImageIcon(getClass().getResource(linkIcon)));
    }

    public ButtonCustom(String text, String type, int fontsize, String linkIcon, int width, int height) {
        initComponent(type, text, fontsize, width, height);
        this.setIcon(new ImageIcon(getClass().getResource(linkIcon)));
    }

    public void setVisible(Boolean value) {
        this.setVisible(value);
    }
}

