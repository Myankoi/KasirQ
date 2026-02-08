/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaswingdev.roundedpasswordtextfield;

/**
 *
 * @author RAMADIAN
 */


import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class RoundedPasswordField extends JPasswordField {

    private int radius = 15;
    private String placeholder = "";
    private Color borderColor = new Color(220, 220, 220);
    private Color focusBorderColor = new Color(180, 180, 180);

    public RoundedPasswordField() {
        initStyle();
    }

    public RoundedPasswordField(String placeholder) {
        this.placeholder = placeholder;
        initStyle();
    }

    private void initStyle() {
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);

        setBorder(new EmptyBorder(10, 14, 10, 14));
        setOpaque(false);
        setCaretColor(Color.BLACK);

        // This is the KEY line 🔑
        setEchoChar('•');

        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                repaint();
            }
        });
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g2);

        // Placeholder (only when empty & not focused)
        if (getPassword().length == 0 && !isFocusOwner() && placeholder != null) {
            g2.setColor(new Color(150, 150, 150));
            g2.setFont(getFont());
            Insets insets = getInsets();
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(placeholder, insets.left, y);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(isFocusOwner() ? focusBorderColor : borderColor);
        g2.drawRoundRect(
                0, 0,
                getWidth() - 1, getHeight() - 1,
                radius, radius
        );

        g2.dispose();
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

