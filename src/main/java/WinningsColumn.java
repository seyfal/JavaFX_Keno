import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;

public class WinningsColumn extends VBox {
    private List<Label> winningsLabels = new ArrayList<>();

    public WinningsColumn() {
        super(10);
        setPadding(new Insets(10, 10, 10, 10));
        setStyle("-fx-background-color: white;");

        for (int i = 1; i <= 10; i++) {
            HBox row = new HBox(5);
            Label spotsLabel = new Label("Spots: " + i);
            Label winningsLabel = new Label("Winnings: $...");
            row.getChildren().addAll(spotsLabel, winningsLabel);
            winningsLabels.add(winningsLabel);
            getChildren().add(row);
        }

        VBox emptyBox = new VBox();
        VBox.setVgrow(emptyBox, Priority.ALWAYS);
        getChildren().add(emptyBox);
    }

    public void updateWinnings(int numSpots, int[] winnings) {
        // Update the winningsLabels based on numSpots and the provided winnings array
    }
}
