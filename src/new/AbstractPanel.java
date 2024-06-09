import javax.swing.*;
import java.awt.*;

public abstract class AbstractPanel extends JPanel {
    protected GridBagConstraints gbc = new GridBagConstraints();

    public AbstractPanel() {
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
    }
}
