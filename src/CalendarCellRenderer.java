import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarCellRenderer extends DefaultTableCellRenderer {
    private Calendar currentCalendar;
    private List<Reservation> reservations;

    public CalendarCellRenderer(Calendar currentCalendar, List<Reservation> reservations) {
        this.currentCalendar = currentCalendar;
        this.reservations = reservations;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value != null) {
            int day = (int) value;
            Calendar cellCalendar = new GregorianCalendar(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), day);

            for (Reservation reservation : reservations) {
                if (reservation.getDate().equals((currentCalendar.get(Calendar.MONTH) + 1) + " " + day + ", " + currentCalendar.get(Calendar.YEAR))) {
                    cell.setBackground(Color.YELLOW);
                    break;
                } else {
                    cell.setBackground(Color.WHITE);
                }
            }
        } else {
            cell.setBackground(Color.WHITE);
        }

        return cell;
    }
}
