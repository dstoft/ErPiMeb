/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import erpimeb.gui.Switchable;
import erpimeb.gui.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class ViewProductController implements Initializable, Switchable {
    private CommodityManagerFacade cManager = CommodityManager.getInstance();
    private Product currentProduct = cManager.getCurrentProduct();
    
    private int imageCounter = 0;
    private int videoCounter = 0;
    private List<Image> imageList = new ArrayList<>();
    private boolean image = true;

    @FXML
    private ImageView imageView;
    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private TextArea productDescription;
    @FXML
    private Button leftArrow;
    @FXML
    private Button rightArrow;
    @FXML
    private WebView videoWebView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cManager = CommodityManager.getInstance();
        this.currentProduct = cManager.getProduct();
        this.setupScene();
    }
    
    /**
     * Sets the values of this objects fxml elements Also determines whether to
     * show the imagery cycle buttons
     */
    void setupScene() {
        productName.setText(currentProduct.getName());
        productPrice.setText(Double.toString(currentProduct.getPrice()));
        productDescription.setText(currentProduct.getDescription());

        //Add images
        List<String> images = cManager.getCurrentProductImages();

        for (String s : images) {
            imageList.add(new Image(s));
        }

        //Add videos
        

    }
    
    void setImagesAndVideos() {
        //Set the image
        imageView.setImage(imageList.get(imageCounter));
        
        //Set the video
        
    }
    
    @FXML
    private void cycleBackward(ActionEvent event) {
        if (image == true) {
            if (imageCounter != 0) {
                imageCounter--;
            }
        } else if (videoCounter != 0) {
            videoCounter--;
        }
        
        setImagesAndVideos();
    }

    @FXML
    private void cycleForward(ActionEvent event) {
        if (image == true) {
            if (imageCounter < imageList.size()) {
                imageCounter++;
            }
        } else if (videoCounter < imageList.size()) {
            videoCounter++;
        }
        
        setImagesAndVideos();
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @Override
    public void setupInternals() {
        this.setupScene();
    }

    @FXML
    private void handleImages(ActionEvent event) {
        image = true;
        imageView.setDisable(false);
        imageView.setVisible(true);
        videoWebView.setDisable(true);
        videoWebView.setVisible(false);
    }

    @FXML
    private void handleVideos(ActionEvent event) {
        image = false;
        imageView.setDisable(true);
        imageView.setVisible(false);
        videoWebView.setDisable(false);
        videoWebView.setVisible(true);
    }

}
