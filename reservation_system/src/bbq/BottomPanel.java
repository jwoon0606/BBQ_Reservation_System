package bbq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class BottomPanel extends AbstractPanel {
    private JLabel monthLabel;
    private JButton prevButton;
    private JButton nextButton;
    private JPanel calendarPanel;
    private Calendar currentMonth;
    private ActionListener dayButtonListener;

    public BottomPanel(Calendar currentMonth, ActionListener prevButtonListener, ActionListener nextButtonListener, ActionListener dayButtonListener) {
        this.currentMonth = currentMonth;
        this.prevButton = new JButton("<");
        this.nextButton = new JButton(">");
        this.dayButtonListener = dayButtonListener;

        this.prevButton.addActionListener(prevButtonListener);
        this.nextButton.addActionListener(nextButtonListener);

        setComponents();
        setPanel();
    }

    private void setComponents() {
        monthLabel = new JLabel();
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7));

        updateCalendar();
    }

    private void setPanel() {
        JPanel monthPanel = new JPanel();
        monthPanel.add(prevButton);
        monthPanel.add(monthLabel);
        monthPanel.add(nextButton);

        setLayout(new BorderLayout());
        add(monthPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);
    }

    public void updateCalendar() {
        calendarPanel.removeAll();
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        for (String day : days) {
            calendarPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        int firstDayOfWeek = currentMonth.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            JButton btn = new JButton(String.valueOf(day));
            btn.addActionListener(dayButtonListener);
            calendarPanel.add(btn);
        }

        monthLabel.setText(String.format("%1$tB %1$tY", currentMonth.getTime()));
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
}
