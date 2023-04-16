/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package guessthenumbergame;

import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GuessTheNumberGame extends Application {

    private Label titleLabel;
    private Label instructionsLabel;
    private Label numberLabel;
    private Label rangeLabel;
    private Button guessButton;
    private Button retry;
    private Button End;
    private Slider numberSlider;
    Random random = new Random();
    int numberToGuess = random.nextInt(100) + 1;
    int numGuesses = 0;
    String result;

    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setMaxHeight(Double.NEGATIVE_INFINITY);
        root.setMaxWidth(Double.NEGATIVE_INFINITY);
        root.setMinHeight(Double.NEGATIVE_INFINITY);
        root.setMinWidth(Double.NEGATIVE_INFINITY);
        root.setPrefHeight(400.0);
        root.setPrefWidth(600.0);
        root.setStyle("-fx-background-color: navy;");
        createTitleLabel();
        createInstructionsLabel();
        createRangeLabel();
        createGuessButton();
        createNumberSlider();
        createNumberLabel();
        Guessbtn();
        End();
        root.getChildren().addAll(guessButton, numberSlider, titleLabel, instructionsLabel, numberLabel, rangeLabel,End,retry);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createTitleLabel() {
        titleLabel = new Label("Guess The Number Game");
        titleLabel.setLayoutX(148.0);
        titleLabel.setLayoutY(30.0);
        titleLabel.setFont(new Font(28.0));
        titleLabel.setTextFill(Color.web("#fffefe"));
    }

    private void createInstructionsLabel() {
        instructionsLabel = new Label("Move to Select Number");
        instructionsLabel.setLayoutX(206.0);
        instructionsLabel.setLayoutY(83.0);
        instructionsLabel.setTextFill(Color.web("#fffefe"));
        instructionsLabel.setFont(new Font(19.0));
    }

    private void createRangeLabel() {
        rangeLabel = new Label("The Number is Between 0 - 100");
        rangeLabel.setLayoutX(215.0);
        rangeLabel.setLayoutY(226.0);
        rangeLabel.setTextFill(Color.WHITE);
    }

    private void createGuessButton() {
        guessButton = new Button("Guess");
        guessButton.setLayoutX(284.0);
        guessButton.setLayoutY(262.0);
        guessButton.setMnemonicParsing(false);
    }

    private void createNumberSlider() {
        numberSlider = new Slider();
        numberSlider.setLayoutX(175.0);
        numberSlider.setLayoutY(192.0);
        numberSlider.setPrefHeight(15.0);
        numberSlider.setPrefWidth(268.0);
    }

    private void createNumberLabel() {
        numberLabel = new Label("0");
        numberLabel.setLayoutX(300.0);
        numberLabel.setLayoutY(148.0);
        numberLabel.setPrefHeight(28.0);
        numberLabel.setPrefWidth(280.0);
        numberLabel.setTextFill(Color.web("#fffefe"));
        numberLabel.setFont(new Font(22.0));
        numberSlider.valueProperty().addListener(cl
                -> {
            //to let it only 1 decimal point as requrid and added it to Label
            String result = String.format("%d", (int) Math.round(numberSlider.getValue()));
            numberLabel.setText(result);
        });
    }

    public void Guessbtn() {
        guessButton.setOnMouseClicked(eh -> {
            game();
        });
    }

    public void End() {
        End = new Button("  End  ");
        End.setLayoutX(284.0);
        End.setLayoutY(302.0);
        End.setMnemonicParsing(false);
        retry = new Button(" Retry ");
        retry.setLayoutX(284.0);
        retry.setLayoutY(342.0);
        retry.setMnemonicParsing(false);
        
        End.setOnMouseClicked(eh-> {
            Platform.exit();
        });
        
        retry.setOnMouseClicked(eh -> {
            numGuesses = 0;
            numberToGuess = random.nextInt(100) + 1;
             rangeLabel.setText("New Game Has Started");
        });
        

    }

    public void game() {
        System.out.println(numberToGuess);
        // Initialize variables
        int guess = Integer.parseInt(numberLabel.getText());
        boolean correctGuess = false;
        // Prompt user to guess until they guess the correct number
        if (guess == numberToGuess) {
            correctGuess = true;
            rangeLabel.setText("Congratulations! You guessed the number in " + numGuesses + " guesses.");
            End();

        } else if (guess < numberToGuess) {

            rangeLabel.setText("Too low. Guess again.\n The Number Above " + guess);
            numGuesses++;

        } else {
            rangeLabel.setText("Too high. Guess again. \n The Number below " + guess);
            numGuesses++;

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
