/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;

import java.io.File;

/**
 *
 * @author AaronKunzer
 */
public class MapHelper {
    
    public static enum MapAction {  TEST,
                                    NOTHING,
                                    PUSH, 
                                    CUT, 
                                    MOVE_THROUGH_GRASS,
                                    SURF_THROUGH_WATER  };
    
    private byte mapWidth;
    private byte mapHeight;
    private int[][] savedMap;
    private File mapFile;
    
    public MapHelper()
    {
        
    }
    
    public MapHelper(String fileName)
    {
        
    }
    
    /**
     * Returns map grid from given filename
     * @param filename
     * @return 
     */
    public int[][] getMapByFilename(String filename)
    {
       return null;
    }
    
    /**
     * Returned the map saved in memory
     * @return 
     */
    public int[][] getSavedMap()
    {
        return null; //replace
    }
    
    /**
     * 
     * @param map
     * @param x
     * @param y 
     * @param action
     */
    public void modifyMap(int[][] map, int x, int y, MapAction action)
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
