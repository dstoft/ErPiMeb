/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.pim;

import java.awt.Image;
import java.util.HashMap;
import java.util.*;

/**
 *
 * @author AKT
 */
public class Product {
    
    private int id;
    private String name;
    private String description;
    private List<Image> images;
    private List<String> videoLinks;
    private List<Integer> relatedProducts;
    private List<String> tags;
    private double price;
    private HashMap<String, String> specification;
    
    public Product() {
        // Initialize lists
        tags = new ArrayList<>();
        images = new ArrayList<>();
        videoLinks = new ArrayList<>();
        relatedProducts = new ArrayList<>();
    }
    
    public void editDescription(String newText) {
        description = newText;
    }
    
    public void pickAndAttachImage(Image newImage) {
        images.add(newImage);
    }
    
    public void addTag(String tag) {
        tags.add(tag);
    }
    
    public void addVideoLink(String videoLink) {
        videoLinks.add(videoLink);
    }
    
    public void addSpecification(String name, String description) {
        specification.put(name, description);
    }
    
    public void addRelatedProduct(int productId) {
        relatedProducts.add(productId);
    }
}
