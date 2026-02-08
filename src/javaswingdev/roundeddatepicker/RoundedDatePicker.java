package javaswingdev.roundeddatepicker;

import javaswingdev.roundedtextfield.RoundedTextField;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class RoundedDatePicker extends JPanel {

    private final RoundedTextField field;
    private final JPopupMenu popup;
    private final CalendarPanel calendar;

    private final SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd");

    public RoundedDatePicker() {
        setLayout(new BorderLayout());
        setOpaque(false);

        field = new RoundedTextField("Select date");
        field.setEditable(false);
        field.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        popup = new JPopupMenu();
        popup.setBorder(BorderFactory.createEmptyBorder());
        popup.setBackground(Color.WHITE);

        calendar = new CalendarPanel(date -> {
            field.setText(formatter.format(date));
            popup.setVisible(false);
        });

        popup.add(calendar);

        field.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                popup.show(field, 0, field.getHeight());
            }
        });

        add(field, BorderLayout.CENTER);
        setPreferredSize(new Dimension(260, 42));
    }

    public Date getDate() {
        try {
            return formatter.parse(field.getText());
        } catch (Exception e) {
            return null;
        }
    }

    public void setDate(Date date) {
        field.setText(formatter.format(date));
    }
}
