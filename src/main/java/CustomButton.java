import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomButton extends Button {

    public CustomButton(String text) {
        setText(text);
        setFont(new Font("Arial", 14));
        setStyle("-fx-base: #3498db; -fx-text-fill: white;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.rgb(50, 50, 50, 0.7));
        setEffect(dropShadow);
    }
}
