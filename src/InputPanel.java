import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel {
    private JTextField beanAmountField;
    private JComboBox<String> tastePreferenceComboBox;
    private JComboBox<String> strengthPreferenceComboBox;
    private MainFrame mainFrame;

    public InputPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // UIコンポーネントの設定
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("豆の量 (5~50g):"));
        beanAmountField = new JTextField(5);
        add(beanAmountField);

        add(new JLabel("味の調整:"));
        String[] tastes = {"甘味", "普通", "酸味"};
        tastePreferenceComboBox = new JComboBox<>(tastes);
        add(tastePreferenceComboBox);

        add(new JLabel("濃さの調整:"));
        String[] strengths = {"薄め", "普通", "濃いめ"};
        strengthPreferenceComboBox = new JComboBox<>(strengths);
        add(strengthPreferenceComboBox);

        JButton startButton = new JButton("開始");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int beanAmount = Integer.parseInt(beanAmountField.getText());
                String tastePreference = (String) tastePreferenceComboBox.getSelectedItem();
                String strengthPreference = (String) strengthPreferenceComboBox.getSelectedItem();

                CoffeeMaker coffeeMaker = new CoffeeMaker(beanAmount, tastePreference, strengthPreference);
                List<String[]> brewingSteps = coffeeMaker.startBrewing();
                mainFrame.getResultPanel().updateResults(brewingSteps);
                mainFrame.switchToResultPanel();
            }
        });
        add(startButton);
    }
}
