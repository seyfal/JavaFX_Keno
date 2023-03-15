import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

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
        BetCardGrid betCardGrid = new BetCardGrid(8, 10);

        // Spots and Draws blocks
        VBox spotsBlock = createButtonBlock("Spots", new int[][]{{1, 4}, {8, 10}});
        VBox drawsBlock = createButtonBlock("Draws", new int[][]{{1, 2}, {3, 4}});

        // Auto and Play buttons
        Button autoButton = new Button("Auto");
        Button playButton = new Button("Play");

        // Add Auto and Play buttons to a horizontal box
        HBox autoPlayBox = new HBox(10);
        autoPlayBox.setAlignment(Pos.CENTER);
        autoPlayBox.getChildren().addAll(autoButton, playButton);

        // Combine Spots, Draws, and Auto/Play buttons in a horizontal layout
        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(spotsBlock, drawsBlock, autoPlayBox);

        // Add the button layout below the BetCardGrid
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(betCardGrid, buttonLayout);
        mainLayout.setAlignment(Pos.CENTER);

        // Set the main layout and winningsColumn in an HBox
        HBox combinedLayout = new HBox(20);
        combinedLayout.setAlignment(Pos.CENTER);
        combinedLayout.getChildren().addAll(winningsColumn, mainLayout);

        // Set the combined layout as the center of the root
        root.setCenter(combinedLayout);

    }

    private VBox createButtonBlock(String title, int[][] numbers) {
        Label blockTitle = new Label(title);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                Button button = new Button(Integer.toString(numbers[i][j]));
                buttonGrid.add(button, j, i);
            }
        }

        VBox buttonBlock = new VBox(10);
        buttonBlock.setAlignment(Pos.CENTER);
        buttonBlock.getChildren().addAll(blockTitle, buttonGrid);

        return buttonBlock;
    }



}
