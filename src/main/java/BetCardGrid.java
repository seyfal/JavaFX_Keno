import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Seyfal Sultanov
 * @version: 3.0
 * @date: 2023-03-15
 *
 * @description:
 *
 * BetCardGrid is a custom GridPane layout designed for betting applications, such as lottery games.
 * It represents a grid of clickable BetButton instances for selecting numbers on the bet card.
 * Each BetButton in the grid displays a unique number, and players can select and deselect numbers
 * by interacting with the buttons.
 *
 * Usage:
 *
 * Create a BetCardGrid with 5 rows and 10 columns
 * BetCardGrid betCardGrid = new BetCardGrid(5, 10);
 *
 * Set the maximum number of spots that can be selected on the bet card
 * betCardGrid.setMaxSpots(5);
 *
 * Create a BorderPane as the root layout and add the BetCardGrid to it
 * BorderPane root = new BorderPane();
 * root.setCenter(betCardGrid);
 *
 * Create a scene, set the root layout, and display the stage
 * Scene scene = new Scene(root, 600, 400);
 * primaryStage.setScene(scene);
 * primaryStage.setTitle("BetCardGrid Example");
 * primaryStage.show();
 *
 */
class BetCardGrid extends GridPane {

    private int maxSpots; // Maximum number of spots that can be selected

    /*
     * Description: Initializes the instance variables, creates the GridPane, and adds BetButton
     * objects for each number in the bet card. The buttons are set up with event handlers for
     * selecting and deselecting numbers. All cells in the GridPane are initially disabled.
     *
     * Application: Creates a new instance of BetCardGrid with the specified number of rows and columns.
     */
    public BetCardGrid(int rows, int columns) {
        setHgap(12); // Increase horizontal spacing between balls
        setVgap(12); // Increase vertical spacing between balls
        setAlignment(Pos.CENTER);
        int number = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                BetButton betButton = createBetButton(number);
                add(betButton, j, i);
                number++;
            }
        }
    }

    /*
     * Private method
     *
     * Description: Creates a BetButton object for the given number.
     *
     * Application: Used internally in the constructor to create BetButtons for each number in the bet card.
     */
    private BetButton createBetButton(int number) {
        BetButton betButton = new BetButton(number);

        betButton.setOnAction(event -> {
            BetButton.ButtonState currentState = betButton.getState();

            if (currentState == BetButton.ButtonState.UNSELECTED && getSelectedNumbers().size() < maxSpots) {
                betButton.setState(BetButton.ButtonState.SELECTED);
            } else if (currentState == BetButton.ButtonState.SELECTED) {
                betButton.setState(BetButton.ButtonState.UNSELECTED);
            }
        });

        return betButton;
    }


    /*
     * Public method
     *
     * Description: Sets the maximum number of spots that can be selected on the bet card.
     */
    public void setMaxSpots(int maxSpots) {
        this.maxSpots = maxSpots;
    }


    /*
     * Public method
     *
     * Description: Resets the state of all BetButtons in the grid to unselected.
     *
     * Application: Resets the BetCardGrid to its initial state, clearing any selected buttons.
     */
    public void resetButtons() {
        for (Node node : getChildren()) {
            if (node instanceof BetButton) {
                BetButton betButton = (BetButton) node;
                betButton.setState(BetButton.ButtonState.UNSELECTED);
            }
        }
    }

    /*
     * Public method
     *
     * Description: Updates the state of BetButtons based on the drawn and selected numbers.
     * Sets the button state to correct, drawn, incorrect, or unselected as appropriate.
     *
     * Application: Visualizes the results of a game, showing which numbers were drawn,
     * which were selected, and whether the selections were correct or not.
     */
    public void updateButtons(List<Integer> drawnNumbers, List<Integer> selectedNumbers) {
        for (Node node : getChildren()) {
            if (node instanceof BetButton) {
                BetButton betButton = (BetButton) node;
                int number = betButton.getNumber();

                if (drawnNumbers.contains(number) && selectedNumbers.contains(number)) {
                    betButton.setState(BetButton.ButtonState.CORRECT);
                } else if (drawnNumbers.contains(number) && !selectedNumbers.contains(number)) {
                    betButton.setState(BetButton.ButtonState.DRAWN);
                } else if (selectedNumbers.contains(number) && !drawnNumbers.contains(number)) {
                    betButton.setState(BetButton.ButtonState.INCORRECT);
                } else {
                    betButton.setState(BetButton.ButtonState.UNSELECTED);
                }
            }
        }
    }

    /*
     * Public method
     *
     * Description: Enables or disables all BetButtons in the grid based on the input value.
     *
     * Application: Allows you to enable or disable user interaction with the BetCardGrid.
     */
    public void enableButtons(boolean enable) {
        for (Node node : getChildren()) {
            if (node instanceof BetButton) {
                BetButton betButton = (BetButton) node;
                betButton.setDisable(!enable);
            }
        }
    }

    /*
     * Public method
     *
     * Description: Retrieves a list of selected numbers from the BetCardGrid based on the current
     * state of the BetButtons.
     *
     * Application: Returns the user's current selections to be used in gameplay or for display purposes.
     */
    public List<Integer> getSelectedNumbers() {
        List<Integer> selectedNumbers = new ArrayList<>();
        for (Node node : getChildren()) {
            if (node instanceof BetButton) {
                BetButton betButton = (BetButton) node;
                if (betButton.getState() == BetButton.ButtonState.SELECTED) {
                    selectedNumbers.add(betButton.getNumber());
                }
            }
        }
        return selectedNumbers;
    }

}