import javax.swing.*;
import java.awt.*;

public class BottomPanel extends AbstractPanel {
    private JLabel monthLabel;
    private JButton prevButton;
    private JButton nextButton;
    private JPanel monthPanel;
    private JPanel calendarPanel;

    public BottomPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        monthPanel = new JPanel();
        prevButton = new JButton("<");
        monthLabel = new JLabel();
        nextButton = new JButton(">");
        monthPanel.add(prevButton);
        monthPanel.add(monthLabel);
        monthPanel.add(nextButton);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(monthPanel, gbc);

        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        add(calendarPanel, gbc);
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

    public JPanel getCalendarPanel() {
        return calendarPanel;
    }
}
