import javax.swing.*;
import java.awt.*;

public abstract class AbstractPanel extends JPanel {
    public AbstractPanel() {
        setLayout(new GridBagLayout());
    }
}
