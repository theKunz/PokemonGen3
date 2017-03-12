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
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    
    public void moveLeft()
    {
        map.moveLeft();
    }
    
    public void moveRight()
    {
        map.moveRight();
    }
    
    public void moveUp()
    {
        map.moveUp();
    }
    
    public void moveDown()
    {
        map.moveDown();
    }
    
    public void setMovementListeners(TiledCanvas canvas)
    {
        map.getIntegerPropertyX().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal)
            {
                Map.Direction dir;
                if (((Integer)newVal) < ((Integer)oldVal))
                {
                    resetOffsetCounter(1);
                    dir = Map.Direction.LEFT;
                }
                else
                {
                    resetOffsetCounter(-1);
                    dir = Map.Direction.RIGHT;
                }
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() 
                    {
                        int offset = getNextOffset();
                        if (offset == GameValues.TILE_WIDTH || offset == GameValues.TILE_WIDTH * -1)
                        {
                            canvas.DrawMapRegion(getCurrentRegion());
                            this.cancel();
                            timer.purge();
                        }
                        else
                        {
                            if (dir == Map.Direction.LEFT)
                            {
                               canvas.DrawMapRegionWithOffset(getCurrentMovementRegion(dir), false, offset, GameValues.TILE_WIDTH * -1, 0); 
                            }
                            else
                            {
                               canvas.DrawMapRegionWithOffset(getCurrentMovementRegion(dir), false, offset, 0, 0);
                            }
                            
                        }
                    }
                }, 0, 10);
            }
        });
        
        
        map.getIntegerPropertyY().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal)
            {
                Map.Direction dir;
                if (((Integer)newVal) < ((Integer)oldVal))
                {
                    resetOffsetCounter(1);
                    dir = Map.Direction.UP;
                }
                else
                {
                    resetOffsetCounter(-1);
                    dir = Map.Direction.DOWN;
                }
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() 
                    {
                        int offset = getNextOffset();
                        if (offset == GameValues.TILE_WIDTH || offset == GameValues.TILE_WIDTH * -1)
                        {
                            canvas.DrawMapRegion(getCurrentRegion());
                            this.cancel();
                            timer.purge();
                        }
                        else
                        {
                            if (dir == Map.Direction.DOWN)
                            {
                               canvas.DrawMapRegionWithOffset(getCurrentMovementRegion(dir), true, offset, 0, 0); 
                            }
                            else
                            {
                               canvas.DrawMapRegionWithOffset(getCurrentMovementRegion(dir), true, offset, 0, GameValues.TILE_WIDTH * -1);
                            }
                        }
                    }
                }, 0, 10);
            }        
        });
    }
    
    int offset;
    int offsetInterval;
    
    /**
     * Dirty hack to get around local variable must be final for inner classes rule.
     * 
     */
    public int getNextOffset()
    {
        offset += offsetInterval;
        return offset;
    }
    
    /**
     * Dirty hack to get around local variable must be final for inner classes rule.
     * @param interval offset interval
     */
    public void resetOffsetCounter(int interval)
    {
        offset = 0;
        offsetInterval = interval;
    }
}
