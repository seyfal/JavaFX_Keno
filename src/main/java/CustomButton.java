// CustomButton.java
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author: Seyfal Sultanov
 * @version: 1.0
 * @date: 2023-03-15
 *
 * @description: n/a yet
 */

public class CustomButton extends Button {

    public CustomButton(String text) {
        setText(text);
        setFont(new Font("Arial", 18));
        setStyle("-fx-base: #2980b9; -fx-text-fill: white; -fx-background-radius: 5;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(8.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.rgb(50, 50, 50, 0.7));
        setEffect(dropShadow);

        // Set button size
        setMinWidth(90); // Set minimum width
        setMinHeight(50); // Set minimum height
        setMaxWidth(130); // Set maximum width
        setMaxHeight(70); // Set maximum height
    }
}
