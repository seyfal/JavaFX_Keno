import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BetButton extends Button {
    private int number;
    private ButtonState state;
    private Circle circle;
    private Text text;
    private StackPane stack;
    private RadialGradient neutralGradient, selectedGradient, drawnGradient;

    public BetButton(int number) {
        this.number = number;
        this.state = ButtonState.UNSELECTED;

        neutralGradient = new RadialGradient(
                0, 0, 0.5, 0.5, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#cccccc")),
                new Stop(1, Color.web("#aaaaaa"))
        );
        selectedGradient = new RadialGradient(
                0, 0, 0.5, 0.5, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#2f6dff")),
                new Stop(1, Color.web("#0d47a1"))
        );
        drawnGradient = new RadialGradient(
                0, 0, 0.5, 0.5, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#eeeeee")),
                new Stop(1, Color.web("#cccccc"))
        );

        circle = new Circle(20);
        circle.setFill(neutralGradient);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        dropShadow.setColor(Color.rgb(50, 50, 50, 0.7));
        circle.setEffect(dropShadow);

        text = new Text(Integer.toString(number));
        text.setFont(new Font(14));
        text.setFill(Color.BLACK);

        stack = new StackPane(circle, text);
        setGraphic(stack);
        setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        setOnMouseClicked(this::onMouseClicked);

        setOnAction(event -> toggleSelection());
    }

    private void onMouseClicked(MouseEvent event) {
        // Add the scaling animation
        ScaleTransition st = new ScaleTransition(Duration.millis(150), this);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(1.1);
        st.setToY(1.1);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();

        // Other code for button state changes...
    }

    public void toggleSelection() {
        if (state == ButtonState.UNSELECTED) {
            state = ButtonState.SELECTED;
        } else if (state == ButtonState.SELECTED) {
            state = ButtonState.UNSELECTED;
        }
        updateAppearance();
    }

    private void updateAppearance() {
        switch (state) {
            case UNSELECTED:
                circle.setFill(neutralGradient);
                text.setFill(Color.BLACK);
                break;
            case SELECTED:
                circle.setFill(selectedGradient);
                text.setFill(Color.WHITE);
                break;
            case DRAWN:
                circle.setFill(drawnGradient);
                text.setFill(Color.BLACK);
                break;
            case CORRECT:
                circle.setFill(Color.web("#4caf50"));
                text.setFill(Color.WHITE);
                break;
            case INCORRECT:
                circle.setFill(Color.web("#f44336"));
                text.setFill(Color.WHITE);
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public ButtonState getState() {
        return state;
    }

    public void setState(ButtonState state) {
        this.state = state;
        updateAppearance();
    }

    public enum ButtonState {
        UNSELECTED, SELECTED, DRAWN, CORRECT, INCORRECT
    }
}
