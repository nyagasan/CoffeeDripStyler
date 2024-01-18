import javax.swing.JFrame;
import java.awt.CardLayout;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;

    public MainFrame() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // パネルを追加
        add(new InputPanel(this), "InputPanel");
        add(new ResultPanel(this), "ResultPanel");

        // ウィンドウ設定
        setTitle("46DripStyler");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void switchToResultPanel() {
        cardLayout.show(this.getContentPane(), "ResultPanel");
    }

    public void switchToInputPanel() {
        cardLayout.show(this.getContentPane(), "InputPanel");
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
