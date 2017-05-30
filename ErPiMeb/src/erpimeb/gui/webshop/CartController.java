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
    private Button goToCart;
    
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
        // Test product objects
//        Product testProduct = new Product(1);
//        testProduct.addImage("/images/test.jpg");
//        testProduct.setName("Test Produkt");
//        testProduct.setPrice(500.50);
//        this.umf.addProductToCart(testProduct);
//        
//        Product testProduct2 = new Product(2);
//        testProduct2.addImage("/images/test2.jpg");
//        testProduct2.setName("Test Produkt2056");
//        testProduct2.setPrice(1000.10);
//        this.umf.addProductToCart(testProduct2);
//        
//        Product testProduct3 = new Product(3);
//        testProduct3.addImage("/images/test3.png");
//        testProduct3.setName("Test Produkt2056");
//        testProduct3.setPrice(1000.10);
//        this.umf.addProductToCart(testProduct3);
//        
//        Product testProduct4 = new Product(4);
//        testProduct4.addImage("/images/test4.jpg");
//        testProduct4.setName("Test Produkt2056");
//        testProduct4.setPrice(1000.10);
//        this.umf.addProductToCart(testProduct4);
//        
//        Product testProduct5 = new Product(5);
//        testProduct5.addImage("/images/test5.png");
//        testProduct5.setName("Test Produkt2056");
//        testProduct5.setPrice(1000.10);
//        this.umf.addProductToCart(testProduct5);
        
        int i = 0;
        for(Product product : this.umf.getCartProducts()){
            AnchorPane innerProductPane = new AnchorPane();
            innerProductPane.setPrefSize(392, 70);
            innerProductPane.setLayoutX(15);
            innerProductPane.setLayoutY(10);
            
            ImageView innerProductImage = new ImageView(product.getImages().get(0));
            innerProductImage.setPreserveRatio(true);
            innerProductImage.setFitHeight(60);
            innerProductImage.setFitWidth(60);
            
            Label innerProductLabelName = new Label(product.getName());
            innerProductLabelName.setLayoutX(65);
            innerProductLabelName.setLayoutY(20);
            
            Label innerProductLabelPrice = new Label(String.valueOf(product.getPrice()));
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
            valueFactory.setValue(1);
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
            
            innerProductPane.getChildren().add(innerProductImage);
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
            this.goToCart.setDisable(true);
        } else {
            this.goToCart.setDisable(false);
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
