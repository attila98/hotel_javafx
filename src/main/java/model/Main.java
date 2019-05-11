package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A kezdő állapot létrehezó osztály.
 */
public class Main extends Application {

    /**
     * A logger létrehozása.
     */
    private static final Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Szoba foglalás");
        primaryStage.show();
        logger.info("A kezdo kepernyo megjelent.");
    }

    /**
     * Elindítja a JavaFx alkalmazást.
     * @param args parancssori argumentumok listája
     */
    public static void main(String[] args)
    {
        logger.info("A program elindult.");
        launch(args);
    }
}
