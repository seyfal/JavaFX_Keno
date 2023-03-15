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

public class KenoGame extends JavaFXTemplate{

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
    private BetCardGrid betCardGrid;
    private Label resultsLabel;

    public KenoGame(int numDrawings, int numSpots, BetCardGrid betCardGrid, Label resultsLabel) {
        this.numDrawings = numDrawings;
        this.numSpots = numSpots;
        this.totalWinnings = 0;
        this.selectedNumbers = new ArrayList<>();
        this.drawnNumbers = new ArrayList<>();
        this.betCardGrid = betCardGrid;
        this.resultsLabel = resultsLabel;
    }

    /*
        * Select number of spots
        * @param numSpots number of spots
     */
    public int playDrawing() {
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

        // TODO - need to get the selected numbers from the bet card

        // Update the appearance of the BetButtons based on the drawn numbers
        betCardGrid.updateButtons(drawnNumbers, selectedNumbers);

        // Calculate winnings based on selected numbers and drawn numbers
        // int winnings = calculateWinnings();
        // TODO - fix the Winnings calculation - it's not working
        // totalWinnings += winnings;

        // Display results to user
        // resultsLabel.setText(String.format("Matched %d numbers! Won $%d on this drawing.", selectedNumbers.size(), winnings));

        // Disable bet card for this drawing
        betCardGrid.setDisable(true);

        // Return winnings
        // return winnings;
        return 0;
    }

    // Reset bet card and selected numbers
    public void clearBetCard() {
        selectedNumbers.clear();
        betCardGrid.resetButtons();
    }

    public int getNumDrawings() {
        return numDrawings;
    }

    public int getNumSpots() {
        return numSpots;
    }

    public void setNumSpots(int numSpots) {
        this.numSpots = numSpots;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

    public void resetSelection() {
        selectedNumbers.clear();
    }

}
