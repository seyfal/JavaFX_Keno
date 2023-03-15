import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.List;

/*
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

    /**
     * This constructor initializes the instance variables for the BetCardGrid object,
     * creates the GridPane and adds Button objects to it for each of the numbers in the bet card.
     * The Button objects are set up with ActionEvent handlers to allow the player to select and
     * deselect numbers. Finally, all cells in the GridPane are initially disabled.
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
        // disable all cells in the grid initially
        setDisable(true);
    }

    /**
     * This function creates a BetButton object for the given number.
     * @return BetButton
     */
    private BetButton createBetButton(int number) {
        return new BetButton(number);
    }

    /**
     * This function sets the maximum number of spots that can be selected on the bet card.
     */
    public void setMaxSpots(int maxSpots) {
        // TODO - implement this function
    }

    /**
     * This function resets the state of all BetButtons in the grid.
     */
    public void resetButtons() {
        for (Node node : getChildren()) {
            if (node instanceof BetButton) {
                BetButton betButton = (BetButton) node;
                betButton.setState(BetButton.ButtonState.UNSELECTED);
            }
        }
    }

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

    public void enableCells(boolean enable) {
        for (Node node : getChildren()) {
            if (node instanceof BetButton) {
                BetButton betButton = (BetButton) node;
                betButton.setDisable(!enable);
            }
        }
    }


}