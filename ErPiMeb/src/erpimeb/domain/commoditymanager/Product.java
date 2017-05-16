/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.domain.commoditymanager;

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
    private List<String> images;
    private List<String> videoLinks;
    private List<Product> relatedProducts;
    private double price;
    private HashMap<String, String> specification;
//    private Cart cart; ved ikke om denne skal være der da den er i en anden pakke
    
    public Product(String name, List<String> images, List<String> videoLinks, String description, HashMap<String, String> specifications, double price, List<Product> relatedProducts) {
        this.name = name;
        this.images = images;
        this.videoLinks = videoLinks;
        this.description = description;
        this.specification = specifications;
        this.price = price;
        this.relatedProducts = relatedProducts;
        
    }
    
    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getVideoLinks() {
        return videoLinks;
    }

    public void setVideoLinks(List<String> videoLinks) {
        this.videoLinks = videoLinks;
    }

    public List<Product> getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(List<Product> relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HashMap<String, String> getSpecification() {
        return specification;
    }

    public void setSpecification(HashMap<String, String> specification) {
        this.specification = specification;
    }
}
