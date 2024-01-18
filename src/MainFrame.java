import javax.swing.*;
import java.awt.CardLayout;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private InputPanel inputPanel;
    private ResultPanel resultPanel;

    public MainFrame() {
        // メインフレームの設定
        setTitle("46DripStyler");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // カードレイアウトの設定
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 各パネルの初期化と追加
        inputPanel = new InputPanel(this);
        resultPanel = new ResultPanel(this);
        mainPanel.add(inputPanel, "InputPanel");
        mainPanel.add(resultPanel, "ResultPanel");

        // メインパネルをフレームに追加
        add(mainPanel);

        // フレームの表示
        setVisible(true);
    }

    // InputPanelへの切り替えメソッド
    public void switchToInputPanel() {
        cardLayout.show(mainPanel, "InputPanel");
    }

    // ResultPanelへの切り替えメソッド
    public void switchToResultPanel() {
        cardLayout.show(mainPanel, "ResultPanel");
    }

    // ResultPanelのインスタンスを取得
    public ResultPanel getResultPanel() {
        return resultPanel;
    }

    public static void main(String[] args) {
        // メインフレームの実行
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
