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
        for (int i = 1; i <= 10; i++) {
            HBox row = new HBox(5);
            row.getChildren().addAll(new Label("Spots: " + i), new Label("Winnings: $..."));
            winningsColumn.getChildren().add(row);
        }

        // Add an empty box to fill up the remaining space below winningsColumn
        VBox emptyBox = new VBox();
        VBox.setVgrow(emptyBox, Priority.ALWAYS);
        winningsColumn.getChildren().add(emptyBox);

        root.setLeft(winningsColumn);

        // Create bet card grid (center)
        BetCardGrid betCardGrid = new BetCardGrid(8, 10);

        // Bind the height of the winningsColumn to the height of the betCardGrid
        winningsColumn.prefHeightProperty().bind(betCardGrid.heightProperty());

        // Spots and Draws blocks
        VBox spotsBlock = createButtonBlock("Spots", new int[][]{{1, 4}, {8, 10}});
        VBox drawsBlock = createButtonBlock("Draws", new int[][]{{1, 2}, {3, 4}});

        // Auto and Play buttons
        Button autoButton = new CustomButton("Auto");
        Button playButton = new CustomButton("Play");

        // Add Auto and Play buttons to a vertical box
        VBox autoPlayBox = new VBox(10);
        autoPlayBox.setAlignment(Pos.CENTER);
        autoPlayBox.getChildren().addAll(autoButton, playButton);

        // Add an empty box to fill up the remaining space above autoPlayBox
        VBox emptyBoxAboveButtons = new VBox();
        VBox.setVgrow(emptyBoxAboveButtons, Priority.ALWAYS);
        autoPlayBox.getChildren().add(0, emptyBoxAboveButtons);

        // Wrap winningsColumn and betCardGrid in a VBox
        VBox leftSide = new VBox();
        leftSide.getChildren().addAll(winningsColumn, new Region()); // Add an empty Region to fill the remaining space
        VBox.setVgrow(winningsColumn, Priority.ALWAYS);
        VBox.setVgrow(new Region(), Priority.ALWAYS);

        // Combine winningsColumn and betCardGrid in an HBox
        HBox topLayout = new HBox(20);
        topLayout.setAlignment(Pos.CENTER);
        topLayout.getChildren().addAll(winningsColumn, betCardGrid);

        // Combine Spots, Draws, and Auto/Play buttons in a horizontal layout
        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(spotsBlock, drawsBlock, autoPlayBox);

        // Set horizontal grow priority to make buttons take up the whole width
        HBox.setHgrow(spotsBlock, Priority.ALWAYS);
        HBox.setHgrow(drawsBlock, Priority.ALWAYS);
        HBox.setHgrow(autoPlayBox, Priority.ALWAYS);

        // Add the top layout and button layout to the main layout with increased spacing
        VBox mainLayout = new VBox(30); // Increase spacing here
        mainLayout.getChildren().addAll(topLayout, buttonLayout);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20, 20, 20, 20)); // Add padding here

        // Set the main layout as the center of the root
        root.setCenter(mainLayout);


    }

    // KenoController.java
    private VBox createButtonBlock(String title, int[][] numbers) {
        Label blockTitle = new Label(title);
        blockTitle.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #333;"); // Update the text style
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
