/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;
import java.io.IOException;
import javafx.application.Application;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hazemchtioui
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
          
        try {
            Parent root=FXMLLoader.load(getClass().getResource("AfficherEventClient.fxml"));
                    
                    Scene scene = new Scene(root);
                    
                    primaryStage.setTitle("Gestion evenement");
                    primaryStage.setScene(scene);
                    primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
