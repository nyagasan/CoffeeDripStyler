import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;

public class ResultPanel extends JPanel {
    private JTable resultTable;

    public ResultPanel(MainFrame mainFrame) {
        // 結果テーブルの設定
        String[] columnNames = {"累計時間", "注湯量", "総量"};
        Object[][] data = {};
        resultTable = new JTable(data, columnNames);
        add(resultTable);

        JButton backButton = new JButton("戻る");
        backButton.addActionListener(e -> mainFrame.switchToInputPanel());
        add(backButton);
    }

    public void updateResults(Object[][] newData) {
        // テーブルのデータを更新
    }
}
