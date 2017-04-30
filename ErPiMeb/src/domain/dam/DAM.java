/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.dam;

import java.awt.Image;
import java.util.*;

/**
 *
 * @author AKT
 */
public class DAM {
    
    private List<Image> images;
    
    public DAM () {
        images = new ArrayList<>();
    }
    public List<Image> searchForImages(String searchTerm) {
        // For each Image in the database correlating with the searchTerm,
        // Add to list images.
        return images;
    }
    
    public boolean uploadImage(Image image) {
        throw new UnsupportedOperationException("Method not yet supported.");
    }
}
