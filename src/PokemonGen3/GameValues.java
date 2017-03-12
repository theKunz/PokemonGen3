/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;

/**
 *
 * @author AaronKunzer
 * 
 * Contains final static values that for reference everywhere.
 */
public class GameValues {
    
    // Pixel dimentions
    
    /**
     * Width of the visual region in pixels
     */
    public final static int GAME_WIDTH = 450; //in px
    
    /**
     * Height of the visual region in pixels
     */
    public final static int GAME_HEIGHT = 330; //in px
    
    /**
     * Width of a tile
     */
    public final static int TILE_WIDTH = 30; //in px //to be changed once actual tiles are used
    
    /**
     * Width of the visual region in tiles
     */
    public final static int REGION_WIDTH = GAME_WIDTH / TILE_WIDTH;
    
    /**
     * Height of the visual region in tiles
     */
    public final static int REGION_HEIGHT = GAME_HEIGHT / TILE_WIDTH;
    
    /**
     * Path to map files
     */
    public final static String MAP_PATH = "Resources\\Maps\\";    
    
}
