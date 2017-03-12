/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.binding.IntegerBinding;
/**
 *
 * @author AaronKunzer
 */
public class Map {
    
    public static enum MapAction {  TEST,
                                    NOTHING,
                                    PUSH, 
                                    CUT, 
                                    MOVE_THROUGH_GRASS,
                                    SURF_THROUGH_WATER  };
    
    public static enum Direction {  LEFT,
                                    RIGHT,
                                    DOWN,
                                    UP };
    
    private int width;
    private int height;
    
    // Position is upper-left hand corner of the player's current region
    private SimpleIntegerProperty currentPositionX; 
    private SimpleIntegerProperty currentPositionY;
   
    private IntegerBinding playerPositionX;
    private IntegerBinding playerPositionY;

    private short[][] map;
    
    public Map(short[][] startingMap, short posX, short posY)
    {
        map = startingMap;
        height = map.length;
        width = map[0].length;
        currentPositionX = new SimpleIntegerProperty(posX);
        currentPositionY = new SimpleIntegerProperty(posY);
        
        if(GameValues.REGION_WIDTH % 2 == 1)
        {
            playerPositionX = currentPositionX.add((GameValues.REGION_WIDTH / 2) + 1);
        }
        else
        {
            playerPositionX = currentPositionX.add((GameValues.REGION_WIDTH / 2));
        }
        
        if(GameValues.REGION_HEIGHT % 2 == 1)
        {
            playerPositionY = currentPositionY.add((GameValues.REGION_HEIGHT / 2) + 1);
        }
        else
        {
            playerPositionX = currentPositionY.add((GameValues.REGION_HEIGHT / 2));
        }
    }
    
    /**
     * Creates a map. Can be easily manipulated using a MapHelper
     * @param startingMap 
     */
    public Map(short[][] startingMap)
    {
        this(startingMap, (short)0, (short)0);
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
    
    public short[][] getCurrentRegion()
    {
        short[][] region = new short[GameValues.REGION_HEIGHT][GameValues.REGION_WIDTH];
        for (int i = currentPositionY.intValue(); i < currentPositionY.intValue() + GameValues.REGION_HEIGHT; i++)
        {
            for (int j = currentPositionX.intValue(); j < currentPositionX.intValue() + GameValues.REGION_WIDTH; j++)
            {
                region[i - currentPositionY.intValue()][j - currentPositionX.intValue()] = map[i][j];
            }
            //System.arraycopy(map, currentPositionX.intValue(), region[i], 0, GameValues.REGION_WIDTH);
        }
        
        return region;
    }
    
    public short[][] getCurrentMovementRegion(Direction direction)
    {
        short[][] region;
        if (direction == Direction.LEFT || direction == Direction.RIGHT)
        {
            region = new short[GameValues.REGION_HEIGHT][GameValues.REGION_WIDTH + 1];
            if (direction == Direction.LEFT)
            {
                for (int i = currentPositionY.intValue(); i < currentPositionY.intValue() + GameValues.REGION_HEIGHT; i++)
                {
                    System.arraycopy(map, currentPositionX.intValue() - 1, region[i], 0, GameValues.REGION_WIDTH + 1);
                }
            }
            else
            {
                for (int i = currentPositionY.intValue(); i < currentPositionY.intValue() + GameValues.REGION_HEIGHT; i++)
                {
                    System.arraycopy(map, currentPositionX.intValue(), region[i], 0, GameValues.REGION_WIDTH + 1);
                }                
            }
        }
        else
        {
            region = new short[GameValues.REGION_HEIGHT + 1][GameValues.REGION_WIDTH];
            if (direction == Direction.UP)
            {
                for (int i = currentPositionY.intValue() - 1; i < currentPositionY.intValue() + GameValues.REGION_HEIGHT + 1; i++)
                {
                    System.arraycopy(map, currentPositionX.intValue(), region[i], 0, GameValues.REGION_WIDTH);
                }
            }
            else
            {
                for (int i = currentPositionY.intValue(); i < currentPositionY.intValue() + GameValues.REGION_HEIGHT + 1; i++)
                {
                    System.arraycopy(map, currentPositionX.intValue(), region[i], 0, GameValues.REGION_WIDTH);
                }                
            }
        }
        
        return region;
    }
    
    public int getCurrentPlayerPositionX()
    {
        return playerPositionX.intValue();
    }
    
    public int getCurrentPlayerPositionY()
    {
        return playerPositionY.intValue();
    }
    
}
