package GUI.Component;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;


public class ButtonToolBar extends JButton{
    int permission;
    
    public ButtonToolBar(String text, String icon, int permisson) {
        this.permission = permisson;
        this.setFont(new java.awt.Font(FlatRobotoFont.FAMILY, 1, 14));
        this.setForeground(new Color(80, 80, 80));
        FlatSVGIcon svgIcon = new FlatSVGIcon("./IMG/" + icon);
        svgIcon.setColorFilter(null);
        this.setIcon(svgIcon);
        this.setText(text);
        this.setFocusable(false);
        this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.putClientProperty("JButton.buttonType", "toolBarButton");
    }
    public int getPermission() {
        return this.permission;
    }
    
}
