/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ProductWrapper product;
    @FXML
    private ImageView imageView;
    @FXML
    private Label productName;
    @FXML
    private Label productPrice;
    @FXML
    private Label productDescription;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    void setProduct(ProductWrapper product){
        this.product = product;
    }
    
    void setValues(){
        this.imageView.setImage(SwingFXUtils.toFXImage(this.product.getImage(0), null));
        this.productName.setText(this.product.getName());
        this.productPrice.setText(String.valueOf(this.product.getPrice()));
        this.productDescription.setText(this.product.getDescription());
    }

    void setStageRef(Stage stageRef) {
        this.stageRef = stageRef;
    }
    
}
