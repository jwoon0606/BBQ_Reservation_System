import javax.swing.*;
import java.awt.*;

public class CalendarFrame extends JFrame{
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel informationPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    // Top Panel Components
    JLabel title = new JLabel();

    // Information Panel Components
    JLabel inf_title = new JLabel();
    JTextArea inf_text = new JTextArea();

    // Bottom Panel Components
    JLabel temp = new JLabel();

    public CalendarFrame(){
        setTitle("BBQ Reservation Program");
        setSize(1200,800);

        setComponents();
        setTopPanel();      // 달력 위쪽 부분
        setBottomPanel();   // 달력 부분
        setMainPanel();     // 전체 패널
        // setEvents();    // 이벤트 처리

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void setTopPanel(){
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy=0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        topPanel.add(title,gbc);

        setInformationPanel();

        gbc.gridx = 0;
        gbc.gridy=1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.7;
        topPanel.add(informationPanel,gbc);
    }

    private void setInformationPanel(){
        informationPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy=0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        informationPanel.add(inf_title,gbc);

        gbc.gridx = 0;
        gbc.gridy=1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        informationPanel.add(inf_text,gbc);
    }

    private void setBottomPanel(){
        bottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy=0;
        bottomPanel.add(temp,gbc);
    }
    private void setMainPanel() {
        // Top Panel Settings
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy=0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        mainPanel.add(topPanel,gbc);

        gbc.gridx = 0;
        gbc.gridy=1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        mainPanel.add(bottomPanel,gbc);
    }

    private void setComponents() {
        // 제목
        title.setText("BBQ Reservatin System(직접 만든 ver)");
        title.setFont(new Font("돋움", Font.BOLD, 22));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        // 이용 안내문의 제목 (이용 안내)
        inf_title.setText("이용 안내");
        inf_title.setFont(new Font("돋움", Font.BOLD, 18));
        inf_title.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        // 이용 안내
        inf_text.setText("-> 사전신청을 하지 않은 개인 또는 단체는 사용할 수 없습니다.\n" +
                "-> 시설물을 훼손 또는 파손하였을 경우에는 실비로 변상하여야 합니다.\n" +
                "-> 대학교의 사정(보수공사, 각종학교 행사나 학생자치기구의 공식행사 등)에 의하여 사전통지 없이 사용을 중지하게 할 수 있으며, 학교 관리자나 위임을 받은자의 지시에 따라야 합니다.\n" +
                "-> 이용자는 질서와 예절을 지키고 서로 양보하는 미덕을 발휘하여야 하며, 상대방에게 불쾌감을 주지 않도록 합니다.\n" +
                "-> 흡연은 전면 금지입니다.\n" +
                "-> 허가된 사용목적으로만 이용하여야 합니다."
        );
        inf_text.setFont(new Font("돋움", Font.PLAIN, 14));
        inf_text.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        inf_text.setLineWrap(true);

        // 달력 대용 temp
        temp.setText("달력이 들어갈 예정입니다.");
        temp.setFont(new Font("돋움", Font.PLAIN, 20));
        temp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

    }


}