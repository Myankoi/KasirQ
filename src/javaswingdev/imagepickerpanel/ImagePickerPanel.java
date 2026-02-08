package javaswingdev.imagepickerpanel;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javaswingdev.roundedbutton.RoundedButton;

public class ImagePickerPanel extends JPanel {

    private JLabel imageLabel;
    private RoundedButton chooseButton;
    private Image image;
    private File selectedFile;

    private final int radius = 15;

    public ImagePickerPanel() {
        setLayout(new BorderLayout(8, 8));
        setOpaque(true);
        setBackground(new Color(245, 247, 250));

        imageLabel = new JLabel("No Image Selected", SwingConstants.CENTER);
        imageLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        imageLabel.setForeground(new Color(140, 150, 160));
        imageLabel.setPreferredSize(new Dimension(300, 180));

        chooseButton = new RoundedButton("Choose Image");
        chooseButton.setFocusPainted(false);
        chooseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chooseButton.addActionListener(e -> chooseImage());

        add(imageLabel, BorderLayout.CENTER);
        add(chooseButton, BorderLayout.SOUTH);
    }

    private void chooseImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(
            new FileNameExtensionFilter("Image Files (JPG, PNG)", "jpg", "jpeg", "png")
        );

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = chooser.getSelectedFile();
                image = ImageIO.read(selectedFile);
                imageLabel.setText("");
                repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to load image");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // ✅ WAJIB DI AWAL

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // background rounded box
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), imageLabel.getHeight(), radius, radius);

        if (image != null) {
            int iw = image.getWidth(this);
            int ih = image.getHeight(this);

            int w = getWidth();
            int h = imageLabel.getHeight();

            float scale = Math.min((float) w / iw, (float) h / ih);
            int nw = (int) (iw * scale);
            int nh = (int) (ih * scale);

            int x = (w - nw) / 2;
            int y = (h - nh) / 2;

            g2.drawImage(image, x, y, nw, nh, this);
        }

        g2.dispose();
    }

    /* ======================
       PUBLIC API (PENTING)
       ====================== */

    public File getSelectedFile() {
        return selectedFile;
    }

    public void clear() {
        image = null;
        selectedFile = null;
        imageLabel.setText("No Image Selected");
        repaint();
    }

    public void setImage(String resourcePath) {
        try {
            image = ImageIO.read(getClass().getResource(resourcePath));
            imageLabel.setText("");
            repaint();
        } catch (Exception e) {
            clear();
        }
    }
}
