import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

    // variables:
    private int numSpots; // the number of spots the player has chosen to play.
    private int[] spots; // an array to store the numbers that the player has selected on the bet card
    private int numSelected; // the number of spots that the player has currently selected
    private int maxSelected; // the maximum number of spots that the player is allowed to select based on their initial choice of numSpots
    private GridPane gridPane; // the JavaFX GridPane that will be used to display the bet card
//    private static final int ROWS = 10; // the number of rows in the bet card grid
//    private static final int COLS = 8; // the number of columns in the bet card grid

    // functions:

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
                BetButton betButton = new BetButton(number);
                add(betButton, j, i);
                number++;
            }
        }
        // disableAll();
    }

    /**
     * This function checks if the given number is already selected on the bet card.
     * It should return a boolean value true if the number is selected and false otherwise.
     *
     * @param number the number to check
     * @return true if the number is selected, false otherwise
     */
    public boolean isSelected(int number) {
        for (int i = 0; i < numSelected; i++) { // looping through the numbers that the player has selected on the bet card
            if (spots[i] == number) { // if the number that the player has selected on the bet card is already selected
                return true; // return true
            }
        }
        return false; // return false if the number that the player has selected on the bet card is not already selected
    }

    /**
     * This function adds the given number to the list of selected spots on the bet card.
     * If the maximum number of spots has already been selected, the function should do nothing.
     *
     * @param number the number to select
     */
    public void select(int number) {
        if (numSelected < maxSelected) { // if the maximum number of spots has not already been selected
            spots[numSelected] = number; // add the number to the list of selected spots on the bet card
            numSelected++; // increment the numSelected instance variable
            updateCell(number, true); // calling the updateCell() method
        }
    }

    /**
     * This function removes the given number from the list of selected spots on the bet card.
     *
     * @param number the number to deselect
     */
    public void deselect(int number) {
        for (int i = 0; i < numSelected; i++) { // looping through the numbers that the player has selected on the bet card
            if (spots[i] == number) { // if the number that the player has selected on the bet card is already selected
                for (int j = i; j < numSelected - 1; j++) { // looping through the numbers that the player has selected on the bet card
                    spots[j] = spots[j + 1]; // removing the number from the list of selected spots on the bet card
                }
                spots[numSelected - 1] = 0; // setting the last element in the spots array to 0
                numSelected--; // decrement the numSelected instance variable
                updateCell(number, false); // calling the updateCell() method
                break; // break out of the loop
            }
        }
    }

    /**
     * This function disables all the cells in the bet card.
     */
    public void disableAll() {
        for (Node node : this.getChildren()) { // looping through the cells in the bet card
            node.setDisable(true); // disabling the cells in the bet card
        }
    }

    /**
     * This function enables all the cells in the bet card.
     */
    public void enableAll() {
        for (Node node : this.getChildren()) { // looping through the cells in the bet card
            node.setDisable(false); // enabling the cells in the bet card
        }
    }

    /**
     * This function returns an array of integers representing the selected spots on the bet card.
     *
     * @return an array of integers representing the selected spots on the bet card
     */
    public int[] getSelectedSpots() {
        int[] selectedSpots = new int[numSelected]; // creating an array of integers to store the numbers that the player has selected on the bet card
        System.arraycopy(spots, 0, selectedSpots, 0, numSelected); // copying the numbers that the player has selected on the bet card to the selectedSpots array
        return selectedSpots; // returning the selectedSpots array
    }

    /**
     * This function updates the visual appearance of the cell corresponding
     * to the given number on the bet card based on whether it is selected or not.
     */
    private void updateCell(int number, boolean selected) {
        for (Node node : this.getChildren()) {
            if (node instanceof Button) {
                Button btn = (Button) node;
                if (btn.getText().equals(String.valueOf(number))) {
                    if (selected) {
                        btn.setStyle("-fx-background-radius: 20; -fx-padding: 5; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-color: #ffffff; -fx-border-color: #0000ff; -fx-border-width: 2; -fx-border-radius: 20; -fx-font-family: System; -fx-font-size: 12");
                    } else {
                        btn.setStyle("-fx-background-radius: 20; -fx-padding: 5; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-color: #ffffff; -fx-border-color: transparent; -fx-font-family: System; -fx-font-size: 12");
                    }
                }
            }
        }
    }

    /**
     * This function sets the maximum number of spots that can be selected on the bet card.
     */
    public void setMaxSpots(int maxSpots) {
        this.maxSelected = maxSpots;
    }


}