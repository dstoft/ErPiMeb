/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.usermanager.UserManager;
import erpimeb.domain.usermanager.UserManagerFacade;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import erpimeb.gui.Switchable;
import erpimeb.gui.SceneSwitcher;
import java.text.DecimalFormat;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class ViewProductController implements Initializable, Switchable {
    private CommodityManagerFacade cManager;
    private UserManagerFacade umf;
    private Product currentProduct;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    private int imageCounter = 0;
    private int videoCounter = 0;
    private List<Image> imageList;
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
    @FXML
    private GridPane relatedProductsGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cManager = CommodityManager.getInstance();
        this.imageList = new ArrayList<>();
        this.umf = UserManager.getInstance();
        this.currentProduct = cManager.getCurrentProduct();
    }
    
    /**
     * Sets the values of this objects fxml elements Also determines whether to
     * show the imagery cycle buttons
     */
    void setupScene() {
        videoWebView.setDisable(true);
        videoWebView.setVisible(false);
        
        productName.setText(currentProduct.getName());
        productPrice.setText(this.df.format(currentProduct.getPrice()) + ",-");
        productDescription.setText(currentProduct.getDescription());

        //Add images
        List<String> images = this.currentProduct.getImages();

        // For loop cannot be implemented until images are
        for (String s : images) {
            if(s.isEmpty()) {
                continue;
            }
            try {
                imageList.add(new Image(s));
            } catch(IllegalArgumentException e) {
                
            }
            
        }
        
        //Add videos
        
        setImagesAndVideos();
    }
    
    void setImagesAndVideos() {
        if(this.imageList.isEmpty()) {
            return;
        }
        //Set the image
        imageView.setImage(imageList.get(imageCounter));
        
        //Set the video
        
    }
    
    @FXML
    private void cycleBackward(ActionEvent event) {
        if(imageList.size() == 1){
            return;
        }
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
        if(imageList.size() == 1){
            return;
        }
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
        
        if(!this.currentProduct.getRelatedProducts().isEmpty()){
            int i = 1;
            for(Product product : this.currentProduct.getRelatedProducts()){
                this.cManager.fillProduct(product);
                AnchorPane innerProductPane = new AnchorPane();
                innerProductPane.setPrefSize(100, 132);
                innerProductPane.setLayoutX(10);
                innerProductPane.setLayoutY(10);
                innerProductPane.setOnMouseClicked(new EventHandler(){ 
                    @Override
                    public void handle(Event event) {
                        cManager.setCurrentProduct(product);
                        SceneSwitcher.changeScene("/resources/WebshopViewProduct.fxml", product.getName());
                    }
                });

                if(!product.getImages().isEmpty() && !product.getImages().get(0).isEmpty()) {
                    try {
                        ImageView innerProductImage = new ImageView(product.getImages().get(0));
                        innerProductImage.setPreserveRatio(true);
                        innerProductImage.setLayoutX(10);
                        innerProductImage.setLayoutY(10);
                        innerProductImage.setFitHeight(100);
                        innerProductImage.setFitWidth(100);
                        innerProductPane.getChildren().add(innerProductImage);
                    } catch(IllegalArgumentException e) {
                        
                    }
                }

                Label innerProductLabelName = new Label(product.getName());
                innerProductLabelName.setLayoutX(10);
                innerProductLabelName.setLayoutY(110);
                innerProductLabelName.setWrapText(false);
                innerProductLabelName.setMaxWidth(100);

                Label innerProductLabelPrice = new Label(this.df.format(product.getPrice()) + ",-");
                innerProductLabelPrice.setLayoutX(15);
                innerProductLabelPrice.setLayoutY(125);
                
                innerProductPane.getChildren().add(innerProductLabelName);
                innerProductPane.getChildren().add(innerProductLabelPrice);
                
                this.relatedProductsGrid.addColumn(i, innerProductPane);
                i++;
            }
        }
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

    @FXML
    private void addToCart(ActionEvent event) {
        this.umf.addProductToCart(this.currentProduct);
        SceneSwitcher.changeScene("/resources/WebshopCart.fxml", "Kurv");
    }

}
