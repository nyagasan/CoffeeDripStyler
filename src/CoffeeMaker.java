import java.util.List;
import java.util.ArrayList;

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

    // 抽出ロジックを実装
    public List<String[]> startBrewing() {
        List<String[]> brewingSteps = new ArrayList<>();

        // 抽出液の計算
        int extractionLiquid = waterAmount * 4 / 10;
        int concentrationLiquid = waterAmount - extractionLiquid;

        // 味の調整に基づく計算
        int firstPour, secondPour;
        switch (tastePreference) {
            case "甘味":
                firstPour = extractionLiquid * 6 / 10;
                secondPour = extractionLiquid - firstPour;
                break;
            case "普通":
                firstPour = extractionLiquid / 2;
                secondPour = firstPour;
                break;
            case "酸味":
                firstPour = extractionLiquid * 4 / 10;
                secondPour = extractionLiquid - firstPour;
                break;
            default:
                firstPour = secondPour = 0;
        }

        // 濃さの調整に基づく計算
        int[] concentrationPours;
        switch (strengthPreference) {
            case "薄め":
                concentrationPours = new int[]{concentrationLiquid / 2, concentrationLiquid / 2};
                break;
            case "普通":
                concentrationPours = new int[]{concentrationLiquid / 3, concentrationLiquid / 3, concentrationLiquid / 3};
                break;
            case "濃いめ":
                concentrationPours = new int[]{concentrationLiquid / 4, concentrationLiquid / 4, concentrationLiquid / 4, concentrationLiquid / 4};
                break;
            default:
                concentrationPours = new int[]{};
        }

        // 抽出ステップの計算

        // 抽出ステップの計算
        int totalTime = 0;
        int totalAmount = 0; // 総量の初期化

        totalAmount += firstPour;
        brewingSteps.add(new String[]{"0秒", String.valueOf(firstPour) + "g", String.valueOf(totalAmount) + "g"});
        totalTime += 45;

        totalAmount += secondPour;
        brewingSteps.add(new String[]{"45秒", String.valueOf(secondPour) + "g", String.valueOf(totalAmount) + "g"});

        for (int pour : concentrationPours) {
            totalTime += 45;
            totalAmount += pour;
            brewingSteps.add(new String[]{totalTime / 60 + "分" + totalTime % 60 + "秒", pour + "g", String.valueOf(totalAmount) + "g"});
        }

        return brewingSteps;
    }
}
