/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.gui;

import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author chris
 */
public class SceneSwitcher {
    private static Stage primaryStage;
    private static Switchable currentController;
    private static Scene currentScene;
    private static String currentTitle;
    private static Stack<Scene> preSceneStack = new Stack<>();
    private static Stack<String> preTitleStack = new Stack<>();
    
    public static void changeScene(String fxml, String title){
        if(currentScene != null){
            preSceneStack.push(currentScene);
            preTitleStack.push(currentTitle);
        }
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxml));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            System.out.println("Something went wrong with changing the scene!" + ex);
        }
        currentController = loader.getController();
        
        currentScene = new Scene(root);
        currentTitle = title;
        
        primaryStage.setScene(currentScene);
        primaryStage.setTitle(title);
        primaryStage.show();
        currentController.setupInternals();
    }
    
    public static void setStage(Stage stage){
        primaryStage = stage;
    }
    
    public static void cycleBackward(){
        if(preSceneStack.size() == 1){
            currentScene = preSceneStack.peek();
            currentTitle = preTitleStack.peek();
        } else {
            currentScene = preSceneStack.pop();
            currentTitle = preTitleStack.pop();
        }

        primaryStage.setScene(currentScene);
        primaryStage.setTitle(currentTitle);
        primaryStage.show();
    }
}
