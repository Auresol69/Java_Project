package com.sieuthimini.ExtendClasses;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class GetImagePNG {
    public Image getImage(String imageName) {
        URL imageURL = getClass().getResource("/img/" + imageName);
        if (imageName != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            Image img = icon.getImage().getScaledInstance(60, (int) (60 / 0.875),
                    Image.SCALE_SMOOTH);
            return img;
        } else {
            System.err.println("Error: Image not found");
            return null;
        }
    }
}
