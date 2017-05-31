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
import erpimeb.gui.SceneSwitcher;
import erpimeb.gui.Switchable;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Agger
 */
public class CartController implements Initializable, Switchable {
    private UserManagerFacade umf;
    private CommodityManagerFacade cmf;
    
    @FXML
    private Label totalPrice;
    @FXML
    private AnchorPane productPane;
    @FXML
    private GridPane productsGrid;
    @FXML
    private Button goToCheckout;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.umf = UserManager.getInstance();
        this.cmf = CommodityManager.getInstance();
    }

    @Override
    public void setupInternals() {
        
        int i = 0;
        HashMap<Product,Integer> products = new HashMap<>();
        for(Product product : this.umf.getCartProducts()){
            if(products.containsKey(product)){
                products.put(product,products.get(product) + 1);
                continue;
            }
            products.put(product, 1);
        }
        for(Product product : products.keySet()){
            AnchorPane innerProductPane = new AnchorPane();
            innerProductPane.setPrefSize(392, 70);
            innerProductPane.setLayoutX(15);
            innerProductPane.setLayoutY(10);
            
            if(!product.getImages().isEmpty() && !product.getImages().get(0).isEmpty()) {
                try {
                    ImageView innerProductImage = new ImageView(product.getImages().get(0));
                    innerProductImage.setPreserveRatio(true);
                    innerProductImage.setFitHeight(60);
                    innerProductImage.setFitWidth(60);
                    innerProductPane.getChildren().add(innerProductImage);
                } catch(IllegalArgumentException e) {

                }
            }
            
            Label innerProductLabelName = new Label(product.getName());
            innerProductLabelName.setLayoutX(65);
            innerProductLabelName.setLayoutY(20);
            innerProductLabelName.setMaxWidth(155);
            innerProductLabelName.setWrapText(false);
            
            DecimalFormat df = new DecimalFormat("#.##");
            Label innerProductLabelPrice = new Label(df.format(product.getPrice()));
            innerProductLabelPrice.setLayoutX(290);
            innerProductLabelPrice.setLayoutY(25);
            
            Spinner innerProductSpinner = new Spinner(1,100,1);
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory<Integer>(){
                @Override
                public void decrement(int steps) {
                    int current = this.getValue();
                    if(current == 1){
                        return;
                    }
                    if(current != 1 || current >= 0){
                        umf.removeOneProductFromCart(product);
                    }
                    this.setValue(current - steps);
                }

                @Override
                public void increment(int steps) {
                    int current = this.getValue();
                    if(current == 100){
                        return;
                    }
                    if(current != 100){
                        umf.addProductToCart(product);
                    }
                    this.setValue(current + steps);
                }
                
            };
            valueFactory.setValue(products.get(product));
            innerProductSpinner.setValueFactory(valueFactory);
            innerProductSpinner.valueProperty().addListener(new ChangeListener<Integer>(){
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    updateTotal();
                }
                
            });
            innerProductSpinner.setPrefWidth(60);
            innerProductSpinner.setLayoutX(220);
            innerProductSpinner.setLayoutY(20);
            
            Button innerProductButton = new Button("Fjern");
            innerProductButton.setLayoutX(350);
            innerProductButton.setLayoutY(20);
            innerProductButton.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    umf.removeProductFromCart(product);
                    innerProductPane.setDisable(true);
                    updateTotal();
                }
            });
            
            innerProductPane.getChildren().add(innerProductLabelName);
            innerProductPane.getChildren().add(innerProductSpinner);
            innerProductPane.getChildren().add(innerProductLabelPrice);
            innerProductPane.getChildren().add(innerProductButton);
            
            this.productsGrid.addRow(i,innerProductPane);
            i++;
        }
        updateTotal();
    }
    
    private void updateTotal(){
        this.totalPrice.setText(String.format("%-6.2f", this.umf.getTotalCartPrice()));
        if(this.umf.getCartProducts().isEmpty()){
            this.goToCheckout.setDisable(true);
        } else {
            this.goToCheckout.setDisable(false);
        }
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @FXML
    private void beginCheckout(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopCheckout.fxml", "Kassen");
    }

    @FXML
    private void handleReturnToMain(ActionEvent event) {
        SceneSwitcher.changeScene("/resources/WebshopMain.fxml", "Webshop");
    }
}
