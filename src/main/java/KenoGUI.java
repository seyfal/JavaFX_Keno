//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class KenoGUI extends Application {
//    private static final int NUM_SPOTS_1 = 1;
//    private static final int NUM_SPOTS_4 = 4;
//    private static final int NUM_SPOTS_8 = 8;
//    private static final int NUM_SPOTS_10 = 10;
//
//    private static final int NUM_DRAWINGS_MIN = 1;
//    private static final int NUM_DRAWINGS_MAX = 4;
//
//    private static final int SPOT_SIZE = 50;
//
//    private KenoGame kenoGame;
//
//    private Stage primaryStage;
//    private Scene welcomeScene;
//    private Scene kenoScene;
//
//    private GridPane betCardGridPane;
//    private Label numSpotsLabel;
//    private Label numDrawingsLabel;
//    private Label winningsLabel;
//    private Label gameResultLabel;
//
//    private Set<Integer> selectedSpots = new HashSet<>();
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//        primaryStage.setTitle("Keno");
//
//        createKenoScene();
//
//        primaryStage.setScene(welcomeScene);
//        primaryStage.show();
//    }
//
//    // Create the Keno scene
//    private void createKenoScene() {
//        BorderPane borderPane = new BorderPane();
//        borderPane.setPadding(new Insets(20));
//        Scene kenoScene = new Scene(borderPane, 700, 700);
//
//        // Create the menu bar
//        MenuBar menuBar = new MenuBar();
//        Menu menu = new Menu("Menu");
//        MenuItem rulesMenuItem = new MenuItem("Rules");
//        rulesMenuItem.setOnAction(e -> showRulesDialog());
//        MenuItem oddsMenuItem = new MenuItem("Odds");
//        oddsMenuItem.setOnAction(e -> showOddsDialog());
//        MenuItem newLookMenuItem = new MenuItem("New Look");
//        newLookMenuItem.setOnAction(e -> changeLook());
//        MenuItem exitMenuItem = new MenuItem("Exit");
//        exitMenuItem.setOnAction(e -> Platform.exit());
//        menu.getItems().addAll(rulesMenuItem, oddsMenuItem, newLookMenuItem, exitMenuItem);
//        menuBar.getMenus().add(menu);
//        borderPane.setTop(menuBar);
//
//        // Create the bet card
//        GridPane betCard = new GridPane();
//        betCard.setPadding(new Insets(20));
//        betCard.setHgap(10);
//        betCard.setVgap(10);
//        for (int i = 1; i <= 80; i++) {
//            Button button = new Button(Integer.toString(i));
//            button.setOnAction(e -> selectNumber(button));
//            button.setPrefWidth(50);
//            button.setPrefHeight(50);
//            betCard.add(button, (i - 1) % 10, (i - 1) / 10);
//            betCard.setHalignment(button, HPos.CENTER);
//            betCard.setValignment(button, VPos.CENTER);
//            betCardNumbers.add(button);
//        }
//        borderPane.setCenter(betCard);
//
//        // Create the controls for selecting number of spots and drawings
//        HBox controlBox = new HBox();
//        controlBox.setPadding(new Insets(20));
//        controlBox.setSpacing(20);
//        Label numSpotsLabel = new Label("Number of Spots:");
//        numSpotsLabel.setPrefWidth(150);
//        ComboBox<Integer> numSpotsComboBox = new ComboBox<>(FXCollections.observableArrayList(1, 4, 8, 10));
//        numSpotsComboBox.getSelectionModel().selectFirst();
//        numSpotsComboBox.setOnAction(e -> selectNumSpots(numSpotsComboBox.getValue()));
//        Label numDrawingsLabel = new Label("Number of Drawings:");
//        numDrawingsLabel.setPrefWidth(150);
//        ComboBox<Integer> numDrawingsComboBox = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4));
//        numDrawingsComboBox.getSelectionModel().selectFirst();
//        numDrawingsComboBox.setOnAction(e -> selectNumDrawings(numDrawingsComboBox.getValue()));
//        controlBox.getChildren().addAll(numSpotsLabel, numSpotsComboBox, numDrawingsLabel, numDrawingsComboBox);
//        borderPane.setBottom(controlBox);
//
//        // Create the status bar
//        HBox statusBar = new HBox();
//        statusBar.setPadding(new Insets(20));
//        statusBar.setSpacing(20);
//        statusLabel = new Label("Select number of spots and drawings to start playing");
//        statusLabel.setPrefWidth(600);
//        statusLabel.setAlignment(Pos.CENTER);
//        Button startButton = new Button("Start");
//        startButton.setOnAction(e -> startGame());
//        startButton.setPrefWidth(80);
//        startButton.setDisable(true);
//        statusBar.getChildren().addAll(statusLabel, startButton);
//        borderPane.setTop(statusBar);
//
//        // Set the scene and show the stage
//        primaryStage.setScene(kenoScene);
//        primaryStage.show();
//
