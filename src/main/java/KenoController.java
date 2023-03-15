import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Collections;
import javafx.scene.Node;
import java.util.List;

/*
 *The KenoController class helps create the UI layout for a Keno game. The class consists of methods
 * that create and organize various UI components, such as a winnings column, a bet card grid, and
 * several button blocks. The UI is built using JavaFX layouts, including BorderPane, VBox, HBox, and GridPane.
 *
 * Here is an overview of the main components of the class:
 *
 * initializeKenoUI(BorderPane root): This method sets up the main Keno UI by placing the winnings
 * column, bet card grid, and button layout inside the given BorderPane.
 *
 * createWinningsColumn(): Creates a VBox containing the winnings information for each number
 * of spots (1-10). Each row displays the number of spots and the corresponding winnings.
 *
 * createButtonLayout(): Creates an HBox that contains three button blocks: spots, draws,
 * and auto-play. Each block is placed in a VBox.
 *
 * createAutoPlayBox(): Creates a VBox with two buttons, "Auto" and "Play", for the auto-play functionality.
 *
 * createButtonBlock(String title, int[][] numbers): A generic method for creating button blocks
 * with a title and a grid of buttons based on the provided numbers array.
 *
 * To use this class in a JavaFX application, you can create a new instance of KenoController and call
 * the initializeKenoUI(BorderPane root) method. The method takes a BorderPane as an argument, which
 * will serve as the root node of your application's layout.
 *
 *    Here is an example:
 *
 *         // Create a new instance of KenoController
 *         KenoController kenoController = new KenoController();
 *
 *         // Create a BorderPane to serve as the root node of the layout
 *         BorderPane root = new BorderPane();
 *
 *         // Initialize the Keno UI with the root node
 *         kenoController.initializeKenoUI(root);
 *
 *         // Create and display the scene
 *         Scene scene = new Scene(root, 800, 600);
 *         primaryStage.setScene(scene);
 *         primaryStage.setTitle("Keno Game Example");
 *         primaryStage.show();
 *
 */


public class KenoController {

    private BetCardGrid                   betCardGrid; // Assuming you have implemented the BetCardGrid class
    private SimpleObjectProperty<Integer> selectedSpots = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Integer> selectedDraws = new SimpleObjectProperty<>();


    public
    void initializeKenoUI (BorderPane root, KenoGame kenoGame) {

        betCardGrid = new BetCardGrid(8, 10); // Assuming you have implemented the BetCardGrid class

        VBox winningsColumn = createWinningsColumn();
        root.setLeft(winningsColumn);

        winningsColumn.prefHeightProperty().bind(betCardGrid.heightProperty());

        HBox topLayout = new HBox(20);
        topLayout.setAlignment(Pos.CENTER);
        topLayout.getChildren().addAll(winningsColumn, betCardGrid);

        HBox buttonLayout = createButtonLayout(kenoGame);

        VBox mainLayout = new VBox(30);
        mainLayout.getChildren().addAll(topLayout, buttonLayout);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20, 20, 20, 20));

        root.setCenter(mainLayout);
    }

    private
    VBox createWinningsColumn () {
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

    private
    HBox createButtonLayout (KenoGame kenoGame) {
        VBox spotsBlock = createButtonBlock("Spots", new int[][]{{1, 4}, {8, 10}}, kenoGame);
        VBox drawsBlock = createButtonBlock("Draws", new int[][]{{1, 2}, {3, 4}}, kenoGame);
        VBox autoPlayBox = createAutoPlayBox(kenoGame);

        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(spotsBlock, drawsBlock, autoPlayBox);

        HBox.setHgrow(spotsBlock, Priority.ALWAYS);
        HBox.setHgrow(drawsBlock, Priority.ALWAYS);
        HBox.setHgrow(autoPlayBox, Priority.ALWAYS);

        return buttonLayout;
    }

    private VBox createAutoPlayBox(KenoGame kenoGame) {
        Button autoButton = new CustomButton("Auto");
        Button playButton = new CustomButton("Play");
        playButton.setOnAction(event -> {
            List<Integer> selectedNumbers = betCardGrid.getSelectedNumbers();
            int matchedNumbers = kenoGame.playDrawing(selectedNumbers);
            int winnings = kenoGame.calculateWinnings(matchedNumbers);
            kenoGame.setTotalWinnings(kenoGame.getTotalWinnings() + winnings);
        });

        VBox autoPlayBox = new VBox(10);
        autoPlayBox.setAlignment(Pos.CENTER);
        autoPlayBox.getChildren().addAll(autoButton, playButton);

        VBox emptyBoxAboveButtons = new VBox();
        VBox.setVgrow(emptyBoxAboveButtons, Priority.ALWAYS);
        autoPlayBox.getChildren().add(0, emptyBoxAboveButtons);

        autoButton.setOnAction(event -> {
            int numSpots = selectedSpots.get(); // Get the number of spots chosen
            betCardGrid.resetButtons(); // Clear the BetCardGrid

            // Generate a list of numbers from 1 to 80
            List<Integer> numbers = IntStream.rangeClosed(1, 80).boxed().collect(Collectors.toList());

            // Shuffle the list of numbers
            Collections.shuffle(numbers);

            // Select the first numSpots elements as the random spots
            List<Integer> randomSpots = numbers.subList(0, numSpots);

            // Simulate pressing the buttons on the BetCardGrid
            for (Node node : betCardGrid.getChildren()) {
                if (node instanceof BetButton) {
                    BetButton betButton = (BetButton) node;
                    int buttonNumber = betButton.getNumber();
                    if (randomSpots.contains(buttonNumber)) {
                        betButton.fire(); // Press the button
                    }
                }
            }
        });

        playButton.setOnAction(event -> {
            // TODO - Implement the play button
        });

        return autoPlayBox;
    }

    private VBox createButtonBlock(String title, int[][] numbers, KenoGame kenoGame) {
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

                if (title.equals("Spots")) {
                    button.setOnAction(event -> {
                        int numSpots = Integer.parseInt(button.getText());
                        selectedSpots.set(numSpots); // Update the selectedSpots property
                        kenoGame.setNumSpots(numSpots);
                        betCardGrid.enableButtons(true);
                    });
                } else if (title.equals("Draws")) {
                    button.setOnAction(event -> {
                        int numDraws = Integer.parseInt(button.getText());
                        selectedDraws.set(numDraws); // Update the selectedDraws property
                        kenoGame.setNumDraws(numDraws);
                    });
                }

                buttonGrid.add(button, j, i);
            }
        }

        VBox buttonBlock = new VBox(10);
        buttonBlock.setAlignment(Pos.CENTER);
        buttonBlock.getChildren().addAll(blockTitle, buttonGrid);

        return buttonBlock;
    }

    // TODO - Implement the KenoController class draw button action
    public KenoController() {
        selectedSpots.addListener((obs, oldValue, newValue) -> {
            betCardGrid.setMaxSpots(newValue); // Set the max spots for BetCardGrid
            betCardGrid.resetButtons(); // Clear the BetCardGrid
        });

        selectedDraws.addListener((obs, oldValue, newValue) -> {
            // Perform any action when the selectedDraws value changes
        });
    }

}
