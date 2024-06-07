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
    private int day;
    private String time;
    private String seat;

    public ReservationForm(List<Reservation> reservations, Calendar calendar, int day, String time, String seat) {
        this.reservations = reservations;
        this.calendar = calendar;
        this.day = day;
        this.time = time;
        this.seat = seat;

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
        add(new JLabel(seat));

        JLabel timeLabel = new JLabel("Time:");
        add(timeLabel);
        add(new JLabel(time));

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
        String date = (calendar.get(Calendar.MONTH) + 1) + " " + day + ", " + calendar.get(Calendar.YEAR);

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Reservation reservation = new Reservation(name, phone, seat, date, time);
        reservations.add(reservation);
        ReservationUtils.saveReservations(reservations, Main.FILE_NAME);

        JOptionPane.showMessageDialog(this, "Reservation made successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
