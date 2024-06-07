import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Main extends JFrame {
    public static final String FILE_NAME = "test.txt";
    private List<Reservation> reservations;
    private JTable calendarTable;
    private Calendar currentCalendar;
    private JPanel loginPanel;
    private JPanel mainPanel;
    private JTextArea reservationDetailsArea;

    public Main() {
        reservations = new ArrayList<>();
        currentCalendar = new GregorianCalendar();

        ReservationUtils.loadReservations(reservations, FILE_NAME);

        setTitle("BBQ Seat Reservation System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        initializeLoginPanel();
        initializeMainPanel();

        add(loginPanel, "login");
        add(mainPanel, "main");

        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "login");
    }

    private void initializeLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel loginNameLabel = new JLabel("Name:");
        loginNameLabel.setBounds(20, 20, 80, 25);
        loginPanel.add(loginNameLabel);

        JTextField loginNameField = new JTextField();
        loginNameField.setBounds(100, 20, 160, 25);
        loginPanel.add(loginNameField);

        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPasswordLabel.setBounds(20, 50, 80, 25);
        loginPanel.add(loginPasswordLabel);

        JPasswordField loginPasswordField = new JPasswordField();
        loginPasswordField.setBounds(100, 50, 160, 25);
        loginPanel.add(loginPasswordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 160, 25);
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "main");
            }
        });
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.setBounds(20, 20, 160, 25);
        mainPanel.add(reserveButton);

        JButton viewReservationsButton = new JButton("View Reservations");
        viewReservationsButton.setBounds(20, 50, 160, 25);
        mainPanel.add(viewReservationsButton);

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReservationForm();
            }
        });

        viewReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservationUtils.showReservationStatus(reservations);
            }
        });

        initializeCalendar();
    }

    private void initializeCalendar() {
        currentCalendar = new GregorianCalendar();

        calendarTable = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        calendarTable.setRowHeight(40);
        CalendarCellRenderer renderer = new CalendarCellRenderer(currentCalendar, reservations);
        calendarTable.setDefaultRenderer(Object.class, renderer);

        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = calendarTable.rowAtPoint(e.getPoint());
                int col = calendarTable.columnAtPoint(e.getPoint());
                Object value = calendarTable.getValueAt(row, col);
                if (value != null) {
                    int day = (int) value;
                    renderer.setSelectedDay(day);
                    updateCalendar();
                    showTimeTable(day);
                }
            }
        });

        updateCalendar();

        JScrollPane calendarScrollPane = new JScrollPane(calendarTable);
        calendarScrollPane.setBounds(20, 100, 740, 400);
        mainPanel.add(calendarScrollPane);

        JLabel currentDateLabel = new JLabel("Today: " + (currentCalendar.get(Calendar.MONTH) + 1) + "/" + currentCalendar.get(Calendar.DAY_OF_MONTH) + "/" + currentCalendar.get(Calendar.YEAR));
        currentDateLabel.setBounds(20, 70, 200, 25);
        mainPanel.add(currentDateLabel);

        reservationDetailsArea = new JTextArea();
        reservationDetailsArea.setBounds(20, 510, 740, 60);
        reservationDetailsArea.setEditable(false);
        mainPanel.add(reservationDetailsArea);
    }

    private void showReservationForm() {
        // 기존 showReservationForm 메서드를 삭제합니다.
    }

    private void updateCalendar() {
        String[] columnNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        Object[][] data = new Object[6][7];

        Calendar calendar = new GregorianCalendar(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int row = 0;
        int column = firstDayOfWeek - 1;
        for (int day = 1; day <= daysInMonth; day++) {
            data[row][column] = day;
            column++;
            if (column == 7) {
                column = 0;
                row++;
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        calendarTable.setModel(model);
    }

    private void showTimeTable(int day) {
        TimeTableFrame timeTableFrame = new TimeTableFrame(reservations, currentCalendar, day);
        timeTableFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
