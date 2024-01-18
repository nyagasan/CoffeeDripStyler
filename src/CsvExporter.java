import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter {
    public static void exportToCsv(List<String[]> data, Component parentComponent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("CSVファイルを保存");

        // ファイル選択ダイアログを表示
        int userSelection = fileChooser.showSaveDialog(parentComponent);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            // ファイル拡張子のチェックと追加
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }

            // データをCSVファイルに書き出し
            try (FileWriter writer = new FileWriter(filePath)) {
                for (String[] row : data) {
                    writer.write(String.join(",", row));
                    writer.write("\n");
                }
                JOptionPane.showMessageDialog(parentComponent, "結果が " + filePath + " に保存されました。", "保存成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parentComponent, "ファイルの保存に失敗しました。", "エラー", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
