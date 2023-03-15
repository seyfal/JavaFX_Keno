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
import javafx.scene.layout.Priority;
import java.util.ArrayList;

/**
 * @author:  Seyfal Sultanov
 * @version: 2.0
 * @date:    2023-03-15
 *
 * @description:
 *
 * The KenoController class is responsible for managing the user interface of a Keno game application,
 * and it interacts with other classes to control the game's logic and visuals. Here is a bird's-eye
 * view of the class and its interactions:
 *
 * KenoController initializes and sets up the Keno game's user interface using the
 * initializeKenoUI(BorderPane root, KenoGame kenoGame) method. It creates and positions
 * the different UI components (like the bet card grid, winnings column, buttons for spots
 * and draws, and the auto-play feature) within the main BorderPane layout provided as the
 * root parameter. It uses the KenoGame object passed as the kenoGame parameter to manage the game's logic.
 *
 * KenoController interacts with the BetCardGrid class, which is responsible for managing the grid of buttons
 * that players use to place bets. The controller configures and controls the state of the bet card grid, including
 * enabling and disabling buttons, setting the maximum number of spots, and resetting the grid when necessary.
 *
 * The class uses the KenoGame object to manage the game's logic, like the number of spots selected by the player,
 * the number of draws, and calculating the winnings based on matched numbers.
 *
 * KenoController uses various helper methods to create different UI components, such as createWinningsColumn(),
 * createButtonLayout(KenoGame kenoGame), createAutoPlayBox(KenoGame kenoGame), and
 * createButtonBlock(String title, int[][] numbers, KenoGame kenoGame). These methods are called
 * internally to set up the interface and are not meant to be used directly by users.
 *
 * The class uses property listeners to react to changes in the number of selected spots and draws.
 * When the number of selected spots changes, the listener sets the maximum spots for the BetCardGrid
 * and resets the grid. When the number of selected draws changes, the listener can be used to perform
 * additional actions if necessary.
 *
 * In summary, the KenoController class serves as a bridge between the user interface and the game's logic,
 * managing the UI components and interacting with the KenoGame and BetCardGrid classes to control the game's
 * flow and state.
 */
public class KenoController {

    // An instance of the BetCardGrid class that displays the grid of numbers for the user to make bets on.
    private BetCardGrid                   betCardGrid;

    // A property that holds the currently selected number of spots for the game.
    private SimpleObjectProperty<Integer> selectedSpots = new SimpleObjectProperty<>();

    // A property that holds the currently selected number of draws for the game.
    private SimpleObjectProperty<Integer> selectedDraws = new SimpleObjectProperty<>();

    // A list of labels that displays the winnings for each number of spots.
    private List<Label> winningsLabels = new ArrayList<>();



    /**
     * A method that initializes the user interface components, such as the BetCardGrid,
     * the winnings column, and the buttons. It adds these components to the provided root node.
     * @usage
     * KenoController controller = new KenoController();
     * KenoGame kenoGame = new KenoGame();
     * BorderPane root = new BorderPane();
     * controller.initializeKenoUI(root, kenoGame);
     */
    public
    void initializeKenoUI (BorderPane root, KenoGame kenoGame) {

        betCardGrid = new BetCardGrid(8, 10); // Create a BetCardGrid with 8 rows and 10 columns

        VBox winningsColumn = createWinningsColumn(); // Create a VBox to display the winnings table
        root.setLeft(winningsColumn); // Add the winnings column to the left side of the root layout

        // Set the height of the winnings column to match the height of the bet card grid
        winningsColumn.prefHeightProperty().bind(betCardGrid.heightProperty());

        HBox topLayout = new HBox(20); // Create an HBox to hold the bet card grid and the winnings column
        topLayout.setAlignment(Pos.CENTER); // Center the HBox in the root layout
        topLayout.getChildren().addAll(winningsColumn, betCardGrid); // Add the bet card grid and the winnings column to the HBox

        HBox buttonLayout = createButtonLayout(kenoGame); // Create an HBox to hold the buttons

        VBox mainLayout = new VBox(30); // Create a VBox to hold the top layout and the button layout
        mainLayout.getChildren().addAll(topLayout, buttonLayout); // Add the top layout and the button layout to the VBox
        mainLayout.setAlignment(Pos.CENTER); // Center the VBox in the root layout
        mainLayout.setPadding(new Insets(20, 20, 20, 20)); // Add padding to the VBox

        root.setCenter(mainLayout);
    }

    /**
     * A method that creates a VBox with information about the winnings for different
     * numbers of spots. The VBox is used in the main layout of the Keno UI.
     * @usage
     * // Called internally by the KenoController class
     * VBox winningsColumn = createWinningsColumn();
     */
    private VBox createWinningsColumn() {
        VBox winningsColumn = new VBox(10);
        winningsColumn.setSpacing(10);
        winningsColumn.setPadding(new Insets(10, 10, 10, 10));
        winningsColumn.setStyle("-fx-background-color: white; -fx-border-color: #333; -fx-border-width: 2px;");

        for (int i = 1; i <= 10; i++) {
            HBox row = new HBox(5);
            Label spotsLabel = new Label("Spots: " + i);
            spotsLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333;");
            Label winningsLabel = new Label("Winnings: $...");
            winningsLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333;");
            row.getChildren().addAll(spotsLabel, winningsLabel);
            winningsLabels.add(winningsLabel); // Add the winnings label to the list
            winningsColumn.getChildren().add(row);
        }

        VBox emptyBox = new VBox();
        VBox.setVgrow(emptyBox, Priority.ALWAYS);
        winningsColumn.getChildren().add(emptyBox);

        return winningsColumn;
    }


    /**
     * A method that creates an HBox containing the button blocks for
     * selecting spots and draws and the auto-play buttons.
     * @usage
     * // Called internally by the KenoController class
     * HBox buttonLayout = createButtonLayout(kenoGame);
     */
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

    /**
     * A method that creates a VBox containing the auto and play
     * buttons. It also defines their behavior when clicked.
     * @usage
     * // Called internally by the KenoController class
     * VBox autoPlayBox = createAutoPlayBox(kenoGame);
     */
    private VBox createAutoPlayBox(KenoGame kenoGame) {
        Button autoButton = new CustomButton("Auto");
        Button playButton = new CustomButton("Play");
        playButton.setOnAction(event -> {
            // TODO - Implement the play button
            List<Integer> selectedNumbers = betCardGrid.getSelectedNumbers(); // Get the numbers selected by the user
            int matchedNumbers = kenoGame.playDrawing(selectedNumbers); // Play the drawing
            int winnings = kenoGame.calculateWinnings(matchedNumbers); // Calculate the winnings
            kenoGame.setTotalWinnings(kenoGame.getTotalWinnings() + winnings); // Update the total winnings
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
                if (node instanceof BetButton) { // Check if the node is a BetButton
                    BetButton betButton = (BetButton) node; // Cast the node to a BetButton
                    int buttonNumber = betButton.getNumber(); // Get the number of the button
                    if (randomSpots.contains(buttonNumber)) { // Check if the button number is in the list of random spots
                        betButton.fire(); // Press the button
                    }
                }
            }
        });

        return autoPlayBox;
    }

    /**
     * A method that creates a VBox containing a title label and a
     * grid of buttons for selecting spots or draws.
     * @usage
     * // Called internally by the KenoController class
     * VBox spotsBlock = createButtonBlock("Spots", new int[][]{{1, 4}, {8, 10}}, kenoGame);
     * VBox drawsBlock = createButtonBlock("Draws", new int[][]{{1, 2}, {3, 4}}, kenoGame);
     */
    private VBox createButtonBlock(String title, int[][] numbers, KenoGame kenoGame) {
        Label blockTitle = new Label(title);
        blockTitle.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #333;");
        blockTitle.setMaxWidth(Double.MAX_VALUE);
        blockTitle.setAlignment(Pos.CENTER);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);

        for (int i = 0; i < numbers.length; i++) { // Loop through the rows
            for (int j = 0; j < numbers[i].length; j++) { // Loop through the columns
                Button button = new CustomButton(Integer.toString(numbers[i][j])); // Create a button with the number as the text

                if (title.equals("Spots")) { // Check if the title is "Spots"
                    button.setOnAction(event -> { // Add an event handler to the button
                        int numSpots = Integer.parseInt(button.getText()); // Get the number of spots from the button text
                        selectedSpots.set(numSpots); // Update the selectedSpots property
                        kenoGame.setNumSpots(numSpots); // Update the number of spots in the KenoGame
                        betCardGrid.enableButtons(true); // Enable the buttons on the BetCardGrid
                    });
                } else if (title.equals("Draws")) { // Check if the title is "Draws"
                    button.setOnAction(event -> { // Add an event handler to the button
                        int numDraws = Integer.parseInt(button.getText()); // Get the number of draws from the button text
                        selectedDraws.set(numDraws); // Update the selectedDraws property
                        kenoGame.setNumDraws(numDraws); // Update the number of draws in the KenoGame
                    });
                }

                buttonGrid.add(button, j, i); // Add the button to the grid
            }
        }

        VBox buttonBlock = new VBox(10); // Create a VBox to hold the title and grid
        buttonBlock.setAlignment(Pos.CENTER); // Center the title and grid
        buttonBlock.getChildren().addAll(blockTitle, buttonGrid); // Add the title and grid to the VBox

        return buttonBlock;
    }

    /**
     * A method that creates a VBox containing the BetCardGrid and
     * the winnings labels.
     * @usage
     * // Called internally by the KenoController class
     * VBox betCardGridBlock = createBetCardGridBlock(kenoGame);
     */
    private void updateWinningsColumn(int numSpots) {
        int[][] winningsMatrix = {
                {2},
                {1, 5, 75},
                {2, 12, 50, 750, 10000},
                {5, 2, 15, 40, 450, 4250, 100000}
        };
        int[] spotsIndex = {1, 4, 8, 10};

        int index = -1;
        for (int i = 0; i < spotsIndex.length; i++) {
            if (spotsIndex[i] == numSpots) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            int[] winnings = winningsMatrix[index];
            for (int i = 0; i < winningsLabels.size(); i++) {
                if (i < winnings.length) {
                    winningsLabels.get(i).setText("Winnings: $" + winnings[i]);
                } else {
                    winningsLabels.get(i).setText("Winnings: $...");
                }
            }
        }
    }


    // TODO - Implement the KenoController class draw button action
    /**
     * The constructor of the KenoController class. It adds listeners to the selectedSpots
     * and selectedDraws properties. When the number of spots or draws is changed, the
     * listeners update the BetCardGrid and perform other actions as needed.
     * @usage
     * KenoController controller = new KenoController();
     */
    public KenoController() {
        selectedSpots.addListener((obs, oldValue, newValue) -> {
            betCardGrid.setMaxSpots(newValue); // Set the max spots for BetCardGrid
            betCardGrid.resetButtons(); // Clear the BetCardGrid
            updateWinningsColumn(newValue); // Update the winnings column based on the selected spots
        });

        selectedDraws.addListener((obs, oldValue, newValue) -> {
            // Perform any action when the selectedDraws value changes
        });
    }

}
