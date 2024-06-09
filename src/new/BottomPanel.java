import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BottomPanel extends AbstractPanel {
    private JLabel monthLabel = new JLabel();
    private JButton prevButton = new JButton();
    private JButton nextButton = new JButton();
    private Calendar currentMonth = new GregorianCalendar();
    private JPanel calendarPanel = new JPanel();

    public BottomPanel() {
        setBottomPanel();
        setEvents();
        updateCalendar();
    }

    private void setBottomPanel() {
        JPanel monthPanel = new JPanel();
        monthPanel.add(prevButton);
        monthPanel.add(monthLabel);
        monthPanel.add(nextButton);

        calendarPanel.setLayout(new GridLayout(0, 7));

        setLayout(new BorderLayout());
        add(monthPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);
    }

    private void setEvents() {
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentMonth.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentMonth.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });
    }

    private void updateCalendar() {
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
            calendarPanel.add(btn);
        }

        monthLabel.setText(String.format("%1$tB %1$tY", currentMonth.getTime()));
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    public Calendar getCurrentMonth() {
        return currentMonth;
    }

    public JPanel getCalendarPanel() {
        return calendarPanel;
    }

    public JLabel getMonthLabel() {
        return monthLabel;
    }

    public JButton getPrevButton() {
        return prevButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }
}
