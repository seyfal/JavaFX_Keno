import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/*
    * KenoGame class
    * Contains all the logic for the Keno game
    *
    * Starting with the KenoGame class, this class represents the overall game and contains the following attributes:
    *
    * numSpots: an integer representing the number of spots the player has chosen to play
    * numDrawings: an integer representing the number of drawings the player has chosen to play.
    * betCard: an instance of the BetCard class representing the player's bet card.
    * drawnNumbers: a list of integers representing the numbers that have been randomly drawn in each drawing.
    * winnings: a double representing the total amount that the player has won since starting the game.
    * gameResults: a list of instances of the GameResult class representing the results of each drawing.
    *
    * The KenoGame class also has several methods that are used to play the game, including:
    *
    * startGame(): a method that starts the game and initializes the necessary variables.
    * selectNumSpots(numSpots: int): a method that allows the player to select the number of spots they want to play.
    * selectNumDrawings(numDrawings: int): a method that allows the player to select the number of drawings they want to play.
    * selectBetCardNumber(number: int): a method that allows the player to select a number on their bet card.
    * + clearBetCard(): a method that clears the player's bet card.
    * bercalculateWinnings(): a method that calculates the player's winnings for each drawing.
    * updateGameResults(): a method that updates the list of game results.
    * getGameResults(): a method that returns the list of game results.
 */

public class KenoGame {

    private int numDrawings; // user chooses number of drawings
    private int numSpots; // user chooses number of spots 1, 4, 8 or 10
    private int totalWinnings; // total winnings for all drawings
    private List<Integer> selectedNumbers; // numbers selected by user in the bet card
    private List<Integer> drawnNumbers; // numbers drawn in each drawing ( winning numbers )

    /*
        * Constructor for KenoGame
        * @param numDrawings number of drawings
        * @param numSpots number of spots
        * @param totalWinnings total winnings for all drawings
        * @param selectedNumbers numbers selected by user in the bet card
        * @param drawnNumbers numbers drawn in each drawing ( winning numbers )
     */
    public KenoGame(int numDrawings, int numSpots) {
        this.numDrawings = numDrawings;
        this.numSpots = numSpots;
        this.totalWinnings = 0;
        this.selectedNumbers = new ArrayList<>();
        this.drawnNumbers = new ArrayList<>();
    }

    /*
        * Select number of spots
        * @param numSpots number of spots
     */
    public int playDrawing(GridPane betCard, Label resultsLabel) {
        // Clear previously drawn numbers
        drawnNumbers.clear();
        // Draw 20 unique numbers between 1 and 80
        List<Integer> possibleNumbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) {
            possibleNumbers.add(i);
        }
        Collections.shuffle(possibleNumbers, new Random());
        for (int i = 0; i < 20; i++) {
            drawnNumbers.add(possibleNumbers.get(i));
        }
        // Calculate winnings based on selected numbers and drawn numbers
        int winnings = calculateWinnings();
        totalWinnings += winnings;
        // Display results to user
        resultsLabel.setText(String.format("Matched %d numbers! Won $%d on this drawing.", selectedNumbers.size(), winnings));
        // Disable bet card for this drawing
        disableBetCard(betCard);
        // Return winnings
        return winnings;
    }

    private int calculateWinnings() {
        int[] spotWinnings = {0, 2, 12, 48, 180}; // Winnings for each number of spots (1-4)
        int[] spotHits = new int[5]; // Number of spots hit (0-4)
        for (int number : selectedNumbers) {
            if (drawnNumbers.contains(number)) {
                spotHits[numSpots]++;
            }
        }
        return spotWinnings[numSpots] * spotHits[numSpots];
    }

    private void disableBetCard(GridPane betCard) {
        for (int i = 1; i <= 80; i++) {
            Button button = (Button) betCard.lookup("#button" + i);

            /*
                It declares a variable called "button" of type "Button".
                It is using the "lookup" method of the "betCard" object to search for a child node with the ID "button" concatenated with the value of "i".
                The "lookup" method returns a "Node" object, which is then casted to type "Button" and assigned to the "button" variable.
             */

            button.setDisable(true);
            if (selectedNumbers.contains(i)) {
                button.getStyleClass().add("selected");
            }
        }
    }

    public void setSelectedNumbers(List<Integer> selectedNumbers) {
        this.selectedNumbers = selectedNumbers;
    }

    public int getNumDrawings() {
        return numDrawings;
    }

    public int getNumSpots() {
        return numSpots;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

}
