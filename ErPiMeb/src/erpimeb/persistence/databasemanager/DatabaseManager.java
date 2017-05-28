/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.persistence.databasemanager;

import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.Product;
import erpimeb.domain.ordermanager.Order;
import erpimeb.domain.ordermanager.ReturnCase;
import erpimeb.domain.usermanager.Address;
import erpimeb.domain.usermanager.Customer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        if(userId < 0) {
            throw new IllegalArgumentException("Illegal userId passed in!");
        }
        
        Customer returnCustomer = new Customer();
        
        ResultSet rs = this.getCustomerInfo(userId);
        try {
            rs.next();
            returnCustomer.setName(rs.getString("Name"));
            returnCustomer.setEmail(rs.getString("Email"));
            returnCustomer.setPhoneNumber(rs.getString("Phone"));
        } catch (SQLException ex) {
            System.out.println("Database error regarding fetching customer data from a resultset!");
            return null;
        }
        
        rs = this.getAddressInfo(userId);
        try {
            while(rs.next()) {
                returnCustomer.addAddress(new Address(rs.getString("Address"), rs.getInt("Zip"), rs.getString("Country")));
            }
        } catch (SQLException ex) {
            System.out.println("Database error regarding fetching customer addresses from a resultset!");
            return null;
        }
        
        return returnCustomer;
    }

    @Override
    public void fillProduct(Product product) {
        int id = product.getId();
        if(id < 0) {
            throw new IllegalArgumentException("There is not a valid id in the product!");
        }
        
        ResultSet rs = this.getProductInfo(id);
        try {
            rs.next();
            product.setName(rs.getString("Name"));
            product.setDescription(rs.getString("Desc"));
            product.setPrice(rs.getDouble("Price"));
        } catch (SQLException ex) {
            System.out.println("Database error regarding fetching product data from a resultset!");
            return;
        }
        
        // Related products
        try {
            String query = "SELECT ProductID_2 FROM Related WHERE ProductID_1=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            rs = prepSt.executeQuery();
            while(rs.next()) {
                product.addRelatedProduct(new Product(rs.getInt("ProductID_2")));
            }
        } catch(SQLException e) {
            System.out.println("Database error regarding fetching product related products from a resultset!");
            return;
        }
        
        // Images
        try {
            String query = "SELECT URL FROM Contains NATURAL JOIN Image WHERE ProductID=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            rs = prepSt.executeQuery();
            while(rs.next()) {
                product.addImage(rs.getString("URL"));
            }
        } catch(SQLException e) {
            System.out.println("Database error regarding fetching product images from a resultset!");
            return;
        }
        
        // Specifications
        try {
            String query = "SELECT Key, Value FROM Has NATURAL JOIN Spec WHERE ProductID=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            rs = prepSt.executeQuery();
            while(rs.next()) {
                product.addSpecification(rs.getString("Key"), rs.getString("Value"));
            }
        } catch(SQLException e) {
            System.out.println("Database error regarding fetching product images from a resultset!");
            return;
        }
        
        // There is no data regarding videos in the database!
    }

    @Override
    public Category fillCategory(String categoryName) {
        //Should probably check whether the name exists in the db first, but oh well
        
        // Calling this.getCategoryInfo is useless, since Category only holds a name
        
        ResultSet rs;
        Category returnCategory = new Category();
        returnCategory.setName(categoryName);
        
        // Products
        try {
            ArrayList<Product> tempProducts = new ArrayList<>();
            String query = "SELECT ProductID FROM Holds WHERE ProductCategoryName=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setString(1, categoryName);
            rs = prepSt.executeQuery();
            while(rs.next()) {
                tempProducts.add(new Product(rs.getInt("ProductID")));
            }
            
            for(Product product : tempProducts) {
                this.fillProduct(product);
                returnCategory.addProduct(product);
            }
        } catch(SQLException e) {
            System.out.println("Database error regarding fetching category's products from a resultset!");
            return null;
        }
        
        // Subcategories
        try {
            ArrayList<Category> tempCategories = new ArrayList<>();
            String query = "SELECT Name_2 FROM Subcategory WHERE Name_1=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setString(1, categoryName);
            rs = prepSt.executeQuery();
            while(rs.next()) {
                Category newCategory = new Category();
                newCategory.setName(rs.getString("Name_2"));
                tempCategories.add(newCategory);
            }
            
            for(Category category : tempCategories) {
                returnCategory.addSubcategory(category);
            }
        } catch(SQLException e) {
            System.out.println("Database error regarding fetching category's subcategories from a resultset!");
            return null;
        }
        
        // Tags
        try {
            ArrayList<Category> tempCategories = new ArrayList<>();
            String query = "SELECT TagName FROM Includes NATURAL JOIN Tag WHERE Name=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setString(1, categoryName);
            rs = prepSt.executeQuery();
            while(rs.next()) {
                returnCategory.addTag(rs.getString("TagName"));
            }
        } catch(SQLException e) {
            System.out.println("Database error regarding fetching category's subcategories from a resultset!");
            return null;
        }
        
        return null;
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
        // 0 = forkert info
        // 1 = normal bruger
        // 2 = administrator
        // 3 = super admin
        // Need SQL implementation
        // so it returns the user ID for the specified username/password combination
        // Requires 2 different implementations for both admin login and user login, since they return 2 different values.
        // When doing an admin login this returns the clearance level of the administrator
        // When doing an user login this returns the user id of that username/password combination. 
        // The following are only test values
        switch (username) {
            case "superadmin":
                return 3;
            case "admin":
                return 2;
            case "user":
                return 1;
            case "user2":
                return 1;
                case "prutfisk":
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public List<Category> getCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getSubcategories(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private ResultSet getProductInfo(int id) {
        String query = "SELECT Name, Price, Desc FROM Product WHERE ProductID=?";
        try {
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            return prepSt.executeQuery();
        } catch(SQLException e) {
            System.out.println("Something went wrong with fetching product info from the database!");
            return null;
        }
    }
    
    private ResultSet getCustomerInfo(int id) {
        String query = "SELECT Name, Phone, Email FROM Product WHERE CustomerID=?";
        try {
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            return prepSt.executeQuery();
        } catch(SQLException e) {
            System.out.println("Something went wrong with fetching product info from the database!");
            return null;
        }
    }
    
    private ResultSet getAddressInfo(int userId) {
        String query = "SELECT Address, Zip, Country FROM ShipTo NATURAL JOIN Address WHERE CustomerID=?";
        try {
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, userId);
            return prepSt.executeQuery();
        } catch(SQLException e) {
            System.out.println("Something went wrong with fetching product info from the database!");
            return null;
        }
    }
    
    public String getEmail(int orderId){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    private ResultSet getCategoryInfo(String categoryName) {
//        String query = "SELECT Address, Zip, Country FROM ShipTo NATURAL JOIN Address WHERE CustomerID=?";
//        try {
//            PreparedStatement prepSt = this.conn.prepareStatement(query);
//            prepSt.setInt(1, userId);
//            return prepSt.executeQuery();
//        } catch(SQLException e) {
//            System.out.println("Something went wrong with fetching product info from the database!");
//            return null;
//        }
//    }
    
    @Override
    public List<Product> searchForProduct(String productName){
        List<Product> foundProducts = new ArrayList<>();
        foundProducts.add(new Product(1));
        foundProducts.add(new Product(2));
        for(Product prod : foundProducts){
            this.fillProduct(prod);
        }
        return foundProducts;
    }
    
    @Override
    //Dummy data.
    public Order fillOrder(int orderId){ 
        ArrayList<Product> dummyData = new ArrayList<>();
        Order filledOrder = new Order(1," name", "email", 
        "phoneNumber",  1,  "status",  true, 
        1, dummyData, 1, 
        "paymentMethod",  "deliveryInformation" , true);
        return filledOrder; 
         
    }
    @Override
    public void submitReturnForm(ReturnCase returnCase){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
