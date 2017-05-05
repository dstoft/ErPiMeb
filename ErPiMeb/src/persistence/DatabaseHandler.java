/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author chris
 */
public class DatabaseHandler {
    /* Default values for Connection */
    private int port;
    private String url = "jdbc:postgresql://";
    private String host;
    private String databaseName;
    private String username;
    private String password;
    
    private Connection conn = null;
    
    public static void main(String[] args){
        DatabaseHandler db = new DatabaseHandler();
        Scanner userInput = new Scanner(System.in);
        
        Scanner fileInput;
        try {
            fileInput = new Scanner(new File("databaseInfo.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Database info file not found.");
            return;
        }
        
        db.port = Integer.parseInt(fileInput.nextLine());
        db.host = fileInput.nextLine();
        db.databaseName = fileInput.nextLine();
        db.username = fileInput.nextLine();
        db.password = fileInput.nextLine();
        
        System.out.println("Here you can change the database connection information to your own.");
        System.out.println("We have put in our own default values. Change them until it matches yours.");
        
        while(db.conn == null){
            System.out.println("Port: " + db.port);
            System.out.println("Host: " + db.host);
            System.out.println("Database Name: " + db.databaseName);
            System.out.println("Username: " + db.username);
            System.out.println("Password: " + db.password);
            
            try{
                db.conn = DriverManager.getConnection(db.url + db.host + ":" + db.port + "/" + db.databaseName, db.username, db.password);
            } catch (SQLException ex) {
                System.out.println("Connection information is invalid. Please edit the information.");
            }
            if(db.conn != null){
                System.out.println("Successfully established connection to database.");
                return;
            }
            
            System.out.println("Set port: ");
            db.port = Integer.parseInt(userInput.nextLine());
            
            System.out.println("Set Host: ");
            db.host = userInput.nextLine();
            
            System.out.println("Set Database name: ");
            db.databaseName = userInput.nextLine();
            
            System.out.println("Set Username: ");
            db.username = userInput.nextLine();
            
            System.out.println("Set Password: ");
            db.password = userInput.nextLine();
        }
    }
}
