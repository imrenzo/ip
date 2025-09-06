package controller;

import java.util.Objects;

import boss.Boss;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Boss boss = new Boss("data/boss.txt");

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/aether.jpg"));
    private Image bossImage = new Image(this.getClass().getResourceAsStream("/images/ineffa.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Boss instance */
    public void setBoss(Boss b) {
        boss = b;
    }

    /**
     * Display greeting message when bot starts
     * */
    public void addGreeting() {
        dialogContainer.getChildren().add(
                DialogBox.getBossDialog(Ui.showWelcome(), bossImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Boss's reply and then appends them to
     * the dialog container. Clears the user input after processing. Exits program if user enters "bye".
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String bossText = boss.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBossDialog(bossText, bossImage)
        );
        userInput.clear();
        if (Objects.equals(bossText, Ui.getExitMessage())) {
            Platform.exit();
        }
    }
}

