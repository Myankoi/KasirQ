package javaswingdev.roundedcombobox;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class RoundedComboBox<E> extends JComboBox<E> {

    private int radius = 15;
    private Color borderColor = new Color(220, 220, 220);
    private Color focusBorderColor = new Color(180, 180, 180);

    public RoundedComboBox(E[] items) {
        super(items);
        initStyle();
    }

    public RoundedComboBox() {
        initStyle();
    }

    private void initStyle() {
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton btn = new JButton("▼");
                btn.setBorder(null);
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
                btn.setForeground(new Color(80, 80, 80));
                return btn;
            }
        });
    }

    public void setRadius(int radius) {
        this.radius = radius;
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
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(hasFocus() ? focusBorderColor : borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

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
