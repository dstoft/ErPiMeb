/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class ViewProductController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    private String preSceneTitle;
    private ProductWrapper product;
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
        
    }
    
    void setProduct(ProductWrapper product){
        this.product = product;
    }
    
    /**
     * Sets the values of this objects fxml elements
     * Also determines whether to show the imagery cycle buttons
     */
    void setupScene(){
        this.imageView.setImage(SwingFXUtils.toFXImage(this.product.getImage(0), null));
        if(this.product.getImageCount() == 1){
            this.leftArrow.setVisible(false);
            this.rightArrow.setVisible(false);
        } else {
            this.leftArrow.setVisible(true);
            this.rightArrow.setVisible(true);
        }
        this.productName.setText(this.product.getName());
        this.productPrice.setText(String.valueOf(this.product.getPrice()));
        this.productDescription.setText(this.product.getDescription());
    }

    void setReferences(Stage stageRef, Scene preSceneRef,String preSceneTitle) {
        this.stageRef = stageRef;
        this.preSceneRef = preSceneRef;
        this.preSceneTitle = preSceneTitle;
    }

    @FXML
    private void cycleBackward(ActionEvent event) {
        if(this.imageCounter == 0){
            this.imageCounter = this.product.getImageCount() - 1;
        } else {
            this.imageCounter--;
        }
        this.imageView.setImage(SwingFXUtils.toFXImage(this.product.getImage(this.imageCounter), null));
    }

    @FXML
    private void cycleForward(ActionEvent event) {
        if(this.imageCounter == this.product.getImageCount() - 1){
            this.imageCounter = 0;
        } else {
            this.imageCounter++;
        }
        this.imageView.setImage(SwingFXUtils.toFXImage(this.product.getImage(this.imageCounter), null));
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle(this.preSceneTitle);
        this.stageRef.show();
    }
    
}
