/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import UserManager.Address;
import UserManager.Customer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author chris
 */
public class DatabaseManager implements Facade{
    public static DatabaseManager manager;
    
    public static DatabaseManager getInstance(){
        if(manager == null){
            manager = new DatabaseManager();
        }
        return manager;
    }
    
    public Customer createCustomer(int id) {
        ResultSet rs;
        
        rs = manager.getCustomerInfo(id);
        
        Customer currentCustomer = new Customer();
        
        try {
            while (rs.next()) {
                currentCustomer.setEmail(rs.getString("email"));
                currentCustomer.setName(rs.getString("name"));
                currentCustomer.setPhoneNumber(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        rs = manager.getAddressInfo(id);
        
        Address newAddress = null;
        
        try {
            while (rs.next()) {
                newAddress = new Address(rs.getString("address"), rs.getInt("zip"), rs.getString("country"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        currentCustomer.addAddress(newAddress);
        
        return currentCustomer;
        
    }
    
    public ResultSet getCustomerInfo(int id) {
        ResultSet rs = null;
        
        try {
            String SQL = "SELECT name, email, phone WHERE id = " + id; // SQL string to be executed
            rs = conn.createStatement().executeQuery(SQL);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet getAddressInfo(int id) {
        ResultSet rs = null;
        
        try {
            String SQL = "SELECT address, zip, country WHERE id = " + id; // SQL string to be executed
            rs = conn.createStatement().executeQuery(SQL);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public boolean saveCustomer(Customer currentUser) {
        try {
            String SQL = "INSERT INTO customer(phone, password, name, temp, email) "
                    + "VALUES (" + currentUser.getPhoneNumber() + "," + currentUser.getPassword() 
                    + "," + currentUser.getName() + "," + currentUser.isTempCustomer() 
                    + "," + currentUser.getEmail() + ");";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    /* Default values for Connection */
    private int port;
    private String url = "jdbc:postgresql://";
    private String host;
    private String databaseName;
    private String username;
    private String password;
    
    private Connection conn = null;
    
    public static void main(String[] args){
        DatabaseManager db = new DatabaseManager();
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
                try (BufferedWriter print = new BufferedWriter(new FileWriter(new File("databaseInfo.txt")))){
                    print.write("" + db.port);
                    print.newLine();
                    print.write(db.host);
                    print.newLine();
                    print.write(db.databaseName);
                    print.newLine();
                    print.write(db.username);
                    print.newLine();
                    print.write(db.password);
                } catch (IOException ex) {
                    System.out.println("Error writing to the database info file.");
                    return;
                }
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
