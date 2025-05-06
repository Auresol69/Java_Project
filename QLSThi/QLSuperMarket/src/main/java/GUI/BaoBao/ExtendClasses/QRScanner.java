package GUI.BaoBao.ExtendClasses;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicReference;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QRScanner {

    public static String read(JFrame parent, String title) {
        AtomicReference<String> text = new AtomicReference<>(null);
        Webcam webcam = Webcam.getDefault();

        if (webcam == null) {
            System.out.println("Không tìm thấy webcam.");
            return null;
        }

        webcam.open();

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);

        JDialog window = new JDialog(parent, title, true);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(parent);

        // Bắt sự kiện đóng cửa sổ
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                webcam.close();
            }
        });

        // Thread quét QR
        new Thread(() -> {
            while (true) {
                BufferedImage image = webcam.getImage();
                if (image == null)
                    continue;

                LuminanceSource source = new BufferedImageLuminanceSource(image); // Convert ảnh thành nguồn sáng đơn
                                                                                  // sắc
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source)); // Chuyển ảnh thành ảnh nhị phân

                try {
                    Result result = new MultiFormatReader().decode(bitmap); // Giải mã nhị phân
                    text.set(result.getText());
                    break;
                } catch (Exception ignored) {
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }

            webcam.close();
            window.dispose();
        }).start();

        window.setVisible(true);

        return text.get();
    }

}