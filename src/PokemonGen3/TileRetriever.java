/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PokemonGen3;

import javafx.scene.image.Image;
/**
 *
 * @author AaronKunzer
 */

public class TileRetriever {
    
    public Image GetImage(String imageName)
    {
        //return new Image("Resources/01.png");
        return new Image(("Resources/" + String.format("%02d", Integer.parseInt(imageName)) + ".png"));
        //return new Image(getClass().getResourceAsStream("Resources/" + String.format("%02d", Integer.parseInt(imageName)) + ".png"));
        //System.out.println((getClass().getResourceAsStream("Resources/" + String.format("%02d", Integer.parseInt(imageName)) + ".png")));  
        //return new Image(String.format("%02d", Integer.getInteger(imageName)) + ".png");
        //return null;
    }
    
}
