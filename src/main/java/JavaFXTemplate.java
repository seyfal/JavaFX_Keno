import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.FontWeight;

import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;


public
class JavaFXTemplate extends Application {
    private BorderPane root; // root node of scene graph
    private Text       welcomeText; // welcome text
    private Button     backButton; // back button
    private int        newLookNum; // number of times new-look button has been clicked

    public static
    void main (String[] args) { // entry point of JavaFX application
        launch(args); // launch JavaFX application
    }


    @Override
    public
    // TODO: Take a look at the exception as it is never thrown in the mentioned method
    void start (Stage primaryStage) throws Exception { // called by launch()
        primaryStage.setTitle("Welcome to JavaFX"); // set title of window

        // @
        Rectangle rect = new Rectangle(100, 40, 100, 100); // create rectangle
        rect.setArcHeight(50); // set arc height
        rect.setArcWidth(50); // set arc width
        rect.setFill(Color.VIOLET); // set fill color

        RotateTransition rt = new RotateTransition(Duration.millis(5000), rect); // create rotate transition
        rt.setByAngle(270); // set by angle
        rt.setCycleCount(4); // set cycle count
        rt.setAutoReverse(true); // set auto reverse
        SequentialTransition seqTransition = new SequentialTransition(new PauseTransition(Duration.millis(500)), rt); // create sequential transition
        seqTransition.play(); // play sequential transition

        FadeTransition ft = new FadeTransition(Duration.millis(5000), rect); // create fade transition
        ft.setFromValue(1.0); // set from value
        ft.setToValue(0.3); // set to value
        ft.setCycleCount(4); // set cycle count
        ft.setAutoReverse(true); // set auto reverse

        ft.play(); // play fade transition
        root = new BorderPane(); // create root node of scene graph

        // add menu bar
        MenuBar menuBar = new MenuBar(); // create menu bar
        Menu fileMenu = new Menu("Menu"); // create file menu
        MenuItem rules = new MenuItem("Game Rules"); // create rules menu item
        MenuItem odds = new MenuItem("Odds of Winning"); // create odds menu item
        MenuItem newLook = new MenuItem("New Look"); // create new-look menu item
        MenuItem exit = new MenuItem("Exit"); // create exit menu item
        fileMenu.getItems().addAll(rules, odds, newLook, exit); // add menu items to file menu
        menuBar.getMenus().addAll(fileMenu); // add file menu to menu bar
        root.setTop(menuBar); // add menu bar to root node of scene graph

        // add welcome text
        welcomeText = new Text("Welcome to Keno"); // create welcome text
        welcomeText.setFont(Font.font("Verdana", FontWeight.BOLD, 40)); // set font of welcome text
        welcomeText.setFill(Color.WHITE); // set fill color of welcome text
        welcomeText.setStroke(Color.BLACK); // set stroke color of welcome text
        welcomeText.setStrokeType(StrokeType.OUTSIDE); // set stroke type of welcome text
        welcomeText.setStrokeWidth(3); // set stroke width of welcome text
        root.setCenter(welcomeText); // add welcome text to root node of scene graph

        // add play button
        Button playButton = new Button("Play"); // create play button
        playButton.setFont(new Font(30)); // set font of play button

        // ----------------- KenoController -----------------
        /*
         * KenoController is a class that controls the Keno game.
         * It is responsible for initializing the Keno UI,
         * handling the user's input, and updating the Keno UI.
         */
        playButton.setOnAction(event -> { // add event handler to play button
            KenoController kenoController = new KenoController(); // create keno controller
            kenoController.initializeKenoUI(root); // initialize keno UI
        });
        // --------------------------------------------------

        VBox vBox = new VBox(welcomeText, playButton); // create vertical box
        vBox.setAlignment(Pos.CENTER); // set alignment of vertical box
        root.setCenter(vBox); // add vertical box to root node of scene graph
        newLookNum = 0; // initialize new-look number
        String backgroundPath = "/KenoBackground" + newLookNum + ".jpg"; // create path to background image
        root.setStyle("-fx-background-image: url('" + backgroundPath + "');" + // set background image
                "-fx-background-size: cover;" + // set background size
                "-fx-background-position: left center;"); // set background position
        Scene scene = new Scene(root, 700, 700); // create scene
        primaryStage.setScene(scene); // add scene to stage
        primaryStage.show(); // display stage

        // add event handlers for menu items
        rules.setOnAction(event -> { // add event handler to rules menu item

            root.getChildren().remove(menuBar); // remove menu bar from root node of scene graph
            welcomeText.setText("Rules"); // set text of welcome text
            root.setCenter(welcomeText); // add welcome text to root node of scene graph
            backButton = new Button("Back"); // create back button

            backButton.setOnAction(event1 -> { // add event handler to back button
                root.setTop(menuBar); // add menu bar to root node of scene graph
                welcomeText.setText("Welcome to Keno"); // set text of welcome text
                root.setLeft(null); // remove back button from root node of scene graph
                vBox.getChildren().clear(); // remove welcome text and play button from vertical box
                vBox.getChildren().addAll(welcomeText, playButton); // add welcome text and play button to vertical box
                root.setCenter(vBox); // add vertical box to root node of scene graph
            });

            VBox vbox = new VBox(10, backButton); // create vertical box
            root.setLeft(vbox); // add vertical box to root node of scene graph
        });

        odds.setOnAction(event -> { // add event handler to odds menu item

            root.getChildren().remove(menuBar); // remove menu bar from root node of scene graph
            welcomeText.setText("Odds"); // set text of welcome text
            root.setCenter(welcomeText); // add welcome text to root node of scene graph
            backButton = new Button("Back"); // create back button

            backButton.setOnAction(event1 -> { // add event handler to back button

                root.setTop(menuBar); // add menu bar to root node of scene graph
                welcomeText.setText("Welcome to Keno"); // set text of welcome text
                root.setLeft(null); // remove back button from root node of scene graph
                vBox.getChildren().clear(); // remove welcome text and play button from vertical box
                vBox.getChildren().addAll(welcomeText, playButton); // add welcome text and play button to vertical box
                root.setCenter(vBox); // add vertical box to root node of scene graph

            });

            VBox vbox = new VBox(10, backButton); // create vertical box
            root.setLeft(vbox); // add vertical box to root node of scene graph
        });

        newLook.setOnAction(event -> { // add event handler to new-look menu item

            newLookNum = (newLookNum + 1) % 4; // increment new-look number
            String newBackgroundPath = "/KenoBackground" + newLookNum + ".jpg"; // create path to background image

            root.setStyle("-fx-background-image: url('" + newBackgroundPath + "');" + // set background image
                    "-fx-background-size: cover;" + // set background size
                    "-fx-background-position: left center;" + // set background position
                    "");

            // Randomize font
            String[] fonts = {"Arial", "Comic Sans MS", "Verdana", "Trebuchet MS"}; // create array of fonts
            String randomFont = fonts[newLookNum]; // get random font
            welcomeText.setFont(Font.font(randomFont, FontWeight.BOLD, 30)); // set font of welcome text

            // Randomize color
            Color[] colors = {Color.ORANGE, Color.AQUA, Color.YELLOW, Color.GREEN}; // create array of colors
            Color randomColor = colors[newLookNum]; // get random color
            welcomeText.setFill(randomColor); // set fill color of welcome text
            welcomeText.setStroke(randomColor.darker()); // set stroke color of welcome text

        });

        exit.setOnAction(event -> primaryStage.close()); // add event handler to exit menu item
    }
}