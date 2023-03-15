import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class WinningsData {
    private final StringProperty matchedNumbers;
    private final StringProperty winnings;

    public WinningsData(String matchedNumbers, String winnings) {
        this.matchedNumbers = new SimpleStringProperty(matchedNumbers);
        this.winnings = new SimpleStringProperty(winnings);
    }

    public static ObservableList<WinningsData> getWinningsData(int numSpots) {
        ObservableList<WinningsData> data = FXCollections.observableArrayList();

        if (numSpots == 1) {
            data.add(new WinningsData("1", "2"));
        } else if (numSpots == 4) {
            data.add(new WinningsData("2", "1"));
            data.add(new WinningsData("3", "5"));
            data.add(new WinningsData("4", "75"));
        } else if (numSpots == 8) {
            data.add(new WinningsData("4", "2"));
            data.add(new WinningsData("5", "12"));
            data.add(new WinningsData("6", "50"));
            data.add(new WinningsData("7", "750"));
            data.add(new WinningsData("8", "10000"));
        } else if (numSpots == 10) {
            data.add(new WinningsData("0", "5"));
            data.add(new WinningsData("5", "2"));
            data.add(new WinningsData("6", "15"));
            data.add(new WinningsData("7", "40"));
            data.add(new WinningsData("8", "450"));
            data.add(new WinningsData("9", "4250"));
            data.add(new WinningsData("10", "100000"));
        }

        return data;
    }
}
