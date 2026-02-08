package javaswingdev.roundeddatepicker;

import java.awt.*;
import java.time.*;
import java.util.Date;
import java.util.function.Consumer;
import javax.swing.*;

public class CalendarPanel extends JPanel {

    private YearMonth currentMonth;
    private final Consumer<Date> onSelect;

    private final JPanel daysPanel = new JPanel(new GridLayout(0, 7, 6, 6));

    private final JLabel lblMonth = new JLabel("", SwingConstants.CENTER);

    public CalendarPanel(Consumer<Date> onSelect) {
        this.onSelect = onSelect;
        this.currentMonth = YearMonth.now();

        setLayout(new BorderLayout(0, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setPreferredSize(new Dimension(260, 260));

        lblMonth.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton prev = createNavButton("<");
        JButton next = createNavButton(">");

        prev.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            refresh();
        });

        next.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            refresh();
        });

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(prev, BorderLayout.WEST);
        header.add(lblMonth, BorderLayout.CENTER);
        header.add(next, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);
        add(daysPanel, BorderLayout.CENTER);

        refresh();
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setBorder(null);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void refresh() {
        daysPanel.removeAll();

        lblMonth.setText(
            currentMonth.getMonth().name().substring(0, 1)
            + currentMonth.getMonth().name().substring(1).toLowerCase()
            + " " + currentMonth.getYear()
        );

        LocalDate firstDay = currentMonth.atDay(1);
        int startDay = firstDay.getDayOfWeek().getValue() % 7;

        for (int i = 0; i < startDay; i++) {
            daysPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= currentMonth.lengthOfMonth(); day++) {
            int d = day;
            JLabel lblDay = new JLabel(String.valueOf(day), SwingConstants.CENTER);
            lblDay.setOpaque(true);
            lblDay.setBackground(Color.WHITE);
            lblDay.setBorder(BorderFactory.createLineBorder(
                new Color(230, 230, 230)
            ));
            lblDay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            lblDay.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    LocalDate selected =
                        currentMonth.atDay(d);
                    onSelect.accept(
                        Date.from(
                            selected.atStartOfDay(
                                ZoneId.systemDefault()
                            ).toInstant()
                        )
                    );
                }
            });

            daysPanel.add(lblDay);
        }

        revalidate();
        repaint();
    }
}
