/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui.webshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class CustomerProfileController implements Initializable {
    private Stage stageRef;
    private Scene preSceneRef;
    private String preSceneTitle;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setReferences(Stage stageRef, Scene scene, String title) {
        this.stageRef = stageRef;
        this.preSceneRef = scene;
        this.preSceneTitle = title;
    }
    
}
