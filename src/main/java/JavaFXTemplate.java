import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.FontWeight;


public class JavaFXTemplate extends Application {
	private BorderPane root;
	private Text welcomeText;
	private Button backButton;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to JavaFX");

		Rectangle rect = new Rectangle(100, 40, 100, 100);
		rect.setArcHeight(50);
		rect.setArcWidth(50);
		rect.setFill(Color.VIOLET);

		RotateTransition rt = new RotateTransition(Duration.millis(5000), rect);
		rt.setByAngle(270);
		rt.setCycleCount(4);
		rt.setAutoReverse(true);
		SequentialTransition seqTransition = new SequentialTransition(
				new PauseTransition(Duration.millis(500)),
				rt
		);
		seqTransition.play();

		FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
		ft.setFromValue(1.0);
		ft.setToValue(0.3);
		ft.setCycleCount(4);
		ft.setAutoReverse(true);

		ft.play();

		root = new BorderPane();

		// add menu bar
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("Menu");
		MenuItem rules = new MenuItem("Game Rules");
		MenuItem odds = new MenuItem("Odds of Winning");
		MenuItem newLook = new MenuItem("New Look");
		MenuItem exit = new MenuItem("Exit");
		fileMenu.getItems().addAll(rules, odds, newLook, exit);

		menuBar.getMenus().addAll(fileMenu);
		root.setTop(menuBar);

		// add welcome text
		welcomeText = new Text("Welcome to Keno");
		welcomeText.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
		welcomeText.setFill(Color.WHITE);
		welcomeText.setStroke(Color.BLACK);
		welcomeText.setStrokeType(StrokeType.OUTSIDE);
		welcomeText.setStrokeWidth(2);
		root.setCenter(welcomeText);

		// add play button
		Button playButton = new Button("Play");
		playButton.setFont(new Font(40));
		playButton.setOnAction(event -> {
			welcomeText.setText("Play Screen");
			// remove "Play" button without removing the welcome text
			root.setCenter(null);


		});


		VBox vBox = new VBox(welcomeText, playButton);
		vBox.setAlignment(Pos.CENTER);
		root.setCenter(vBox);

		root.setStyle("-fx-background-image: url('/KenoBackground.jpg');" +
				"-fx-background-size: cover;" +
				"-fx-background-position: left center;");
		Scene scene = new Scene(root, 700, 700);
		scene.setFill(Color.BLUE);
		primaryStage.setScene(scene);

		primaryStage.show();

		// add event handlers for menu items
		rules.setOnAction(event -> {
			root.getChildren().remove(menuBar);
			welcomeText.setText("Rules");
			root.setCenter(welcomeText);
			backButton = new Button("Back");
			backButton.setOnAction(event1 -> {
				root.setTop(menuBar);
				welcomeText.setText("Welcome to Keno");
				root.setLeft(null);
				vBox.getChildren().clear();
				vBox.getChildren().addAll(welcomeText, playButton);
				root.setCenter(vBox);
			});
			VBox vbox = new VBox(10, backButton);
			root.setLeft(vbox);
		});

		odds.setOnAction(event -> {

			root.getChildren().remove(menuBar);
			welcomeText.setText("Odds");
			root.setCenter(welcomeText);
			backButton = new Button("Back");
			backButton.setOnAction(event1 -> {
				root.setTop(menuBar);
				welcomeText.setText("Welcome to Keno");
				root.setLeft(null);
				vBox.getChildren().clear();
				vBox.getChildren().addAll(welcomeText, playButton);
				root.setCenter(vBox);
			});
			VBox vbox = new VBox(10, backButton);
			root.setLeft(vbox);
		});

		newLook.setOnAction(event -> {

			root.getChildren().remove(menuBar);
			welcomeText.setText("Change Look");
			root.setCenter(welcomeText);
			backButton = new Button("Back");

			backButton.setOnAction(event1 -> {
				root.setTop(menuBar);
				welcomeText.setText("Welcome to Keno");
				root.setLeft(null);
				vBox.getChildren().clear();
				vBox.getChildren().addAll(welcomeText, playButton);
				root.setCenter(vBox);
			});
			VBox vbox = new VBox(10, backButton);
			root.setLeft(vbox);
		});

		exit.setOnAction(event -> primaryStage.close());
	}

}