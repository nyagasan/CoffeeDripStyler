import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ResultPanel extends JPanel {
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public ResultPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] columnNames = {"累計時間", "注湯量", "総量"};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable));

        JButton backButton = new JButton("戻る");
        backButton.addActionListener(e -> mainFrame.switchToInputPanel());
        add(backButton);
    }

    public void updateResults(List<String[]> newData) {
        tableModel.setRowCount(0); // テーブルをクリア
        for (String[] row : newData) {
            tableModel.addRow(row);
        }
    }
}
