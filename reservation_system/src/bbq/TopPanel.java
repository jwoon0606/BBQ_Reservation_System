package bbq;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends AbstractPanel {
    private JLabel title;
    private JLabel infTitle;
    private JTextArea infText;
    private JButton viewReservationsButton;

    public TopPanel(JButton viewReservationsButton) {
        this.viewReservationsButton = viewReservationsButton;
        setComponents();
        setPanel();
    }

    private void setComponents() {
        title = new JLabel("BBQ Reservation System");
        title.setFont(new Font("돋움", Font.BOLD, 22));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        infTitle = new JLabel("Information");
        infTitle.setFont(new Font("돋움", Font.BOLD, 18));
        infTitle.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        infText = new JTextArea("-> Individuals or organizations who have not applied in advance cannot use it.\n" +
                "-> If the facility is damaged or damaged, it must be reimbursed at actual expenses.\n" +
                "-> Users should maintain order and etiquette and exercise the virtue of yielding to each other, and do not offend the other party.\n" +
                "-> Smoking is completely prohibited.\n" +
                "-> It should be used only for authorized use purposes.");
        infText.setFont(new Font("돋움", Font.PLAIN, 14));
        infText.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        infText.setLineWrap(true);
        infText.setWrapStyleWord(true);
    }

    private void setPanel() {
        addComponent(title, 0, 0, 4, 1, 1.0, 0.3);

        addComponent(infTitle, 0, 1, 4, 1, 1.0, 0.1);
        addComponent(infText, 0, 2, 4, 1, 1.0, 0.6);

        addComponent(new JLabel(""), 0, 3, 1, 1, 0.7, 0.1);
        addComponent(new JLabel(""), 1, 3, 1, 1, 0.7, 0.1);
        addComponent(viewReservationsButton, 3, 3, 1, 1, 0.1, 0.1);
    }
}
