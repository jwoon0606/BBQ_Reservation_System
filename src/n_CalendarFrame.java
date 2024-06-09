import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CalendarFrame extends JFrame {
    // Panels
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel informationPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JPanel monthPanel = new JPanel();
    private JPanel calendarPanel = new JPanel();

    // Top Panel Components
    private JLabel title = new JLabel();
    private JButton viewReservationsButton = new JButton();
    private JLabel voidLabel1 = new JLabel();
    private JLabel voidLabel2 = new JLabel();
    private JLabel voidLabel3 = new JLabel();
    // Information Panel Components
    private JLabel inf_title = new JLabel();
    private JTextArea inf_text = new JTextArea();

    // Bottom Panel Components (Calendar Components)
    private JLabel monthLabel = new JLabel();
    private JButton prevButton = new JButton();
    private JButton nextButton = new JButton();
    private Calendar currentMonth = new GregorianCalendar();

    // Reservation form components
    private JTextField nameField, phoneField;
    private JComboBox<String> seatComboBox, timeComboBox;
    private JTextArea reservationArea;

    // List to store reservations
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public CalendarFrame() {
        setTitle("BBQ Reservation Program");
        setSize(700, 800);

        setComponents();
        setTopPanel();
        setBottomPanel();
        setMainPanel();
        setEvents();

        add(mainPanel);

        loadReservations(); // 예약 정보 로드

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

    private void setTopPanel() {
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 2.0;
        gbc.weighty = 0.3;
        topPanel.add(title, gbc);

        setInformationPanel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // title이 두 열에 걸쳐 표시되도록 설정
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        topPanel.add(title, gbc);

        setInformationPanel();

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4; // informationPanel이 두 열에 걸쳐 표시되도록 설정
        gbc.weightx = 1.0;
        gbc.weighty = 0.6;
        topPanel.add(informationPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // voidLabel과 viewReservationsButton이 각각 한 열에 표시되도록 설정
        gbc.weightx = 0.7;
        gbc.weighty = 0.1;
        topPanel.add(voidLabel1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.1;
        topPanel.add(voidLabel2, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        topPanel.add(viewReservationsButton, gbc);
    }

    private void setBottomPanel(){
        monthPanel.add(prevButton);
        monthPanel.add(monthLabel);
        monthPanel.add(nextButton);

        calendarPanel.setLayout(new GridLayout(0, 7));

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(monthPanel, BorderLayout.NORTH);
        bottomPanel.add(calendarPanel, BorderLayout.CENTER);

        updateCalendar();
    }

    private void setInformationPanel() {
        informationPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        informationPanel.add(inf_title, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        informationPanel.add(inf_text, gbc);
    }

    private void setComponents() {
        title.setText("BBQ Reservation System");
        title.setFont(new Font("돋움", Font.BOLD, 22));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        inf_title.setText("Information");
        inf_title.setFont(new Font("돋움", Font.BOLD, 18));
        inf_title.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        inf_text.setText("-> Individuals or organizations who have not applied in advance cannot use it.\n" +
                "-> If the facility is damaged or damaged, it must be reimbursed at actual expenses.\n" +
                "-> Users should maintain order and etiquette and exercise the virtue of yielding to each other, and do not offend the other party.\n" +
                "-> Smoking is completely prohibited.\n" +
                "-> It should be used only for authorized use purposes."
        );
        inf_text.setFont(new Font("돋움", Font.PLAIN, 14));
        inf_text.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        inf_text.setLineWrap(true);
        inf_text.setWrapStyleWord(true);

        voidLabel1.setText("");
        voidLabel2.setText("");
        voidLabel3.setText("");
        viewReservationsButton.setText("View Reservations");

        currentMonth.set(Calendar.DAY_OF_MONTH, 1);

        prevButton.setText("<");
        nextButton.setText(">");

        updateCalendar();
    }

    private void setEvents(){
        prevButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                currentMonth.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });

        nextButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                currentMonth.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });

        viewReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReservations();
            }
        });
    }

    private void updateCalendar() {
        calendarPanel.removeAll();
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        for (String day : days) {
            calendarPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        int firstDayOfWeek = currentMonth.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            JButton btn = new JButton(String.valueOf(day));
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showReservationForm(Integer.parseInt(btn.getText()));
                }
            });
            calendarPanel.add(btn);
        }

        monthLabel.setText(String.format("%1$tB %1$tY", currentMonth.getTime()));
        calendarPanel.revalidate();
        calendarPanel.repaint();
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
        reservationArea.setLineWrap(true); // 자동 줄바꿈 설정
        reservationArea.setWrapStyleWord(true); // 단어 단위로 줄바꿈 설정

        reservationFrame.add(reservationArea);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReservation(day);
            }
        });

        reservationFrame.setVisible(true);

        updateAvailableTimes(day); // 사용 가능한 시간대 업데이트
    }

    private void updateAvailableTimes(int day) {
        String date = (currentMonth.get(Calendar.MONTH) + 1) + "/" + day + "/" + currentMonth.get(Calendar.YEAR);
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
        reservationArea.setText("Reservation made:"+ "\n" + reservation.toString());

        saveReservations(); // 예약 정보 저장

        updateCalendar();
    }

    private boolean validateName(String name) {
        return name.matches("[a-zA-Z가-힣]+");
    }

    private boolean validatePhone(String phone) {
        return phone.matches("\\d{3}-\\d{4}-\\d{4}");
    }

    private void saveReservations() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("reservations.txt", false))) { // 중복 방지 위해 false
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
        reservationListArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // 보기 좋은 폰트 설정
        for (Reservation reservation : reservations) {
            reservationListArea.append(reservation.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(reservationListArea);
        reservationListFrame.add(scrollPane, BorderLayout.CENTER);

        reservationListFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new CalendarFrame();
    }
}
