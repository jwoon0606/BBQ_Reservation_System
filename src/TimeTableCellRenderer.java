import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Calendar;
import java.util.List;

public class TimeTableCellRenderer extends DefaultTableCellRenderer {
    private List<Reservation> reservations;
    private Calendar calendar;
    private int day;

    public TimeTableCellRenderer(List<Reservation> reservations, Calendar calendar, int day) {
        this.reservations = reservations;
        this.calendar = calendar;
        this.day = day;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column >= 1) {
            String time = (String) table.getValueAt(row, 0);
            String seat = (String) table.getColumnName(column);

            boolean reserved = false;
            for (Reservation reservation : reservations) {
                String[] dateParts = reservation.getDate().split(" ");
                int reservationDay = Integer.parseInt(dateParts[1].replace(",", ""));
                if (reservationDay == day && (calendar.get(Calendar.MONTH) + 1) == Integer.parseInt(dateParts[0]) && reservation.getTime().equals(time) && reservation.getSeat().equals(seat)) {
                    reserved = true;
                    break;
                }
            }

            if (reserved) {
                cell.setBackground(Color.YELLOW);
            } else {
                cell.setBackground(Color.WHITE);
            }
        } else {
            cell.setBackground(Color.WHITE);
        }

        return cell;
    }
}
