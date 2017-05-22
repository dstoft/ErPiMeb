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
import java.util.ResourceBundle;
import erpimeb.gui.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class ViewProductController implements Initializable, Switchable {
    private CommodityManagerFacade cmf;
    private Product product;
    private int imageCounter = 0;
    @FXML
    private ImageView imageView;
    @FXML
    private Label productName;
    @FXML
    private Label productPrice;
    @FXML
    private Label productDescription;
    @FXML
    private Button leftArrow;
    @FXML
    private Button rightArrow;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmf = CommodityManager.getInstance();
        this.product = cmf.getProduct();
    }
    
    /**
     * Sets the values of this objects fxml elements
     * Also determines whether to show the imagery cycle buttons
     */
    void setupScene(){
//        this.imageView.setImage(SwingFXUtils.toFXImage(this.product.getImage(0), null));
//        if(this.product.getImageCount() == 1){
//            this.leftArrow.setVisible(false);
//            this.rightArrow.setVisible(false);
//        } else {
//            this.leftArrow.setVisible(true);
//            this.rightArrow.setVisible(true);
//        }
//        this.productName.setText(this.product.getName());
//        this.productPrice.setText(String.valueOf(this.product.getPrice()));
//        this.productDescription.setText(this.product.getDescription());
    }

    @FXML
    private void cycleBackward(ActionEvent event) {
//        if(this.imageCounter == 0){
//            this.imageCounter = this.product.getImageCount() - 1;
//        } else {
//            this.imageCounter--;
//        }
//        this.imageView.setImage(SwingFXUtils.toFXImage(this.product.getImage(this.imageCounter), null));
    }

    @FXML
    private void cycleForward(ActionEvent event) {
//        if(this.imageCounter == this.product.getImageCount() - 1){
//            this.imageCounter = 0;
//        } else {
//            this.imageCounter++;
//        }
//        this.imageView.setImage(SwingFXUtils.toFXImage(this.product.getImage(this.imageCounter), null));
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @Override
    public void setupInternals() {
        this.setupScene();
    }
    
}
