import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

public class BBQReservationSystem extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> seatComboBox;
    private JComboBox<String> timeComboBox;
    private JButton reserveButton;
    private JTextArea reservationArea;
    private List<Reservation> reservations;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private JTable calendarTable;
    private Calendar currentCalendar;
    private String selectedDate;
    private JButton loginButton;
    private JTextField loginNameField;
    private JPasswordField loginPasswordField;
    private JPanel loginPanel;
    private JPanel mainPanel;

    public BBQReservationSystem() {
        reservations = new ArrayList<>();
        currentCalendar = new GregorianCalendar();

        setTitle("BBQ Seat Reservation System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        initializeLoginPanel();
        initializeMainPanel();

        add(loginPanel, "login");
        add(mainPanel, "main");

        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "login");
    }

    private void initializeLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel loginNameLabel = new JLabel("Name:");
        loginNameLabel.setBounds(20, 20, 80, 25);
        loginPanel.add(loginNameLabel);

        loginNameField = new JTextField();
        loginNameField.setBounds(100, 20, 160, 25);
        loginPanel.add(loginNameField);

        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPasswordLabel.setBounds(20, 50, 80, 25);
        loginPanel.add(loginPasswordLabel);

        loginPasswordField = new JPasswordField();
        loginPasswordField.setBounds(100, 50, 160, 25);
        loginPanel.add(loginPasswordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 160, 25);
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 간단한 로그인 검증 (생략)
                ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "main");
            }
        });
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.setBounds(20, 20, 160, 25);
        mainPanel.add(reserveButton);

        JButton viewReservationsButton = new JButton("View Reservations");
        viewReservationsButton.setBounds(20, 50, 160, 25);
        mainPanel.add(viewReservationsButton);

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReservationForm();
            }
        });

        viewReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReservationStatus();
            }
        });

        initializeCalendar();
    }

    private void initializeCalendar() {
        currentCalendar = new GregorianCalendar();

        calendarTable = new JTable();
        calendarTable.setRowHeight(40);
        calendarTable.setDefaultRenderer(Object.class, new CalendarCellRenderer());

        updateCalendar();

        JScrollPane calendarScrollPane = new JScrollPane(calendarTable);
        calendarScrollPane.setBounds(20, 100, 740, 400);
        mainPanel.add(calendarScrollPane);
    }

    private void showReservationForm() {
        JFrame reservationFrame = new JFrame("Make a Reservation");
        reservationFrame.setSize(400, 400);
        reservationFrame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        reservationFrame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 160, 25);
        reservationFrame.add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 50, 80, 25);
        reservationFrame.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(100, 50, 160, 25);
        reservationFrame.add(phoneField);

        JLabel seatLabel = new JLabel("Seat:");
        seatLabel.setBounds(20, 80, 80, 25);
        reservationFrame.add(seatLabel);

        seatComboBox = new JComboBox<>(new String[]{"Seat 1", "Seat 2", "Seat 3"});
        seatComboBox.setBounds(100, 80, 160, 25);
        reservationFrame.add(seatComboBox);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(20, 110, 80, 25);
        reservationFrame.add(dateLabel);

        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        monthComboBox.setBounds(100, 110, 80, 25);
        reservationFrame.add(monthComboBox);

        yearComboBox = new JComboBox<>(new Integer[]{2023, 2024, 2025});
        yearComboBox.setBounds(190, 110, 80, 25);
        reservationFrame.add(yearComboBox);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(20, 140, 80, 25);
        reservationFrame.add(timeLabel);

        timeComboBox = new JComboBox<>(new String[]{"12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"});
        timeComboBox.setBounds(100, 140, 160, 25);
        reservationFrame.add(timeComboBox);

        JButton submitButton = new JButton("Reserve");
        submitButton.setBounds(100, 170, 160, 25);
        reservationFrame.add(submitButton);

        reservationArea = new JTextArea();
        reservationArea.setBounds(20, 200, 340, 140);
        reservationFrame.add(reservationArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReservation();
            }
        });

        reservationFrame.setVisible(true);
    }

    private void showReservationStatus() {
        JFrame statusFrame = new JFrame("Reservation Status");
        statusFrame.setSize(400, 400);
        statusFrame.setLayout(new BorderLayout());

        JTextArea statusArea = new JTextArea();
        for (Reservation reservation : reservations) {
            statusArea.append(reservation.toString() + "\n");
        }

        statusFrame.add(new JScrollPane(statusArea), BorderLayout.CENTER);
        statusFrame.setVisible(true);
    }

    private void addReservation() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String seat = (String) seatComboBox.getSelectedItem();
        String date = monthComboBox.getSelectedItem() + " " + yearComboBox.getSelectedItem();
        String time = (String) timeComboBox.getSelectedItem();

        if (!validateName(name)) {
            reservationArea.setText("Invalid name. Name should not contain numbers.");
            return;
        }

        if (!validatePhone(phone)) {
            reservationArea.setText("Invalid phone number. Format should be 000-0000-0000.");
            return;
        }

        for (Reservation reservation : reservations) {
            if (reservation.getDate().equals(date) && reservation.getTime().equals(time) && reservation.getSeat().equals(seat)) {
                reservationArea.setText("This seat is already reserved for the selected date and time.");
                return;
            }
        }

        Reservation reservation = new Reservation(name, phone, seat, date, time);
        reservations.add(reservation);
        reservationArea.setText("Reservation made: " + reservation.toString());

        updateCalendar();
    }

    private boolean validateName(String name) {
        return !name.matches(".*\\d.*");
    }

    private boolean validatePhone(String phone) {
        return Pattern.matches("\\d{3}-\\d{4}-\\d{4}", phone);
    }

    private void updateCalendar() {
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        DefaultTableModel model = new DefaultTableModel(null, days);
        calendarTable.setModel(model);

        currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int startDay = currentCalendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Object[][] calendarData = new Object[6][7];
        int day = 1;

        for (int i = startDay - 1; i < startDay - 1 + numberOfDays; i++) {
            int row = i / 7;
            int col = i % 7;
            calendarData[row][col] = day;
            day++;
        }

        for (Object[] week : calendarData) {
            model.addRow(week);
        }

        calendarTable.setDefaultRenderer(Object.class, new CalendarCellRenderer());
    }

    private class CalendarCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null) {
                int day = (int) value;
                String date = (currentCalendar.get(Calendar.MONTH) + 1) + "/" + day + "/" + currentCalendar.get(Calendar.YEAR);
                long count = reservations.stream().filter(r -> r.getDate().equals(date)).count();

                if (count > 0) {
                    cell.setBackground(Color.PINK);
                    setText(value.toString() + " (" + count + ")");
                } else {
                    cell.setBackground(Color.WHITE);
                }
            } else {
                cell.setBackground(Color.WHITE);
            }

            return cell;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BBQReservationSystem().setVisible(true);
            }
        });
    }
}
