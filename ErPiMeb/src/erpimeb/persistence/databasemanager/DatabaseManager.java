/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.persistence.databasemanager;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.ordermanager.Order;
import erpimeb.domain.usermanager.Address;
import erpimeb.domain.usermanager.Customer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.sql.*;
import java.util.List;

/**
 *
 * @author chris
 */
public class DatabaseManager implements DatabaseManagerFacade{
    public static DatabaseManager manager;
    
    public static DatabaseManager getInstance(){
        if(manager == null){
            manager = new DatabaseManager();
        }
        return manager;
    }
    
    public Customer createCustomer(int id) {
        return new Customer();
        
        /*
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
        */
    }
    
    private ResultSet getCustomerInfo(int id) {
        ResultSet rs = null;
        
        try {
            String SQL = "SELECT name, email, phone WHERE id = " + id; // SQL string to be executed
            rs = conn.createStatement().executeQuery(SQL);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    private ResultSet getAddressInfo(int id) {
        ResultSet rs = null;
        
        try {
            String SQL = "SELECT address, zip, country WHERE id = " + id; // SQL string to be executed
            rs = conn.createStatement().executeQuery(SQL);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    @Override
    public boolean saveCustomer(Customer currentUser) {
        
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate("Begin;");
        } catch(SQLException e) {
            System.out.println("Something went wrong with beginning a commit for saving a customer!");
        }
        
        try {
            String query = "INSERT INTO Customer(Phone, Name, Temp, Email) "
                    + "VALUES (?, ?, FALSE, ?);";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setString(1, currentUser.getPhoneNumber());
            prepSt.setString(2, currentUser.getName());
            prepSt.setString(3, currentUser.getEmail());
            prepSt.executeUpdate();
            
            query = "INSERT INTO Login(Email, Password) "
                    + "VALUES (?, ?);";
            prepSt = this.conn.prepareStatement(query);
            prepSt.setString(1, currentUser.getEmail());
            prepSt.setString(2, currentUser.getPassword());
            prepSt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Database error regarding saving a customer object!");
        }
        
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate("COMMIT;");
            return true;
        } catch(SQLException e) {
            System.out.println("Something went wrong with commit saving a customer!");
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

    @Override
    public Customer fillCustomer(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category fillCategory(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order createOrder(int orderId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int checkCredentials(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getSubcategories(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
