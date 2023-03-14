import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
//import javafx.scene.input.MouseEvent;
//import javafx.event.EventHandler;


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

    // functions:

    /**
     * This constructor initializes the instance variables for the BetCardGrid object,
     * creates the GridPane and adds Button objects to it for each of the numbers in the bet card.
     * The Button objects are set up with ActionEvent handlers to allow the player to select and
     * deselect numbers. Finally, all cells in the GridPane are initially disabled.
     *
     * @param numSpots the number of spots on the bet card
     */
    public BetCardGrid(int numSpots) {
        this.numSpots = numSpots; // assigning the value of the numSpots parameter to the numSpots instance variable
        this.maxSelected = numSpots; // assigning the value of the numSpots parameter to the maxSelected instance variable
        this.spots = new int[80]; // creating an array of 80 integers to store the numbers that the player has selected on the bet card
        this.numSelected = 0; // assigning the value of 0 to the numSelected instance variable

        // Initialize the GridPane
        this.gridPane = new GridPane(); // creating a new GridPane object and assigning it to the gridPane instance variable
        gridPane.setHgap(10); // setting the horizontal gap between the cells in the GridPane to 10
        gridPane.setVgap(10); // setting the vertical gap between the cells in the GridPane to 10

        // Create the bet card cells and add them to the GridPane
        for (int i = 1; i <= 80; i++) { // looping through the numbers 1 to 80
            Button btn = new Button(String.valueOf(i)); // creating a new Button object and assigning it to the btn variable
            btn.setPrefSize(40, 40); // setting the preferred size of the Button object to 40 x 40
            btn.setOnAction(event -> { // setting an ActionEvent handler for the Button object
                int number = Integer.parseInt(btn.getText()); // creating an integer variable called number and assigning it the value of the number that the player has selected on the bet card
                if (isSelected(number)) { // if the number that the player has selected on the bet card is already selected
                    deselect(number); // deselect the number
                } else { // if the number that the player has selected on the bet card is not already selected
                    select(number); // select the number
                }
            });
            gridPane.add(btn, (i - 1) % 10, (i - 1) / 10); // adding the Button object to the GridPane
        }

        // Disable all cells initially
        disableAll(); // calling the disableAll() method
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
        for (Node node : gridPane.getChildren()) { // looping through the cells in the bet card
            node.setDisable(true); // disabling the cells in the bet card
        }
    }

    /**
     * This function enables all the cells in the bet card.
     */
    public void enableAll() {
        for (Node node : gridPane.getChildren()) { // looping through the cells in the bet card
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
        for (Node node : gridPane.getChildren()) { // looping through the cells in the bet card
            if (node instanceof Button) { // if the cell is a Button object
                Button btn = (Button) node; // casting the cell to a Button object and assigning it to the btn variable
                if (btn.getText().equals(String.valueOf(number))) { // if the cell is the cell that the player has selected on the bet card
                    if (selected) { // if the cell is selected
                        btn.setStyle("-fx-background-color: #00ff00"); // set the background colour of the cell to green
                    } else { // if the cell is not selected
                        btn.setStyle("-fx-background-color: #ffffff"); // set the background colour of the cell to white
                    }
                }
            }
        }
    }

}