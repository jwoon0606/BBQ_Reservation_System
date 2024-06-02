import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class ReservationUtils {
    public static void loadReservations(List<Reservation> reservations, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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

    public static void saveReservations(List<Reservation> reservations, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Reservation reservation : reservations) {
                writer.write(reservation.getName() + "," + reservation.getPhone() + "," + reservation.getSeat() + "," + reservation.getDate() + "," + reservation.getTime());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showReservationStatus(List<Reservation> reservations) {
        JFrame statusFrame = new JFrame("Reservation Status");
        statusFrame.setSize(600, 400);
        statusFrame.setLayout(new BorderLayout());

        String[] columnNames = {"Name", "Phone", "Seat", "Date", "Time"};
        Object[][] data = new Object[reservations.size()][5];
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            data[i][0] = reservation.getName();
            data[i][1] = reservation.getPhone();
            data[i][2] = reservation.getSeat();
            data[i][3] = reservation.getDate();
            data[i][4] = reservation.getTime();
        }

        JTable reservationTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        statusFrame.add(scrollPane, BorderLayout.CENTER);

        statusFrame.setVisible(true);
    }
}
