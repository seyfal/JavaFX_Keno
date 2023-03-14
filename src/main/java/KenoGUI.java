//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.MenuItem;
//import javafx.scene.control.ToggleButton;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Random;
//import java.util.Set;
//
//// This class is going to be called by the button in the JavaFXTemplate class.
//// it is going to be a new JavaFX scene
//
//// This class will be responsible for creating the GUI for the Keno game.
//
//// It will have to create the following:
//// 1. A screen that will allow the user to select
//// the number of spots they want to play (1,4, 8, 10)
//// the number of drawings they want to play ( 1 to 4 )
//// the betCardGrid
//
//// Your implementation will allow a single player to play. They will fill out a single bet card
//// with the number of spots they have chosen to play. They will also have the option of letting
//// the game pick their numbers for them. They will decided how many drawings they want to play
//// the bet card for. After each drawing, the player will be informed of how many numbers they
//// matched, what those numbers were and how much they have won on that drawing. They will also
//// be notified of the total they have won since they started the program. After the selected
//// amount of drawings have completed, the player will be able to fill out a new bet card, spots
//// to play and drawings to play or they may exit the program.
//
//// so create an auto fill button that will fill the bet card with random numbers
////
//
//// will include :
//// The same menu as the welcome screen with an additional menu option: New Look.
//// This option, when implemented, will change the look of the GUI; such as new
//// colors, fonts, images....etc. While there is no minimum for elements to change,
//// the new look must be noticeable to the average user.
//
//// so the menu at the top left that will be a drop down menu with the following options:
//// Rules
//// Odds
//// New Look
//// Exit
//
//// The bet card will be displayed and you must use JavaFX GridPane to implement it.
//// It will be a 8X10 grid of clickable Nodes (Buttons, ImageViews, etc.). Each of
//// the Nodes should display the number it represents (1-80).
//// in our case we will use buttons and everything that we have in the betCardGrid class
//// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/ GridPane.html
//
//// The Nodes in the GridPane should be disabled until the player decides on how many
//// spots they want to play.
//
//// There must be a way for the player to pick how many spots to play(1,4,8 or 10).
//// This can not change once the drawings begin.
//
//// There must be a way for the player to pick how many drawings they will play their bet
//// card for (minimum of 1 and maximum of 4). This can not change once the drawings begin.
//
//// Once the player decides on how many spots they want to play, the Nodes of the GridPane
//// should be enabled to allow the user to choose their numbers. The user should not be
//// able to select duplicate numbers or select more spots than they decided originally.
//
//// Once a number is selected on the bet card, it should show that it has been chosen.
//// Players can edit their choices as often as they want before the drawings occur.
//// They can not change once the drawings begin.
//
////• There should be a way that the player can select to have their numbers chosen
//// automatically and randomly for them if they don’t want to choose themselves.
//
////• When the number of spots has been chosen, bet card filled and number of drawings
//// selected the user should have a way to start the first drawing.
//
////• The drawing will display 20, randomly selected numbers (1-80) with no duplicates,
//// one by one with a pause in between selection. How they are displayed is up to you.
//// After each drawing, the user should be able to see how many numbers they matched,
//// which numbers they matched and how much they won in that particular drawing.
//// They should also be able to see what they have won since the program was started.
//
////• We will use the North Carolina state lottery Spot 1, Spot 4, Spot 8 and Spot 10
//// winnings and odds found here:
////• https://nclottery.com/KenoHow
//
////• There should be a way for the player to decide to continue to the next drawing.
////• When all drawings are complete, the user should be able to fill out a new bet card, number of spots and number of drawings or exit the program.
//
//// there will be a button that will clear the bet card in the top right corner
//
//// there will be couple of buttons in the bottom starting with the first group of
//// buttons to chose haw many spots to play, then group for the number of drawings
//// then the button to chose the numbers automatically and then the button to start
//
//// we should display the balance and hits / prize in a column to the right of the bet card
//
////    Based on the specifications you provided, here are some general steps that you can follow to create the class:
////
////    Create a new JavaFX class that will serve as the GUI for the Keno game.
////    In the constructor of the class, create a new JavaFX scene with the necessary components, such as buttons, menus, and GridPane.
////    Implement the menu at the top left with the following options: Rules, Odds, New Look, and Exit.
////    Implement the 8x10 GridPane for the bet card with clickable nodes that display the number it represents (1-80).
////    Disable the nodes in the GridPane until the player decides on how many spots they want to play.
////    Implement a way for the player to pick how many spots to play (1, 4, 8, or 10).
////    Implement a way for the player to pick how many drawings they will play their bet card for (minimum of 1 and maximum of 4).
////    Once the player decides on how many spots they want to play, enable the nodes of the GridPane to allow the user to choose their numbers.
////    Implement a way for the player to select to have their numbers chosen automatically and randomly for them if they don’t want to choose themselves.
////    Implement a button to clear the bet card in the top right corner.
////    Implement buttons for choosing how many spots to play, the number of drawings, and starting the game.
////    Implement the drawing function that will display 20, randomly selected numbers (1-80) with no duplicates, one by one with a pause in between selection. After each drawing, the user should be able to see how many numbers they matched, which numbers they matched, and how much they won in that particular drawing. They should also be able to see what they have won since the program was started.
////    Display the balance and hits/prize in a column to the right of the bet card.
////    Finally, create a way for the user to fill out a new bet card, number of spots, and number of drawings or exit the program after all drawings are complete.
//
//
//public class KenoGUI extends Application {
//
//    // GUI components
//    private Scene scene; //
//    private GridPane betCardGrid;
//    private ToggleButton autoFillButton;
//    private Label balanceLabel;
//    private Label hitsLabel;
//    private Button clearButton;
//    private Button startButton;
//    private Button newBetButton;
//    private MenuBar menuBar;
//    private Menu gameMenu;
//    private MenuItem rulesMenuItem;
//    private MenuItem oddsMenuItem;
//    private MenuItem newLookMenuItem;
//    private MenuItem exitMenuItem;
//    private VBox leftPane;
//    private HBox spotsPane;
//    private HBox drawingsPane;
//    private VBox rightPane;
//    private BorderPane mainPane;
//
//    // State variables
//    private int numSpots;
//    private int numDrawings;
//    private Set<Integer> selectedSpots;
//    private ArrayList<Integer> winningNumbers;
//    private double balance;
//    private double totalHits;
//    private double totalPrize;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        // Initialize state variables
//        numSpots = 0;
//        numDrawings = 0;
//        selectedSpots = new HashSet<>();
//        winningNumbers = new ArrayList<>();
//        balance = 100;
//        totalHits = 0;
//        totalPrize = 0;
//
//        // Create GUI components
//        createMenuBar();
//        createBetCardGrid();
//        createAutoFillButton();
//        createBalanceLabel();
//        createHitsLabel();
//        createClearButton();
//        createSpotsPane();
//        createDrawingsPane();
//        createStartButton();
//        createNewBetButton();
//        createLeftPane();
//        createRightPane();
//        createMainPane();
//
//        // Create scene and show stage
//        scene = new Scene(mainPane, 800, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void createMenuBar() {
//        rulesMenuItem = new MenuItem("Rules");
//        oddsMenuItem = new MenuItem("Odds");
//        newLookMenuItem = new MenuItem("New Look");
//        exitMenuItem = new MenuItem("Exit");
//        gameMenu = new Menu("Game");
//        gameMenu.getItems().addAll(rulesMenuItem, oddsMenuItem, newLookMenuItem, exitMenuItem);
//        menuBar = new MenuBar();
//        menuBar.getMenus().add(gameMenu);
//    }
//
//    private void createBetCardGrid() {
//        betCardGrid = new GridPane();
//        betCardGrid.setPadding(new Insets(10));
//        betCardGrid.setHgap(5);
//        betCardGrid.setVgap(5);
//        betCardGrid.setDisable(true);
//
//        for (int i = 1; i <= SPOT_1_MAX; i++) {
//            int row = (i - 1) / 10;
//            int
//
//        }
//
