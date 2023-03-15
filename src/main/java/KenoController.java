import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class KenoController {

    public void initializeKenoUI(BorderPane root) {
        VBox winningsColumn = createWinningsColumn();
        root.setLeft(winningsColumn);

        BetCardGrid betCardGrid = new BetCardGrid(8, 10);
        winningsColumn.prefHeightProperty().bind(betCardGrid.heightProperty());

        HBox topLayout = new HBox(20);
        topLayout.setAlignment(Pos.CENTER);
        topLayout.getChildren().addAll(winningsColumn, betCardGrid);

        HBox buttonLayout = createButtonLayout();

        VBox mainLayout = new VBox(30);
        mainLayout.getChildren().addAll(topLayout, buttonLayout);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20, 20, 20, 20));

        root.setCenter(mainLayout);
    }

    private VBox createWinningsColumn() {
        VBox winningsColumn = new VBox(10);
        winningsColumn.setSpacing(10);
        winningsColumn.setPadding(new Insets(10, 10, 10, 10));
        winningsColumn.setStyle("-fx-background-color: white;");

        for (int i = 1; i <= 10; i++) {
            HBox row = new HBox(5);
            row.getChildren().addAll(new Label("Spots: " + i), new Label("Winnings: $..."));
            winningsColumn.getChildren().add(row);
        }

        VBox emptyBox = new VBox();
        VBox.setVgrow(emptyBox, Priority.ALWAYS);
        winningsColumn.getChildren().add(emptyBox);

        return winningsColumn;
    }

    private HBox createButtonLayout() {
        VBox spotsBlock = createButtonBlock("Spots", new int[][]{{1, 4}, {8, 10}});
        VBox drawsBlock = createButtonBlock("Draws", new int[][]{{1, 2}, {3, 4}});
        VBox autoPlayBox = createAutoPlayBox();

        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(spotsBlock, drawsBlock, autoPlayBox);

        HBox.setHgrow(spotsBlock, Priority.ALWAYS);
        HBox.setHgrow(drawsBlock, Priority.ALWAYS);
        HBox.setHgrow(autoPlayBox, Priority.ALWAYS);

        return buttonLayout;
    }

    private VBox createAutoPlayBox() {
        Button autoButton = new CustomButton("Auto");
        Button playButton = new CustomButton("Play");

        VBox autoPlayBox = new VBox(10);
        autoPlayBox.setAlignment(Pos.CENTER);
        autoPlayBox.getChildren().addAll(autoButton, playButton);

        VBox emptyBoxAboveButtons = new VBox();
        VBox.setVgrow(emptyBoxAboveButtons, Priority.ALWAYS);
        autoPlayBox.getChildren().add(0, emptyBoxAboveButtons);

        return autoPlayBox;
    }

    private VBox createButtonBlock(String title, int[][] numbers) {
        Label blockTitle = new Label(title);
        blockTitle.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #333;");
        blockTitle.setMaxWidth(Double.MAX_VALUE);
        blockTitle.setAlignment(Pos.CENTER);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                Button button = new CustomButton(Integer.toString(numbers[i][j]));
                buttonGrid.add(button, j, i);
            }
        }

        VBox buttonBlock = new VBox(10);
        buttonBlock.setAlignment(Pos.CENTER);
        buttonBlock.getChildren().addAll(blockTitle, buttonGrid);

        return buttonBlock;
    }
}


