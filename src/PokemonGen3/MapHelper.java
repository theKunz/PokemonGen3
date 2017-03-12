/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;

import java.io.File;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 *
 * @author AaronKunzer
 */
public final class MapHelper {
    
    private short mapWidth;
    private short mapHeight;
    @SuppressWarnings("FieldMayBeFinal")
    private Map map;
    
    public MapHelper(String fileName)
    {
        loadNewMap(fileName);
    }
    
    public void loadNewMap(String filename)
    {
        map = new Map(getMapByFilename(filename));
    }
    
    /**
     * Returns map grid from given filename
     * @param filename
     * @return 
     */
    public short[][] getMapByFilename(String filename)
    {
        short[][] ret = null;
        
        try
        {
            DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(filename))));
            
            try
            {
                mapHeight = input.readShort();
                mapWidth = input.readShort();
                
                ret = new short[mapHeight][mapWidth];
                for (int i = 0; i < mapHeight; i++)
                {
                    for (int j = 0; j < mapWidth; j++)
                    {
                        ret[i][j] = input.readShort();
                    }
                }
                input.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        
        if (ret == null)
        {
            throw new NullPointerException(".map file " + filename + " not read");
        }
        
        return ret;
    }
    
    public Image[][] getCurrentRegion()
    {
        short[][] region = map.getCurrentRegion();
        Image[][] ret = new Image[region.length][region[0].length];
        
        for(int i = 0; i < region.length; i++)
        {
            for(int j = 0; j < region[0].length; j++)
            {
                ret[i][j] = TileRetriever.GetImage(region[i][j]);
            }
        }
        
        return ret;
    }
    
    public Image[][] getCurrentMovementRegion(Map.Direction direction)
    {
        short[][] region = map.getCurrentMovementRegion(direction);
        Image[][] ret = new Image[region.length][region[0].length];
        
        for(int i = 0; i < region.length; i++)
        {
            for(int j = 0; j < region[0].length; j++)
            {
                ret[i][j] = TileRetriever.GetImage(region[i][j]);
            }
        }
        
        return ret;       
    }
    
    /**
     * 
     * @param map
     * @param x
     * @param y 
     * @param action
     */
    public void modifyMap(short[][] map, int x, int y, Map.MapAction action)
    {
        if (action == null)
        {
            throw new NullPointerException("Action cannot be null");
        }
        
        switch (action)
        {
            case NOTHING:
                break;
            case TEST:
                break;
            case CUT:
                break;
            case PUSH:
                break;
            case MOVE_THROUGH_GRASS:
                break;
            case SURF_THROUGH_WATER:
                break;
        }
    }
    
}
