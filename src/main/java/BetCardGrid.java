import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/*
 BetCardGrid is a JavaFX GridPane that displays a bet card with 80 numbers. The player can select numbers on the bet
 card by clicking on the corresponding cell, and the selected numbers are highlighted with a green background.
 The class provides methods to select and deselect numbers, as well as to disable and enable all cells in the bet card.
 It also provides a method to retrieve an array of integers representing the selected numbers.
 Usage: To use BetCardGrid in a JavaFX application, create a new instance of the class and add it to the scene.
 The constructor takes an integer parameter representing the number of spots on the bet card.
 Example usage:
 BetCardGrid betCardGrid = new BetCardGrid(10);
 Scene scene = new Scene(betCardGrid);
 The BetCardGrid class provides the following public methods:
 boolean isSelected(int number)
 This method takes an integer parameter representing a number on the bet card, and returns a boolean value indicating whether the number is currently selected.
 void select(int number)
 This method takes an integer parameter representing a number on the bet card, and adds the number to the list of selected numbers.
 If the maximum number of spots has already been selected, the method does nothing.
 void deselect(int number)
 This method takes an integer parameter representing a number on the bet card, and removes the number from the list of selected numbers.
 void disableAll()
 This method disables all cells in the bet card.
 void enableAll()
 This method enables all cells in the bet card.
 int[] getSelectedSpots()
 This method returns an array of integers representing the selected numbers on the bet card.
 Note: This class assumes that the bet card has 80 cells numbered from 1 to 80. It also assumes that the cells are arranged in a grid with 10 columns and 8 rows.
 */

/**
 * Represents a grid of clickable circles for selecting numbers on the bet card.
 */
class BetCardGrid extends GridPane {
    private int maxSelected; // the maximum number of spots that the player is allowed to select based on their initial choice of numSpots

    /**
     * This constructor initializes the instance variables for the BetCardGrid object,
     * creates the GridPane and adds Button objects to it for each of the numbers in the bet card.
     * The Button objects are set up with ActionEvent handlers to allow the player to select and
     * deselect numbers. Finally, all cells in the GridPane are initially disabled.
     *
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
        this.maxSelected = maxSpots;
    }

}