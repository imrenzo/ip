package main;

import java.io.IOException;

import boss.Boss;
import controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class for Launcher to call to run program
 */
public class Main extends Application {
    private final Boss boss = new Boss("data/boss.txt");

    /** {@inheritDoc} */
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBoss(boss); // inject the Boss instance
            fxmlLoader.<MainWindow>getController().addGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
