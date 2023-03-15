import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class winningsColumn extends VBox {
    private KenoGame kenoGame;

    public winningsColumn() {
//        this.kenoGame = kenoGame;
        setSpacing(10);
        setPadding(new Insets(10, 10, 10, 10));
        setStyle("-fx-background-color: white;");
        setAlignment(Pos.CENTER);
        update(1);
    }

    public void update(int spots) {
        getChildren().clear();

        // Add the balance label on top
        Label balanceLabel = new Label("Balance: $" + kenoGame.getBalance());
        getChildren().add(balanceLabel);

        // Add paytable rows based on the selected spots
        for (int i = 1; i <= spots; i++) {
            HBox row = new HBox(5);
            row.getChildren().addAll(new Label("Spots: " + i), new Label("Winnings: $..."));
            getChildren().add(row);
        }
    }
}