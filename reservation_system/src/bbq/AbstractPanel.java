package bbq;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPanel extends JPanel {
    protected GridBagConstraints gbc;

    public AbstractPanel() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
    }

    protected void addComponent(JComponent component, int x, int y, int width, int height, double weightx, double weighty) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        add(component, gbc);
    }
}
