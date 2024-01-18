import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.IOException;

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
        tastePreferenceComboBox.setSelectedItem("普通");
        add(tastePreferenceComboBox);

        add(new JLabel("濃さの調整:"));
        String[] strengths = {"薄め", "普通", "濃いめ"};
        strengthPreferenceComboBox = new JComboBox<>(strengths);
        strengthPreferenceComboBox.setSelectedItem("普通");
        add(strengthPreferenceComboBox);

        JButton startButton = new JButton("開始");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int beanAmount = validateAndGetBeanAmount();
                    String tastePreference = (String) tastePreferenceComboBox.getSelectedItem();
                    String strengthPreference = (String) strengthPreferenceComboBox.getSelectedItem();

                    CoffeeMaker coffeeMaker = new CoffeeMaker(beanAmount, tastePreference, strengthPreference);
                    List<String[]> brewingSteps = coffeeMaker.startBrewing();
                    mainFrame.getResultPanel().updateResults(brewingSteps);
                    mainFrame.switchToResultPanel();

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(InputPanel.this, ex.getMessage(), "入力エラー", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(startButton);
        // CSV読み込みボタン
        JButton loadCsvButton = new JButton("CSV読み込み");
        loadCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(mainFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!filePath.toLowerCase().endsWith(".csv")) {
                        JOptionPane.showMessageDialog(mainFrame, "CSVファイルを選択してください。", "エラー", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        List<String[]> data = CsvReader.readCsv(filePath);
                        mainFrame.getResultPanel().updateResults(data);
                        mainFrame.switchToResultPanel();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainFrame, "ファイルの読み込みに失敗しました。", "エラー", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(loadCsvButton);

    }
    private int validateAndGetBeanAmount() throws IllegalArgumentException {
        try {
            int beanAmount = Integer.parseInt(beanAmountField.getText());
            if (beanAmount < 5 || beanAmount > 50) {
                throw new IllegalArgumentException("豆の量は5gから50gの間で指定してください。");
            }
            return beanAmount;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("無効な数字形式です。");
        }
    }
}
