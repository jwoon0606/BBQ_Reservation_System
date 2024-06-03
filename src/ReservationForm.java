import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

public class ReservationForm extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> seatComboBox;
    private JComboBox<String> timeComboBox;
    private JButton reserveButton;
    private Calendar calendar;
    private List<Reservation> reservations;

    public ReservationForm(List<Reservation> reservations, Calendar calendar) {
        this.reservations = reservations;
        this.calendar = calendar;

        setTitle("Make a Reservation");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        add(nameLabel);
        nameField = new JTextField();
        add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        add(phoneLabel);
        phoneField = new JTextField();
        add(phoneField);

        JLabel seatLabel = new JLabel("Seat:");
        add(seatLabel);
        String[] seats = {"A1", "A2", "A3", "B1", "B2", "B3"};
        seatComboBox = new JComboBox<>(seats);
        add(seatComboBox);

        JLabel timeLabel = new JLabel("Time:");
        add(timeLabel);
        String[] times = {"12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"};
        timeComboBox = new JComboBox<>(times);
        add(timeComboBox);

        reserveButton = new JButton("Reserve");
        add(reserveButton);
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeReservation();
            }
        });
    }

    private void makeReservation() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String seat = (String) seatComboBox.getSelectedItem();
        String time = (String) timeComboBox.getSelectedItem();
        String date = (calendar.get(Calendar.MONTH) + 1) + " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " + calendar.get(Calendar.YEAR);

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Reservation reservation = new Reservation(name, phone, seat, time, date);
        reservations.add(reservation);
        ReservationUtils.saveReservations(reservations, Main.FILE_NAME);

        JOptionPane.showMessageDialog(this, "Reservation made successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
