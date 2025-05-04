package com.sieuthimini.ExtendClasses;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class GetImagePNG {
    public Image getImage(String imageName, int scale) {
        URL imageURL = getClass().getResource("/img/" + imageName);
        if (imageName != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            Image img = icon.getImage();
            int originalWidth = img.getWidth(null) / scale;
            int originalHeight = img.getHeight(null) / scale;
            img = icon.getImage().getScaledInstance(originalWidth, originalHeight,
                    Image.SCALE_SMOOTH);
            return img;
        } else {
            System.err.println("Error: Image not found");
            return null;
        }
    }
}
