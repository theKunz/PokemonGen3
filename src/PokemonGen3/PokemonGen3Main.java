/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author AaronKunzer
 */
public class PokemonGen3Main extends Application {
    
    TiledCanvas canvasRoot;
    MapHelper helper;
    Scene scene;
    
    @Override
    public void start(Stage primaryStage) {
        Initialize();
        
        canvasRoot = new TiledCanvas(GameValues.GAME_WIDTH, GameValues.GAME_HEIGHT);
        helper = new MapHelper(GameValues.MAP_PATH + "TestMap1.map");
        canvasRoot.DrawMapRegion(helper.getCurrentRegion());
        
        Group root = new Group();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(canvasRoot);
        root.getChildren().add(vbox);
        scene = new Scene(root, GameValues.GAME_WIDTH, GameValues.GAME_HEIGHT);
        
        setListeners();
        
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
     * Run any code needed to be run at the beginning.
     */
    private void Initialize()
    {
        TileRetriever.InitTileMap();
    }
    
    private void setListeners()
    {
        helper.setMovementListeners(canvasRoot);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case LEFT:
                        helper.moveLeft();
                        break;
                    case RIGHT:
                        helper.moveRight();
                        break;
                    case UP:
                        helper.moveUp();
                        break;
                    case DOWN:
                        helper.moveDown();
                        break;
                    default:
                        break;
                }
            }
        });
        
    }
}
