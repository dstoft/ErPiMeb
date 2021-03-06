/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.pim;

import erpimeb.gui.SceneSwitcher;
import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
import erpimeb.domain.commoditymanager.Product;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import erpimeb.gui.Switchable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class CreateProductController implements Initializable, Switchable {
    private List<String> images = new ArrayList<>();
    private List<String> videos = new ArrayList<>();
    private ArrayList<Product> related = new ArrayList<>();
    private HashMap<String, String> specs = new HashMap<>();
    
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private ListView<String> imageListView;
    @FXML
    private ListView<String> videoListView;
    @FXML
    private TextField imageUrlTextField;
    @FXML
    private TextField videoLinkTextField;
    @FXML
    private ComboBox<String> keysComboBox;
    @FXML
    private TextField specValueTextField;
    @FXML
    private ListView<String> specListView;
    @FXML
    private Label statusLabel;
    @FXML
    private ComboBox<Category> allCategoriesComboBox;
    @FXML
    private TextField erpSNrTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommodityManagerFacade comManager = CommodityManager.getInstance();
        
        keysComboBox.getItems().addAll(comManager.getAllSpecKeys()); // Skal hente keys fra database
        
        this.allCategoriesComboBox.getItems().addAll(comManager.getAllCategories());
    }

    @FXML
    private void handleReturnToParent(ActionEvent event) {
        SceneSwitcher.cycleBackward();
    }

    @Override
    public void setupInternals() {
        
    }

    @FXML
    private void handleAttachImage(ActionEvent event) {
        if(!imageUrlTextField.getText().isEmpty()){
            images.add(imageUrlTextField.getText());
            imageListView.getItems().add(imageUrlTextField.getText());
        }
    }

    @FXML
    private void handleAttachVideo(ActionEvent event) {
        if(!videoLinkTextField.getText().isEmpty()){
            videos.add(videoLinkTextField.getText());
            videoListView.getItems().add(videoLinkTextField.getText());
        }
    }

    @FXML
    private void handleCreateProduct(ActionEvent event) {
        if (!this.nameTextField.getText().isEmpty() || !this.erpSNrTF.getText().isEmpty() || !this.descriptionTextArea.getText().isEmpty()) {
            CommodityManagerFacade comManager = CommodityManager.getInstance();
            int serialNumber = -1;
            try {
                serialNumber = Integer.parseInt(this.erpSNrTF.getText());
            } catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERP SNr feltet skal være tal");
                alert.setHeaderText("ERP serie nummer feltet skal være tal!");
                alert.showAndWait();
                return;
            }
            if(!comManager.validateSerialNumber(serialNumber)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERP SNr skal være eksisterende i ERP og være unik");
                alert.setHeaderText("ERP serie nummer skal være eksisterende i ERP og den må ikke tilhøre et andet produkt!");
                alert.showAndWait();
                return;
            }
            
            comManager.createProduct(nameTextField.getText(), images, videos,
                    descriptionTextArea.getText(), specs, serialNumber,
                    this.allCategoriesComboBox.getSelectionModel().getSelectedItem());

            // Go back to PIM
            SceneSwitcher.cycleBackward();
        } else {
            statusLabel.setText("Name, price and description MUST be filled out.");
        }

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
        if(specListView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        String[] parts = splitSpecification(specListView.getSelectionModel().getSelectedItem());
        specs.remove(parts[0]);
        specListView.getItems().remove(specListView.getSelectionModel().getSelectedItem());
    }

    private String[] splitSpecification(String string) {
        String[] parts = string.split(" = ");
        return parts;
    }

}
