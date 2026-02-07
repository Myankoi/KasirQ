package javaswingdev.imagepanel;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage image;
    private String imagePath;
    private int radius = 10;

    // DEFAULT COLOR
    private Color backgroundColor = new Color(240, 240, 240);

    public ImagePanel() {
        setOpaque(false);
    }

    public ImagePanel(String imagePath) {
        this();
        setImage(imagePath);
    }

    // ===== SET IMAGE =====
    public void setImage(String path) {
        this.imagePath = path;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            image = null;
        }
        repaint();
    }

    public void clearImage() {
        image = null;
        repaint();
    }

    // ===== SET BACKGROUND COLOR =====
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    // ===== ROUNDED =====
    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public int getRadius() {
        return radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Rounded shape
        Shape shape = new RoundRectangle2D.Float(
                0, 0, w, h,
                radius, radius
        );

        // Draw background color
        g2.setColor(backgroundColor);
        g2.fill(shape);

        // Clip rounded
        g2.setClip(shape);

        // Draw image if exists
        if (image != null) {
            double scaleX = (double) w / image.getWidth();
            double scaleY = (double) h / image.getHeight();
            double scale = Math.max(scaleX, scaleY);

            int imgW = (int) (image.getWidth() * scale);
            int imgH = (int) (image.getHeight() * scale);

            int x = (w - imgW) / 2;
            int y = (h - imgH) / 2;

            g2.drawImage(image, x, y, imgW, imgH, this);
        }

        g2.dispose();
    }
}
