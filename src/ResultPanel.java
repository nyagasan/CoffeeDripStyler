import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultPanel extends JPanel {
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private List<String[]> data; // 結果データを保持

    public ResultPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // テーブルの設定
        String[] columnNames = {"累計時間", "注湯量", "総量"};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable));

        // 戻るボタン
        JButton backButton = new JButton("戻る");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.switchToInputPanel();
            }
        });
        add(backButton);

        // CSV出力ボタン
        JButton exportCsvButton = new JButton("CSVで保存");
        exportCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveResultsToCsv();
            }
        });
        add(exportCsvButton);
    }

    public void updateResults(List<String[]> newData) {
        tableModel.setRowCount(0); // テーブルをクリア
        for (String[] row : newData) {
            tableModel.addRow(row);
        }
        this.data = newData; // 結果データの更新
    }

    private void saveResultsToCsv() {
        if (data != null && !data.isEmpty()) {
            CsvExporter.exportToCsv(data, this); // このパネルを親コンポーネントとして渡す
        } else {
            JOptionPane.showMessageDialog(this, "保存するデータがありません。", "エラー", JOptionPane.ERROR_MESSAGE);
        }
    }

}
