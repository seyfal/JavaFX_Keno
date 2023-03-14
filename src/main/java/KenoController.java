import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class KenoController {
    public void initializeKenoUI(BorderPane root) {
        // Create winnings column (left side)
        VBox winningsColumn = new VBox(10);
        winningsColumn.setSpacing(10);
        winningsColumn.setPadding(new Insets(10, 10, 10, 10));
        winningsColumn.setStyle("-fx-background-color: white;");
        winningsColumn.setAlignment(Pos.CENTER);
        for (int i = 1; i <= 10; i++) {
            HBox row = new HBox(5);
            row.getChildren().addAll(new Label("Spots: " + i), new Label("Winnings: $..."));
            winningsColumn.getChildren().add(row);
        }
        root.setLeft(winningsColumn);

        // Create bet card grid (center)
        BetCardGrid betCardGrid = new BetCardGrid(10);
        betCardGrid.setStyle("-fx-background-color: white;");
        betCardGrid.setPadding(new Insets(20));
        root.setCenter(betCardGrid);

        // Create control buttons (bottom)
        HBox controls = new HBox(20);
        controls.setSpacing(20);
        controls.setPadding(new Insets(10));
        controls.setAlignment(Pos.CENTER);

        VBox spotsButtons = new VBox(5);
        spotsButtons.getChildren().addAll(new Button("1 Spot"), new Button("4 Spots"), new Button("8 Spots"), new Button("10 Spots"));

        VBox drawsButtons = new VBox(5);
        drawsButtons.getChildren().addAll(new Button("1 Draw"), new Button("2 Draws"), new Button("3 Draws"), new Button("4 Draws"));

        Button autoButton = new Button("Auto");
        Button playButton = new Button("Play");

        controls.getChildren().addAll(spotsButtons, drawsButtons, autoButton, playButton);
        root.setBottom(controls);
    }
}
