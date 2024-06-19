package bbq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CalendarFrame extends JFrame {
    private JPanel mainPanel = new JPanel();
    private Calendar currentMonth = new GregorianCalendar();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private BottomPanel bottomPanel;
    private TopPanel topPanel;

    public CalendarFrame() {
        setTitle("BBQ Reservation Program");
        setSize(700, 800);

        loadReservations(); // Load reservations

        JButton viewReservationsButton = new JButton("View Reservations");
        viewReservationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReservations();
            }
        });

        topPanel = new TopPanel(viewReservationsButton);
        bottomPanel = new BottomPanel(currentMonth, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentMonth.add(Calendar.MONTH, -1);
                bottomPanel.updateCalendar();
            }
        }, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentMonth.add(Calendar.MONTH, 1);
                bottomPanel.updateCalendar();
            }
        }, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                int day = Integer.parseInt(source.getText());
                showReservationForm(day);
            }
        });

        setMainPanel();
        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setMainPanel() {
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        mainPanel.add(topPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        mainPanel.add(bottomPanel, gbc);
    }

    private void showReservationForm(int day) {
        JFrame reservationFrame = new JFrame("Make a Reservation");
        reservationFrame.setSize(400, 400);
        reservationFrame.setLayout(null);

        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JComboBox<String> seatComboBox = new JComboBox<>(new String[]{"Seat 1", "Seat 2", "Seat 3", "Seat 4"});
        JComboBox<String> timeComboBox = new JComboBox<>(new String[]{"08:30 AM", "10:00 AM", "11:30 AM", "01:00 PM", "02:30 PM", "04:00 PM", "05:30 PM", "07:00 PM"});
        JTextArea reservationArea = new JTextArea();

        reservationFrame.add(new JLabel("Name:")).setBounds(20, 20, 80, 25);
        nameField.setBounds(100, 20, 160, 25);
        reservationFrame.add(nameField);

        reservationFrame.add(new JLabel("Phone:")).setBounds(20, 50, 80, 25);
        phoneField.setBounds(100, 50, 160, 25);
        reservationFrame.add(phoneField);

        reservationFrame.add(new JLabel("Seat:")).setBounds(20, 80, 80, 25);
        seatComboBox.setBounds(100, 80, 160, 25);
        reservationFrame.add(seatComboBox);

        reservationFrame.add(new JLabel("Time:")).setBounds(20, 110, 80, 25);
        timeComboBox.setBounds(100, 110, 160, 25);
        reservationFrame.add(timeComboBox);

        JButton submitButton = new JButton("Reserve");
        submitButton.setBounds(100, 140, 160, 25);
        reservationFrame.add(submitButton);

        reservationArea.setBounds(20, 170, 360, 150);
        reservationArea.setLineWrap(true);
        reservationArea.setWrapStyleWord(true);
        reservationFrame.add(reservationArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReservation(day, nameField, phoneField, seatComboBox, timeComboBox, reservationArea);
            }
        });

        reservationFrame.setVisible(true);
    }

    private void addReservation(int day, JTextField nameField, JTextField phoneField, JComboBox<String> seatComboBox, JComboBox<String> timeComboBox, JTextArea reservationArea) {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String seat = (String) seatComboBox.getSelectedItem();
        String date = (currentMonth.get(Calendar.MONTH) + 1) + "/" + day + "/" + currentMonth.get(Calendar.YEAR);
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
        reservationArea.setText("Reservation made:\n" + reservation.toString());

        saveReservations(); // Save reservations
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
                String[] parts = line.split(",");
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
        reservationListArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        StringBuilder str = new StringBuilder();
        for (Reservation reservation : reservations) {
            str.append(reservation.toString()).append("\n");
        }
        reservationListArea.setText(str.toString());
        JScrollPane scrollPane = new JScrollPane(reservationListArea);
        reservationListFrame.add(scrollPane, BorderLayout.CENTER);

        reservationListFrame.setVisible(true);
    }
}
