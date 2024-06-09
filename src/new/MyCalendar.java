import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyCalendar extends JFrame {
    private BottomPanel bottomPanel;
    private ArrayList<Reservation> reservations;
    private JTextArea reservationArea;
    private JTextField nameField, phoneField;
    private JComboBox<String> seatComboBox, timeComboBox;

    public MyCalendar(ArrayList<Reservation> reservations) {
        this.reservations = reservations;

        setTitle("My Calendar");
        setSize(700, 800);

        bottomPanel = new BottomPanel();
        add(bottomPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addEventListeners();
    }

    private void addEventListeners() {
        for (int i = 1; i <= bottomPanel.getCalendarPanel().getComponentCount(); i++) {
            if (bottomPanel.getCalendarPanel().getComponent(i - 1) instanceof JButton) {
                JButton btn = (JButton) bottomPanel.getCalendarPanel().getComponent(i - 1);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showReservationForm(Integer.parseInt(btn.getText()));
                    }
                });
            }
        }
    }

    private void showReservationForm(int day) {
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

        seatComboBox = new JComboBox<>(new String[]{"Seat 1", "Seat 2", "Seat 3", "Seat 4"});
        seatComboBox.setBounds(100, 80, 160, 25);
        reservationFrame.add(seatComboBox);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(20, 110, 80, 25);
        reservationFrame.add(timeLabel);

        timeComboBox = new JComboBox<>(new String[]{"08:30 AM", "10:00 AM", "11:30 AM", "01:00 PM", "02:30 PM", "04:00 PM", "05:30 PM", "07:00 PM"});
        timeComboBox.setBounds(100, 110, 160, 25);
        reservationFrame.add(timeComboBox);

        JButton submitButton = new JButton("Reserve");
        submitButton.setBounds(100, 140, 160, 25);
        reservationFrame.add(submitButton);

        reservationArea = new JTextArea();
        reservationArea.setBounds(20, 170, 360, 150);
        reservationArea.setLineWrap(true); 
        reservationArea.setWrapStyleWord(true); 

        reservationFrame.add(reservationArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReservation(day);
            }
        });

        reservationFrame.setVisible(true);

        updateAvailableTimes(day); 
    }

    private void updateAvailableTimes(int day) {
        String date = (bottomPanel.getCurrentMonth().get(Calendar.MONTH) + 1) + "/" + day + "/" + bottomPanel.getCurrentMonth().get(Calendar.YEAR);
        Map<String, Set<String>> seatToReservedTimes = new HashMap<>();

        for (Reservation reservation : reservations) {
            if (reservation.getDate().equals(date)) {
                String seat = reservation.getSeat();
                String time = reservation.getTime();

                if (!seatToReservedTimes.containsKey(seat)) {
                    seatToReservedTimes.put(seat, new HashSet<>());
                }
                seatToReservedTimes.get(seat).add(time);
            }
        }

        String selectedSeat = (String) seatComboBox.getSelectedItem();
        if (seatToReservedTimes.containsKey(selectedSeat)) {
            Set<String> reservedTimes = seatToReservedTimes.get(selectedSeat);
            for (int i = 0; i < timeComboBox.getItemCount(); i++) {
                String time = timeComboBox.getItemAt(i);
                if (reservedTimes.contains(time)) {
                    timeComboBox.removeItem(time);
                    i--;
                }
            }
        }
    }

    private void addReservation(int day) {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String seat = (String) seatComboBox.getSelectedItem();
        String date = (bottomPanel.getCurrentMonth().get(Calendar.MONTH) + 1) + "/" + day + "/" + bottomPanel.getCurrentMonth().get(Calendar.YEAR);
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
        reservationArea.setText("Reservation made:"+ "\n" + reservation.toString());

        saveReservations();

        bottomPanel.updateCalendar();
    }

    private boolean validateName(String name) {
        return name.matches("[a-zA-Z가-힣]+");
    }

    private boolean validatePhone(String phone) {
        return phone.matches("\\d{3}-\\d{4}-\\d{4}");
    }

    private void saveReservations() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("reservations.txt", false))) {
            for (Reservation reservation : reservations) {
                writer.println(reservation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadReservations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 5) {
                    reservations.add(new Reservation(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showReservations() {
        JFrame reservationListFrame = new JFrame("Reservations List");
        reservationListFrame.setSize(400, 400);
        reservationListFrame.setLayout(new BorderLayout());

        JTextArea reservationListArea = new JTextArea();
        reservationListArea.setEditable(false);
        reservationListArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        for (Reservation reservation : reservations) {
            reservationListArea.append(reservation.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(reservationListArea);
        reservationListFrame.add(scrollPane, BorderLayout.CENTER);

        reservationListFrame.setVisible(true);
    }
}
