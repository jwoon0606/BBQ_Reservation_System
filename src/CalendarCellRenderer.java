import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarCellRenderer extends DefaultTableCellRenderer {
    private Calendar currentCalendar;
    private List<Reservation> reservations;
    private int selectedDay = -1; // 선택된 날짜를 저장할 변수

    public CalendarCellRenderer(Calendar currentCalendar, List<Reservation> reservations) {
        this.currentCalendar = currentCalendar;
        this.reservations = reservations;
    }

    public void setSelectedDay(int day) {
        this.selectedDay = day;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value != null) {
            int day = (int) value;
            Calendar cellCalendar = new GregorianCalendar(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), day);

            boolean hasReservation = false;
            for (Reservation reservation : reservations) {
                if (reservation.getDate().equals((currentCalendar.get(Calendar.MONTH) + 1) + " " + day + ", " + currentCalendar.get(Calendar.YEAR))) {
                    hasReservation = true;
                    break;
                }
            }

            if (day == selectedDay) {
                cell.setBackground(Color.CYAN); // 선택된 날짜를 표시
            } else if (hasReservation) {
                //cell.setBackground(Color.YELLOW); // 예약이 있는 날짜를 표시
            } else {
                cell.setBackground(Color.WHITE); // 기본 배경
            }
        } else {
            cell.setBackground(Color.WHITE);
        }

        return cell;
    }
}
