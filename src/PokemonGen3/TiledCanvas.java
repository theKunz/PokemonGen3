/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;

import javafx.scene.layout.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author AaronKunzer
 */
public class TiledCanvas extends Canvas {
     
    int gridwidth;
    int gridheight;
    int[][] grid;
    int currentX;
    int currentY;
    
    //test if Canvas with specefied tiles or if a grid of panes is faster.
    public TiledCanvas(int width, int height) 
    {
        super(width, height);
        gridwidth = width;
        gridheight = height;
        grid = new int[gridwidth][gridheight];
        currentX = 0;
        currentY = 0; 
    }
    
    public TiledCanvas(int width, int height, String filename)
    {
        this(width, height);
        // TODO: create a filetype to read and load it here
    }
    
    public TiledCanvas(int width, int height, int startingX, int startingY)
    {
        this(width, height);
        currentX = startingX;
        currentY = startingY;
    }
    
    /**
     * TODO: remove when done testing
     */
    public void setTestTiles()
    {
        TileRetriever tileRetriever = new TileRetriever();
        GraphicsContext gc = getGraphicsContext2D();
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                Image tile = tileRetriever.GetImage(Integer.toString((j + 1) + (i * 11)));
                gc.drawImage(tile, j*30, i*30, 30, 30);
            }
        }
    }
    
    public void loadMap()
    {
        //TODO: implement
    }
    
    public void setPosition(int xPos, int yPos)
    {
        currentX = xPos;
        currentY = yPos;
    }
    
    public void moveLeft()
    {
        
    }
    
    public void moveRight()
    {
        
    }
    
    public void moveDown()
    {
        
    }
    
    public void moveUp()
    {
        
    }
    
}
