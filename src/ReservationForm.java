import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

public class ReservationForm extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> seatComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<Integer> dayComboBox;
    private JComboBox<String> timeComboBox;
    private JTextArea reservationArea;
    private List<Reservation> reservations;
    private Calendar currentCalendar;

    public ReservationForm(List<Reservation> reservations, Calendar currentCalendar) {
        this.reservations = reservations;
        this.currentCalendar = currentCalendar;

        setTitle("Make a Reservation");
        setSize(400, 400);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 160, 25);
        add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 50, 80, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(100, 50, 160, 25);
        add(phoneField);

        JLabel seatLabel = new JLabel("Seat:");
        seatLabel.setBounds(20, 80, 80, 25);
        add(seatLabel);

        seatComboBox = new JComboBox<>(new String[]{"Seat 1", "Seat 2", "Seat 3", "Seat 4"});
        seatComboBox.setBounds(100, 80, 160, 25);
        add(seatComboBox);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(20, 110, 80, 25);
        add(dateLabel);

        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        monthComboBox.setBounds(100, 110, 80, 25);
        add(monthComboBox);

        yearComboBox = new JComboBox<>(new Integer[]{2024});
        yearComboBox.setBounds(190, 110, 80, 25);
        add(yearComboBox);

        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setBounds(20, 140, 80, 25);
        add(dayLabel);

        dayComboBox = new JComboBox<>();
        dayComboBox.setBounds(100, 140, 80, 25);
        add(dayComboBox);

        monthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDayComboBox();
            }
        });

        yearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDayComboBox();
            }
        });

        updateDayComboBox();

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(20, 170, 80, 25);
        add(timeLabel);

        timeComboBox = new JComboBox<>(new String[]{"08:30 AM", "10:00 AM", "11:30 AM", "01:00 PM", "02:30 PM", "04:00 PM", "05:30 PM", "07:00 PM"});
        timeComboBox.setBounds(100, 170, 160, 25);
        add(timeComboBox);

        JButton submitButton = new JButton("Reserve");
        submitButton.setBounds(100, 200, 160, 25);
        add(submitButton);

        reservationArea = new JTextArea();
        reservationArea.setBounds(20, 230, 340, 140);
        add(reservationArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReservation();
            }
        });
    }

    private void updateDayComboBox() {
        int selectedMonth = monthComboBox.getSelectedIndex();
        int selectedYear = (int) yearComboBox.getSelectedItem();

        Calendar tempCalendar = new GregorianCalendar(selectedYear, selectedMonth, 1);
        int daysInMonth = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        dayComboBox.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            dayComboBox.addItem(i);
        }
    }

    private void addReservation() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String seat = (String) seatComboBox.getSelectedItem();
        String date = monthComboBox.getSelectedItem() + " " + dayComboBox.getSelectedItem() + ", " + yearComboBox.getSelectedItem();
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

        ReservationUtils.saveReservations(reservations, Main.FILE_NAME);
    }

    private boolean validateName(String name) {
        return !Pattern.compile("[0-9]").matcher(name).find();
    }

    private boolean validatePhone(String phone) {
        return Pattern.compile("\\d{3}-\\d{4}-\\d{4}").matcher(phone).matches();
    }
}
