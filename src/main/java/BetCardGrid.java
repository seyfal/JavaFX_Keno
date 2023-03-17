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

 * Usage:

 * Create a BetCardGrid with 5 rows and 10 columns
 * BetCardGrid betCardGrid = new BetCardGrid(5, 10);

 * Set the maximum number of spots that can be selected on the bet card
 * betCardGrid.setMaxSpots(5);

 * Create a BorderPane as the root layout and add the BetCardGrid to it
 * BorderPane root = new BorderPane();
 * root.setCenter(betCardGrid);

 * Create a scene, set the root layout, and display the stage
 * Scene scene = new Scene(root, 600, 400);
 * primaryStage.setScene(scene);
 * primaryStage.setTitle("BetCardGrid Example");
 * primaryStage.show();
 *
 */
class BetCardGrid extends GridPane {

    private int maxSpots;
    private int numDraws;

    public BetCardGrid(int rows, int columns) {
        setHgap(12);
        setVgap(12);
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

    public BetButton getBetButton(int number) {
        for (Node node : getChildren()) {
            if (node instanceof BetButton) {
                BetButton betButton = (BetButton) node;
                if (betButton.getNumber() == number) {
                    return betButton;
                }
            }
        }
        return null;
    }

    public void setMaxSpots(int maxSpots) {
        this.maxSpots = maxSpots;
    }

    public void setNumDraws(int newValue)
    {
        this.numDraws = newValue;
    }

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

    public void enableButtons(boolean enable) {
        for (Node node : getChildren()) {
            if (node instanceof BetButton) {
                BetButton betButton = (BetButton) node;
                betButton.setDisable(!enable);
            }
        }
    }

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