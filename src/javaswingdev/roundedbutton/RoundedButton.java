/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaswingdev.roundedbutton;

/**
 *
 * @author RAMADIAN
 */
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class RoundedButton extends JButton {

    private int radius = 15;
    private int fontsize = 14;

    public RoundedButton() {
        initStyle();
    }

    public RoundedButton(String text) {
        super(text);
        initStyle();
    }

    private void initStyle() {
        // Default style
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        setBackground(new Color(40, 167, 69)); // Hijau
        setForeground(Color.WHITE);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public int getRadius() {
        return radius;
    }
    
    public void setFontSize(int fontsize) {
        this.fontsize = fontsize;
        repaint();
    }

    public int getFontSize() {
        return fontsize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Background dengan efek hover & click
        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(
                0, 0,
                getWidth(), getHeight(),
                radius, radius
        );

        // Text center
        FontMetrics fm = g2.getFontMetrics();
        int textX = (getWidth() - fm.stringWidth(getText())) / 2;
        int textY = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

        g2.setColor(getForeground());
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // border opsional (kalau mau)
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(
                0, 0,
                getWidth(), getHeight(),
                radius, radius
        );
        return shape.contains(x, y);
    }
}
