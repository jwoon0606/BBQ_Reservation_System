import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationManager {
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private MyCalendar calendarFrame;

    public ReservationManager() {
        loadReservations();
        calendarFrame = new MyCalendar(reservations);
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

    public void showReservations() {
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
