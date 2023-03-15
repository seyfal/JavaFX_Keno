import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KenoGame {

    private int numDrawings;
    private int numSpots;
    private int totalWinnings;
    private List<Integer> drawnNumbers;

    public KenoGame() {
        this.numDrawings = 0;
        this.numSpots = 0;
        this.totalWinnings = 0;
        this.drawnNumbers = new ArrayList<>();
    }

    public int playDrawing(List<Integer> selectedNumbers) {
        drawnNumbers.clear();
        drawNumbers();
        return calculateMatchedNumbers(selectedNumbers);
    }

    private void drawNumbers() {
        List<Integer> possibleNumbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) {
            possibleNumbers.add(i);
        }
        Collections.shuffle(possibleNumbers, new Random());
        for (int i = 0; i < 20; i++) {
            drawnNumbers.add(possibleNumbers.get(i));
        }
    }

    public int calculateWinnings(int matchedNumbers) {
        int winnings = 0;

        // Calculate winnings based on the matched numbers and numSpots
        // You should replace the following code with the actual payout rules for your Keno game
        if (numSpots == 1) {
            if (matchedNumbers == 1) {
                winnings += 2;
            }
        } else if (numSpots == 4) {
            if (matchedNumbers == 2) {
                winnings += 1;
            } else if (matchedNumbers == 3) {
                winnings += 5;
            } else if (matchedNumbers == 4) {
                winnings += 75;
            }
        } else if (numSpots == 8) {
            if (matchedNumbers == 4) {
                winnings += 2;
            } else if (matchedNumbers == 5) {
                winnings += 12;
            } else if (matchedNumbers == 6) {
                winnings += 50;
            } else if (matchedNumbers == 7) {
                winnings += 750;
            } else if (matchedNumbers == 8) {
                winnings += 10000;
            }
        } else if (numSpots == 10) {
            if (matchedNumbers == 0) {
                winnings += 5;
            }else if (matchedNumbers == 5) {
                winnings += 2;
            } else if (matchedNumbers == 6) {
                winnings += 15;
            } else if (matchedNumbers == 7) {
                winnings += 40;
            } else if (matchedNumbers == 8) {
                winnings += 450;
            } else if (matchedNumbers == 9) {
                winnings += 4250;
            } else if (matchedNumbers == 10) {
                winnings += 100000;
            }
        }

        return winnings;
    }

    public int calculateMatchedNumbers(List<Integer> selectedNumbers) {
        int matchedNumbers = 0;
        for (Integer number : selectedNumbers) {
            if (drawnNumbers.contains(number)) {
                matchedNumbers++;
            }
        }
        return matchedNumbers;
    }

    // Getters
    public int getNumDraws() {
        return numDrawings;
    }

    public int getNumSpots() {
        return numSpots;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

    public List<Integer> getDrawnNumbers() {
        return drawnNumbers;
    }

    // Setters
    public void setNumDraws(int numDraws) {
        this.numDrawings = numDraws;
    }

    public void setNumSpots(int numSpots) {
        this.numSpots = numSpots;
    }

    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }
}
