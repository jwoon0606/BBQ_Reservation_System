import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

public class TimeTableFrame extends JFrame {
    private List<Reservation> reservations;
    private Calendar calendar;
    private int day;
    private JTable timeTable;
    private String[] seats = {"A1", "A2", "A3", "B1", "B2", "B3"};
    private String[] times = {"12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"};
    private int[] selectedCells = {-1, -1};

    public TimeTableFrame(List<Reservation> reservations, Calendar calendar, int day) {
        this.reservations = reservations;
        this.calendar = calendar;
        this.day = day;

        setTitle("Time Table");
        setSize(800, 600);
        setLayout(new BorderLayout());

        initializeTimeTable();
    }

    private void initializeTimeTable() {
        String[] columnNames = new String[seats.length + 1];
        columnNames[0] = "Time";
        System.arraycopy(seats, 0, columnNames, 1, seats.length);

        Object[][] data = new Object[times.length][seats.length + 1];
        for (int i = 0; i < times.length; i++) {
            data[i][0] = times[i];
        }

        timeTable = new JTable(new DefaultTableModel(data, columnNames)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        timeTable.setRowHeight(40);
        timeTable.setGridColor(Color.BLACK);

        // 타임 테이블에서 셀을 클릭하면 선택 상태를 업데이트
        timeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = timeTable.rowAtPoint(e.getPoint());
                int col = timeTable.columnAtPoint(e.getPoint());
                if (col > 0 && (selectedCells[0] == -1 || (selectedCells[0] == row && Math.abs(selectedCells[1] - col) <= 1))) {
                    selectedCells[0] = row;
                    selectedCells[1] = col;
                    updateTableSelection();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(timeTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveSelectedTimeSlot();
            }
        });
        add(reserveButton, BorderLayout.SOUTH);
    }

    private void updateTableSelection() {
        for (int i = 0; i < timeTable.getRowCount(); i++) {
            for (int j = 1; j < timeTable.getColumnCount(); j++) {
                timeTable.setValueAt("", i, j);
            }
        }
        if (selectedCells[0] != -1) {
            timeTable.setValueAt("Selected", selectedCells[0], selectedCells[1]);
        }
    }

    private void reserveSelectedTimeSlot() {
        if (selectedCells[0] != -1 && selectedCells[1] != -1) {
            String seat = seats[selectedCells[1] - 1];
            String time = times[selectedCells[0]];
            JOptionPane.showMessageDialog(this, "Reserved " + seat + " at " + time);
            selectedCells[0] = -1;
            selectedCells[1] = -1;
            updateTableSelection();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a time slot.");
        }
    }
}
