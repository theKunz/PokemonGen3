/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author AaronKunzer
 */
public class PokemonGen3Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Initialize();
        
        TiledCanvas canvasRoot = new TiledCanvas(GameValues.GAME_WIDTH, GameValues.GAME_HEIGHT);
        canvasRoot.SetTestTiles();
        
        
        
        Group root = new Group();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(canvasRoot);
        root.getChildren().add(vbox);
        Scene scene = new Scene(root, GameValues.GAME_WIDTH, GameValues.GAME_HEIGHT);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Pokemon v. Aaron");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene(); //because setting to non-risizable added unwanted padding
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Run any code needed to be run BEFORE creating the TiledCanvas object.
     */
    private void Initialize()
    {
        TileRetriever.InitTileMap();
    }
    
}
