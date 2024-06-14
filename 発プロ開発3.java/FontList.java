import java.awt.*;

public class FontList {
    public static void main(String[] args) {
        // ローカルのグラフィック環境から利用可能なフォント一覧を取得
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        // フォント一覧を表示
        for (String fontName : fontNames) {
            System.out.println(fontName);
        }
    }
}
