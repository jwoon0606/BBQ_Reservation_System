import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationUtils {
    private static final Pattern RESERVATION_PATTERN = Pattern.compile("Reservation\\{name='(.*?)', phone='(.*?)', seat='(.*?)', date='(.*?)', time='(.*?)'\\}");

    public static void loadReservations(List<Reservation> reservations, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = RESERVATION_PATTERN.matcher(line);
                if (matcher.matches()) {
                    reservations.add(new Reservation(
                        matcher.group(1),
                        matcher.group(2),
                        matcher.group(3),
                        matcher.group(4),
                        matcher.group(5)
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveReservations(List<Reservation> reservations, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { // Overwrite mode
            for (Reservation reservation : reservations) {
                writer.write(reservation.toString());
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
