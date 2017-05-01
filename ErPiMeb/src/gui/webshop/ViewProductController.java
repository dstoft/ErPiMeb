/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.webshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class ViewProductController implements Initializable {
    private int productId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Flow here
        ** Query the webshop database for a products information.
        ** The query will select the information for this.productId.
        */
    }
    
    void setProductId(int productId){
        this.productId = productId;
    }
    
}
