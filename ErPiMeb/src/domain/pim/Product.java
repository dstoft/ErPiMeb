/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.pim;

import java.awt.Image;
import java.util.HashMap;
import java.util.List;

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
    private List <Integer> relatedProducts;
    private double price;
    private HashMap<String, String> specification;
}
