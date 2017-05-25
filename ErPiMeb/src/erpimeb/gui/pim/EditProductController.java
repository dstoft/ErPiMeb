/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.pim;

import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class EditProductController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    private Product pickedProduct;
    private List<String> images = new ArrayList<>();
    private List<String> videos = new ArrayList<>();
    private ArrayList<Product> related = new ArrayList<>();
    private HashMap<String, String> specs = new HashMap<>();
    private CommodityManagerFacade cManager = CommodityManager.getInstance();
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ListView<String> imageListView;
    @FXML
    private ListView<String> videoListView;
    @FXML
    private TextField imageUrlTextField;
    @FXML
    private TextField videoLinkTextField;
    @FXML
    private ComboBox<Product> allProductsComboBox;
    @FXML
    private ComboBox<Product> relatedProductsComboBox;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField specValueTextField;
    @FXML
    private ComboBox<String> keysComboBox;
    @FXML
    private ListView<String> specListView;
    @FXML
    private Label statusLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommodityManagerFacade comManager = CommodityManager.getInstance();
        
        keysComboBox.getItems().addAll(comManager.getAllSpecKeys()); // Skal hente keys fra database
    }    

    void setReferences(Stage stageRef,Scene preSceneRef) {
        this.stageRef = stageRef;
        this.preSceneRef = preSceneRef;
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        this.stageRef.setScene(this.preSceneRef);
        this.stageRef.setTitle("PIM Backend");
        this.stageRef.show();
    }

    @FXML
    private void handleAttachImage(ActionEvent event) {
        images.add(imageUrlTextField.getText());
        imageListView.getItems().add(imageUrlTextField.getText());
    }

    @FXML
    private void handleAttachVideo(ActionEvent event) {
        videos.add(videoLinkTextField.getText());
        videoListView.getItems().add(videoLinkTextField.getText());
    }

    @FXML
    private void handleSaveChanges(ActionEvent event) {
        this.pickedProduct.setName(this.nameTextField.getText());
        this.pickedProduct.setDescription(this.descriptionTextArea.getText());
        
        double price;
        try {
            price = Double.parseDouble(this.priceTextField.getText());
        } catch(NumberFormatException e) {
            System.out.println("Number format exception regarding price!");
            price = this.pickedProduct.getPrice();
        }
        this.pickedProduct.setPrice(price);
        this.pickedProduct.setSpecification(this.specs);
        this.pickedProduct.setImages(this.images);
        this.pickedProduct.setVideoLinks(this.videos);
        //this.pickedProduct.setCategory(category); No dropdown implemented yet!
        this.pickedProduct.setRelatedProducts(this.related);
        
        
        this.cManager.saveChangesToProduct();
        
        // Go back to PIM
            this.stageRef.setScene(this.preSceneRef);
            this.stageRef.setTitle("PIM Backend");
            this.stageRef.show();
    }

    @FXML
    private void handleAttachRelatedProduct(ActionEvent event) {
    }

    @FXML
    private void handleRemoveRelatedProduct(ActionEvent event) {
    }

    @FXML
    private void handleAddSpecifications(ActionEvent event) {
        if (specs.get(keysComboBox.getSelectionModel().getSelectedItem()) == null && keysComboBox.getSelectionModel().getSelectedItem() != null) {
            specs.put(keysComboBox.getSelectionModel().getSelectedItem(), specValueTextField.getText());
            specListView.getItems().add(keysComboBox.getSelectionModel().getSelectedItem() + " = " + specValueTextField.getText());
        }
    }

    @FXML
    private void handleSpecClick(MouseEvent event) {
        String[] parts = splitSpecification(specListView.getSelectionModel().getSelectedItem());
        specs.remove(parts[0]);
        specListView.getItems().remove(specListView.getSelectionModel().getSelectedItem());
    }
    
    public void setPickedProduct(Product pickedProduct) {
        this.pickedProduct = pickedProduct;
        // Set pre-existing values
        nameTextField.setText(pickedProduct.getName());
        priceTextField.setText(Double.toString(pickedProduct.getPrice()));
        descriptionTextArea.setText(pickedProduct.getDescription());
        if (pickedProduct.getImages() != null) {
            for (String s : pickedProduct.getImages()) {
                images.add(s);
                imageListView.getItems().add(s);
            }
        }
        if (pickedProduct.getVideoLinks() != null) {
            for (String s : pickedProduct.getVideoLinks()) {
                videos.add(s);
                videoListView.getItems().add(s);
            }
        }
        if(pickedProduct.getSpecification() != null) {
            for(String specKey : pickedProduct.getSpecification().keySet()) {
                this.specs.put(specKey, pickedProduct.getSpecification().get(specKey));
                specListView.getItems().add(specKey + " = " + pickedProduct.getSpecification().get(specKey));
            }
        }
    }
    
    private String[] splitSpecification(String string) {
        String[] parts = string.split(" = ");
        return parts;
    }
    
}
