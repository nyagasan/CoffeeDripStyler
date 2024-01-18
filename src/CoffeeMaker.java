public class CoffeeMaker {
    private int beanAmount;
    private int waterAmount;
    private String tastePreference;
    private String strengthPreference;

    public CoffeeMaker(int beanAmount, String tastePreference, String strengthPreference) {
        this.beanAmount = beanAmount;
        this.waterAmount = beanAmount * 15;
        this.tastePreference = tastePreference;
        this.strengthPreference = strengthPreference;
    }

    // ここに抽出ロジックを実装
    public void startBrewing() {
        // 抽出プロセスのロジック
    }
}
