/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;
import javafx.beans.property.IntegerProperty;
/**
 *
 * @author AaronKunzer
 */
public class Map {
    
    private int width;
    private int height;
    private IntegerProperty currentPositionX;
    private IntegerProperty currentPositionY;
    private int[][] map;
    /**
     * Creates a map. Can be easily manipulated using a MapHelper
     * @param startingMap 
     */
    public Map(int[][] startingMap)
    {
        map = startingMap;
        height = map.length;
        width = map[0].length;
    }
    
    public void moveLeft()
    {
        currentPositionX.subtract(1);
    }
    
    public void moveRight()
    {
        currentPositionX.add(1);
    }
    
    public void moveUp()
    {
        currentPositionY.subtract(1);
    }
    
    public void moveDown()
    {
        currentPositionY.add(1);
    }
    
}
