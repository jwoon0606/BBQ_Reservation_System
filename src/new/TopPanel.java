import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TopPanel extends AbstractPanel {
    private JLabel title = new JLabel();
    private JButton viewReservationsButton = new JButton();
    private JLabel voidLabel1 = new JLabel();
    private JLabel voidLabel2 = new JLabel();
    private JLabel voidLabel3 = new JLabel();
    private JPanel informationPanel = new JPanel();
    private JLabel inf_title = new JLabel();
    private JTextArea inf_text = new JTextArea();

    public TopPanel() {
        setComponents();
        setTopPanel();
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

        setInformationPanel();

        viewReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReservations();
            }
        });
    }

    private void setTopPanel() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 2.0;
        gbc.weighty = 0.3;
        add(title, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6;
        add(informationPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.1;
        add(voidLabel1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.1;
        add(voidLabel2, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        add(viewReservationsButton, gbc);
    }

    private void setInformationPanel() {
        informationPanel.setLayout(new GridBagLayout());
        GridBagConstraints inf_gbc = new GridBagConstraints();
        inf_gbc.fill = GridBagConstraints.BOTH;

        inf_gbc.gridx = 0;
        inf_gbc.gridy = 0;
        inf_gbc.weightx = 1.0;
        inf_gbc.weighty = 0.1;
        informationPanel.add(inf_title, inf_gbc);

        inf_gbc.gridx = 0;
        inf_gbc.gridy = 1;
        inf_gbc.weightx = 1.0;
        inf_gbc.weighty = 0.9;
        informationPanel.add(inf_text, inf_gbc);
    }

    public JButton getViewReservationsButton() {
        return viewReservationsButton;
    }
}
