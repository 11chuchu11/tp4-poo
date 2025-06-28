package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.YearMonth;

public class DatePicker extends JDialog {
    private LocalDate selectedDate;
    private final JTextField targetField;
    private final JComboBox<Integer> comboAnio;
    private final JComboBox<Integer> comboMes;
    private final JPanel daysPanel;

    public DatePicker(JFrame parent, JTextField targetField) {
        super(parent, "Seleccionar fecha", true);
        this.targetField = targetField;
        setLayout(new BorderLayout());
        setSize(320, 260);
        setLocationRelativeTo(parent);

        JPanel topPanel = new JPanel();
        comboAnio = new JComboBox<>();
        for (int y = 2024; y <= 2030; y++) comboAnio.addItem(y);
        comboMes = new JComboBox<>();
        for (int m = 1; m <= 12; m++) comboMes.addItem(m);

        topPanel.add(new JLabel("Año:"));
        topPanel.add(comboAnio);
        topPanel.add(new JLabel("Mes:"));
        topPanel.add(comboMes);

        add(topPanel, BorderLayout.NORTH);

        daysPanel = new JPanel(new GridLayout(0, 7, 2, 2));
        add(daysPanel, BorderLayout.CENTER);

        comboAnio.addActionListener(e -> updateCalendar());
        comboMes.addActionListener(e -> updateCalendar());

        updateCalendar();
    }

    private void updateCalendar() {
        daysPanel.removeAll();
        int year = (int) comboAnio.getSelectedItem();
        int month = (int) comboMes.getSelectedItem();
        YearMonth ym = YearMonth.of(year, month);

        String[] dias = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
        for (String d : dias) {
            JLabel lbl = new JLabel(d, SwingConstants.CENTER);
            lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
            daysPanel.add(lbl);
        }

        LocalDate firstDay = ym.atDay(1);
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue(); // 1-7

        for (int i = 1; i < firstDayOfWeek; i++) {
            daysPanel.add(new JLabel(""));
        }

        for (int d = 1; d <= ym.lengthOfMonth(); d++) {
            JButton btn = new JButton(String.valueOf(d));
            btn.addActionListener(e -> {
                selectedDate = LocalDate.of(year, month, Integer.parseInt(btn.getText()));
                targetField.setText(selectedDate.toString());
                dispose();
            });
            daysPanel.add(btn);
        }

        int totalCells = ((firstDayOfWeek - 1) + ym.lengthOfMonth());
        int extra = (7 - (totalCells % 7)) % 7;
        for (int i = 0; i < extra; i++) {
            daysPanel.add(new JLabel(""));
        }

        daysPanel.revalidate();
        daysPanel.repaint();
    }

    public static void attachTo(JTextField field, JFrame parent) {
        field.setEditable(false);
        field.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DatePicker picker = new DatePicker(parent, field);
                picker.setVisible(true);
            }
        });
    }
}