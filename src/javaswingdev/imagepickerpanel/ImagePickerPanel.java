package javaswingdev.imagepickerpanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javaswingdev.roundedbutton.RoundedButton;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePickerPanel extends JPanel {

    private JLabel imageLabel;
    private RoundedButton chooseButton;
    private Image image;
    private int radius = 15;

    public ImagePickerPanel() {
        setLayout(new BorderLayout(10, 10));
        setOpaque(false);

        imageLabel = new JLabel("No Image Selected", SwingConstants.CENTER);
        imageLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        imageLabel.setForeground(new Color(120, 130, 150));
        imageLabel.setPreferredSize(new Dimension(300, 160));

        chooseButton = new RoundedButton("Choose Image");
        chooseButton.setFocusPainted(false);
        chooseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        chooseButton.addActionListener(e -> chooseImage());

        add(imageLabel, BorderLayout.CENTER);
        add(chooseButton, BorderLayout.SOUTH);
    }

    private void chooseImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png"));

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                image = ImageIO.read(file);
                imageLabel.setText("");
                repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to load image");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background box
        g2.setColor(new Color(245, 247, 250));
        g2.fillRoundRect(0, 0, getWidth(), imageLabel.getHeight(), radius, radius);

        // Draw Image
        if (image != null) {
            int iw = image.getWidth(this);
            int ih = image.getHeight(this);

            int w = getWidth();
            int h = imageLabel.getHeight();

            float ratio = Math.min((float) w / iw, (float) h / ih);
            int newW = (int) (iw * ratio);
            int newH = (int) (ih * ratio);

            int x = (w - newW) / 2;
            int y = (h - newH) / 2;

            g2.drawImage(image, x, y, newW, newH, this);
        }

        g2.dispose();
        super.paintComponent(g);
    }

    public File getSelectedFile() {
        return null; // bisa ditambah kalau mau simpan path
    }
}
