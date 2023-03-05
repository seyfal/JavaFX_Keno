import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class KenoGame {

    private int numDrawings;
    private int numSpots;
    private int totalWinnings;
    private List<Integer> selectedNumbers;
    private List<Integer> drawnNumbers;

    public KenoGame(int numDrawings, int numSpots) {
        this.numDrawings = numDrawings;
        this.numSpots = numSpots;
        this.totalWinnings = 0;
        this.selectedNumbers = new ArrayList<>();
        this.drawnNumbers = new ArrayList<>();
    }

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
