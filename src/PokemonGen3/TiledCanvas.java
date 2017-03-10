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
     
    private int gridwidth;
    private int gridheight;
    
    /**
     * Create a new Canvas with methods to create a gridded world.
     * @param width number of tiles wide
     * @param height number of tiles tall
     */
    public TiledCanvas(int width, int height) 
    {
        super(width, height);
        gridwidth = width;
        gridheight = height;
    }
    
    /**
     * TODO: remove when done testing
     */
    public void SetTestTiles()
    {
        TileRetriever tileRetriever = new TileRetriever();
        GraphicsContext gc = getGraphicsContext2D();
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                Image tile = tileRetriever.GetImage((j + 1) + (i * 11));
                gc.drawImage(tile, j*30 - 30, i*30 - 30, 30, 30);
            }
        }
        
        gc.clearRect(0, 0, GameValues.TILE_WIDTH, GameValues.TILE_WIDTH);
        gc.drawImage(tileRetriever.GetImage(70), 0, 0, 30, 30);
        
        gc.clearRect(45, 45, GameValues.TILE_WIDTH, GameValues.TILE_WIDTH);
    }
    
    /**
     * Draws the full grid of tiles
     * @param region 
     */
    public void DrawMapRegion(Image[][] region)
    {   
        DrawMapRegionWithOffset(region, true, 0);
    }
    
    /**
     * Draws the map region with the tiles offset. Useful for animating movement
     * by calling rapidly together with increasing offset until TILE_WIDTH is reached.
     * @param region
     * @param isVertical
     * @param offset 
     */
    public void DrawMapRegionWithOffset(Image[][] region, boolean isVertical, int offset)
    {
        GraphicsContext gc = this.getGraphicsContext2D();
        int neededHeight = gridheight;
        int neededWidth = gridwidth;
        
        if (offset < 0 || offset > GameValues.TILE_WIDTH)
        {
            throw new IndexOutOfBoundsException("offset must be between 0 and " + GameValues.TILE_WIDTH + ", inclusive.");
        }
        
        if (offset == 0)
        {
            if (region.length != neededHeight || region[0].length != neededWidth)
            {
                throw new IndexOutOfBoundsException("Region must be " + neededHeight + " rows and " + neededWidth + " columns.");
            }
        }
        else
        {
            if (isVertical)
            {
                neededHeight++;
            }
            else
            {
                neededWidth++;
            }
            
            if (region.length != neededHeight || region[0].length != neededWidth)
            {
                throw new IndexOutOfBoundsException("Region must be " + neededHeight + " rows and " + neededWidth + " columns.");
            }
        }
        
        for(int i = 0; i < region.length; i++)
        {
            for(int j = 0; j < region[0].length; j++)
            {
                if (isVertical)
                {
                    gc.drawImage(region[i][j], j*GameValues.TILE_WIDTH, (i*GameValues.TILE_WIDTH) + offset, GameValues.TILE_WIDTH, GameValues.TILE_WIDTH);
                }
                else
                {
                    gc.drawImage(region[i][j], (j*GameValues.TILE_WIDTH) + offset, i*GameValues.TILE_WIDTH, GameValues.TILE_WIDTH, GameValues.TILE_WIDTH);
                }
            }
        }
    }
    
    /**
     * Draws a specific tile
     * @param x
     * @param y
     * @param tile 
     */
    public void drawTile(int x, int y, Image tile)
    {
        if (x < 0 || y < 0 || x >= GameValues.GAME_WIDTH / GameValues.TILE_WIDTH || y >= GameValues.GAME_HEIGHT / GameValues.TILE_WIDTH)
        {
            throw new IndexOutOfBoundsException("Selected Coordinate outside of region.");
        }
        
        if (tile == null)
        {
            throw new NullPointerException("Image cannot be null");
        }
        
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(x * GameValues.TILE_WIDTH, y * GameValues.TILE_WIDTH, GameValues.TILE_WIDTH, GameValues.TILE_WIDTH);
        gc.drawImage(tile, x * GameValues.TILE_WIDTH, y * GameValues.TILE_WIDTH, GameValues.TILE_WIDTH, GameValues.TILE_WIDTH);
    }
    
}
