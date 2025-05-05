package com.sieuthimini.ExtendClasses;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class GetImagePNG {

    public Image getImage(String imageName, int scale) {
        if (imageName == null || imageName.trim().isEmpty()) {
            // System.err.println("Tên ảnh không hợp lệ.");
            return getDefaultImage(scale);
        }

        // Loại bỏ tiền tố "/img/" nếu người dùng đã truyền vào
        if (imageName.startsWith("img/")) {
            imageName = imageName.substring(4); // Bỏ đi "/img/"
        }

        URL imageURL = getClass().getResource("/img/" + imageName);

        // Nếu ảnh không tồn tại thì dùng ảnh mặc định
        if (imageURL == null) {
            return getDefaultImage(scale);
        }

        return scaleImage(imageURL, scale);
    }

    private Image getDefaultImage(int scale) {
        URL defaultURL = getClass().getResource("/img/default.png");
        if (defaultURL == null) {
            System.err.println("Ảnh mặc định không tồn tại!");
            return null;
        }
        return scaleImage(defaultURL, scale);
    }

    private Image scaleImage(URL imageURL, int scale) {
        ImageIcon icon = new ImageIcon(imageURL);
        Image img = icon.getImage();

        int originalWidth = img.getWidth(null);
        int originalHeight = img.getHeight(null);

        if (originalWidth <= 0 || originalHeight <= 0) {
            System.err.println("Kích thước ảnh không hợp lệ.");
            return null;
        }

        int newWidth = originalWidth / scale;
        int newHeight = originalHeight / scale;

        return img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }
}
