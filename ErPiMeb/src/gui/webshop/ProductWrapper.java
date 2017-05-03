/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author chris
 */
class ProductWrapper {
    private int id;
    private String name;
    private List<BufferedImage> image;
    private String description;
    private double price;
    
    public ProductWrapper(int id){
        this.id = id;
        /* Database flow description
        ** The constructor will query the database for the remaining
        ** attributes values based on the provided id
        ** The following values are for test purposes
        */
        this.name = "Test produkt";
        this.image = new ArrayList<>();
        try {
            this.image.add(ImageIO.read(new File("src/test.jpg")));
            this.image.add(ImageIO.read(new File("src/test2.jpg")));
            this.image.add(ImageIO.read(new File("src/test3.png")));
            this.image.add(ImageIO.read(new File("src/test4.jpg")));
            this.image.add(ImageIO.read(new File("src/test5.png")));
        } catch (IOException ex) {
            Logger.getLogger(ProductWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.description = "Dette er en test beskrivelse af et produkt, som samtidig tester hvordan et listview viser denne data.";
        this.price = 2500.50;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public BufferedImage getImage(int imageNum) {
        return this.image.get(imageNum);
    }
    
    public int getImageCount(){
        return this.image.size();
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }
    
    @Override
    public String toString(){
        return this.id + " | " + this.name + " | " + this.price + "\n" + this.description;
    }
    
}
