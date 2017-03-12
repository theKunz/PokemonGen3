/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;

import java.util.HashMap;
import javafx.scene.image.Image;
/**
 *
 * @author AaronKunzer
 */

public class TileRetriever {
    
    public static HashMap<Short, String> TileMap;
    
    public static void InitTileMap()
    {
        TileMap = new HashMap<>();
        for (short i = 1; i <= 10; i++)
        {
            TileMap.put(i, String.format("%02d", i));
        }
    }
    
    public static Image GetImage(short tileKey)
    {
        if(TileMap == null)
        {
            throw new NullPointerException("You must call InitTileMap before you can call this.");
        }
        return new Image("Resources/" + TileMap.get(tileKey) + ".png");
    }
    
}
