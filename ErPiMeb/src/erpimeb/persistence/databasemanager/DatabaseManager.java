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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class DatabaseManager implements DatabaseManagerFacade {

    public static DatabaseManager manager;

    public DatabaseManager() {
        Scanner fileInput;
        try {
            fileInput = new Scanner(new File("databaseInfo.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Database info file not found.");
            return;
        }

        this.port = Integer.parseInt(fileInput.nextLine());
        this.host = fileInput.nextLine();
        this.databaseName = fileInput.nextLine();
        this.username = fileInput.nextLine();
        this.password = fileInput.nextLine();

        try {
            this.conn = DriverManager.getConnection(this.url + this.host + ":" + this.port + "/" + this.databaseName, this.username, this.password);
        } catch (SQLException ex) {
            System.out.println("Database connection error!");
        }
    }

    public static DatabaseManager getInstance() {
        if (manager == null) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    private ResultSet getAddressInfo(int id) {
        ResultSet rs = null;

        try {
            String SQL = "SELECT address, zip, country WHERE id = " + id; // SQL string to be executed
            rs = conn.createStatement().executeQuery(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
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

    public static void main(String[] args) {
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

        while (db.conn == null) {
            System.out.println("Port: " + db.port);
            System.out.println("Host: " + db.host);
            System.out.println("Database Name: " + db.databaseName);
            System.out.println("Username: " + db.username);
            System.out.println("Password: " + db.password);

            try {
                db.conn = DriverManager.getConnection(db.url + db.host + ":" + db.port + "/" + db.databaseName, db.username, db.password);
            } catch (SQLException ex) {
                System.out.println("Connection information is invalid. Please edit the information.");
            }
            if (db.conn != null) {
                try (BufferedWriter print = new BufferedWriter(new FileWriter(new File("databaseInfo.txt")))) {
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
        int id = product.getId();
        if(id < 0) {
            throw new IllegalArgumentException("There is not a valid id in the product!");
        }
        
        ResultSet rs = this.getProductInfo(id);
        try {
            rs.next();
            product.setName(rs.getString("Name"));
            product.setDescription(rs.getString("Description"));
            product.setPrice(rs.getDouble("Price"));
            product.setCategory(this.fillCategory(rs.getString("ProductCategory_Name")));
        } catch (SQLException ex) {
            System.out.println("Database error regarding fetching product data from a resultset!" + ex);
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
            System.out.println("Database error regarding fetching product images from a resultset!" + e);
            return;
        }
        
        // Specifications
        try {
            String query = "SELECT SpecKey, Value FROM Has NATURAL JOIN Spec WHERE ProductID=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            rs = prepSt.executeQuery();
            while(rs.next()) {
                product.addSpecification(rs.getString("SpecKey"), rs.getString("Value"));
            }
        } catch(SQLException e) {
            System.out.println("Database error regarding fetching product specifications from a resultset!" + e);
            return;
        }
        
        // There is no data regarding videos in the database!
        
        // Category
        
    }

    @Override
    public Category fillCategory(String categoryName) {
        Category newCategory = new Category();
        newCategory.setName(categoryName);
        return newCategory;
    }

    @Override
    public boolean saveOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveProduct(Product product) { // For you Daniel my boy
        boolean whatToReturn;
        boolean alreadyExists = false;
        
        try {
            String query = "SELECT ProductID FROM Product WHERE ProductID = ?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);

            prepSt.setInt(1, product.getId());

            ResultSet rs = prepSt.executeQuery();

            alreadyExists = rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking whether a product exists before inserting / updating it!");
            return false;
        }
        
        
        
        if(alreadyExists) {
            return this.updateProduct(product);
        } else {
            try {
                this.conn.createStatement().execute("Begin;");
            } catch (SQLException ex) {
                System.out.println("Database error regarding beginning a transaction!");
            }
            
            try {
                String query = "INSERT INTO Product(Name, Description, Price, ProductCategory_Name, ERP_SN) VALUES (?, ?, ?, ?, ?) RETURNING ProductID;";
                PreparedStatement prepSt = this.conn.prepareStatement(query);

                prepSt.setString(1, product.getName());
                prepSt.setString(2, product.getDescription());
                prepSt.setInt(3, 1337);
                prepSt.setString(4, product.getCategory().getName());
                prepSt.setInt(5, (int)(Math.random()*10000));

                ResultSet rs = prepSt.executeQuery();
                
                rs.next();
                product.setId(rs.getInt("ProductID"));
                
                this.insertProductRelationships(product);
                
                whatToReturn = true;
            } catch (SQLException e) {
                System.out.println("Database error regarding inserting a product!" + e);
                whatToReturn = false;
            }
            
            try {
                this.conn.createStatement().execute("Commit;");
            } catch (SQLException ex) {
                System.out.println("Database error regarding committing a transaction!");
            }
            
            return whatToReturn;
        }
    }
    
    private boolean updateProduct(Product product) {
        boolean whatToReturn;
        
        try {
            this.conn.createStatement().execute("Begin;");
        } catch (SQLException ex) {
            System.out.println("Database error regarding beginning a transaction!");
        }
        
        try {
            String query = "UPDATE Product SET Name=?, Description=?, ProductCategory_Name=?, Price=?, ERP_SN=? WHERE ProductID=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);

            prepSt.setString(1, product.getName());
            prepSt.setString(2, product.getDescription());
            prepSt.setString(3, product.getCategory().getName());
            prepSt.setInt(4, 1337);
            prepSt.setInt(5, (int)(Math.random()*100000));
            prepSt.setInt(6, product.getId());

            prepSt.executeUpdate();
            
            query = "DELETE FROM Has WHERE ProductID=?;";
            prepSt = this.conn.prepareStatement(query);

            prepSt.setInt(1, product.getId());

            prepSt.executeUpdate();
            
            query = "DELETE FROM Related WHERE ProductID_1=? OR ProductID_2=?;";
            prepSt = this.conn.prepareStatement(query);

            prepSt.setInt(1, product.getId());
            prepSt.setInt(2, product.getId());

            prepSt.executeUpdate();
            
            query = "DELETE FROM Contains WHERE ProductID=?;";
            prepSt = this.conn.prepareStatement(query);

            prepSt.setInt(1, product.getId());

            prepSt.executeUpdate();
            
            this.insertProductRelationships(product);
            whatToReturn = true;
        } catch (SQLException e) {
            System.out.println("Database error regarding updating a product!" + e);
            whatToReturn = false;
        }
        
        try {
            this.conn.createStatement().execute("Commit;");
        } catch (SQLException ex) {
            System.out.println("Database error regarding committing a transaction!");
        }
        
        return whatToReturn;
    }
    
    /**
     * 
     * @param product
     * @throws SQLException so it will break the transaction whereever it is used. This is instead of returning a boolean;
     */
    private void insertProductRelationships(Product product) throws SQLException {
        //Related products
        PreparedStatement prepSt;
        String query;
        for(Product relatedProduct : product.getRelatedProducts()) {
            query = "INSERT INTO Related VALUES (?, ?);";
            prepSt = this.conn.prepareStatement(query);

            prepSt.setInt(1, product.getId());
            prepSt.setInt(2, relatedProduct.getId());

            prepSt.executeUpdate();
        }

        //Images
        for(String imgUrl : product.getImages()) {
            int imgId;

            query = "SELECT ImageID FROM Image WHERE URL=?;";
            prepSt = this.conn.prepareStatement(query);

            prepSt.setString(1, imgUrl);

            ResultSet rs = prepSt.executeQuery();

            if(rs.next()) {
                imgId = rs.getInt("ImageId");
            } else {
                query = "INSERT INTO Image(URL) VALUES (?) RETURNING ImageID;";
                prepSt = this.conn.prepareStatement(query);
                prepSt.setString(1, imgUrl);
                rs = prepSt.executeQuery();
                rs.next();
                imgId = rs.getInt("ImageID");
            }

            query = "INSERT INTO Contains(ProductID, ImageID) VALUES (?, ?);";
            prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, product.getId());
            prepSt.setInt(2, imgId);
            prepSt.executeUpdate();
        }

        //Specifications
        for(String specKey : product.getSpecification().keySet()) {
            System.out.println(specKey);
            query = "INSERT INTO Has(ProductID, SpecKey, Value) VALUES (?, ?, ?);";
            prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, product.getId());
            prepSt.setString(2, specKey);
            prepSt.setString(3, product.getSpecification().get(specKey));
            prepSt.executeUpdate();
        }
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

    @Override
    public List<Product> searchForProduct(String productName) {
        ArrayList<Product> returnList = new ArrayList<>();
        if(productName.isEmpty()) {
            try {
                String query = "SELECT ProductID FROM Product;";
                ResultSet rs = this.conn.createStatement().executeQuery(query);
                
                while(rs.next()) {
                    returnList.add(new Product(rs.getInt("ProductID")));
                }
            } catch(SQLException e) {
                System.out.println("Database error regarding fetching all of the products when search for an empty string!" + e);
                return new ArrayList<>();
            }
        }
        
        for(Product product : returnList) {
            this.fillProduct(product);
        }
        
        System.out.println("Return list len: " + returnList.size());
        return returnList;
    }

    @Override
    public List<Integer> searchForProductId(String productName) {
        List<Integer> ids = new ArrayList<>();
        try {
            String SQL = "SELECT productid FROM product WHERE name = " + productName;
            ResultSet rs = conn.createStatement().executeQuery(SQL);

            while (rs.next()) {
                ids.add(rs.getInt("productid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ids;
    }

    private ResultSet getProductInfo(int id) {
        String query = "SELECT Name, Price, Description, ProductCategory_Name FROM Product WHERE ProductID=?";
        try {
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            return prepSt.executeQuery();
        } catch(SQLException e) {
            System.out.println("Something went wrong with fetching product info from the database!" + e);
            return null;
        }
    }

    /**
     *
     *
     * (NOTE: For testing, run this SQL in the DB, and then add a product with
     * 'energimærke' equals 'A'. SQL: "INSERT INTO Has VALUES('A', 1,
     * 'energimærke');")
     *
     * @param specifications
     * @return
     */
    @Override
    public List<Product> getRelatedProducts(Map<String, String> specifications) {
        ArrayList<Product> returnProducts = new ArrayList<>();

        for (String keyStr : specifications.keySet()) {
            String valueStr = specifications.get(keyStr);

            try {
                String query = "SELECT ProductID, Name FROM Spec NATURAL JOIN Has NATURAL JOIN Product WHERE RelateAble = true AND Key = ? AND Value = ?;";
                PreparedStatement prepSt = this.conn.prepareStatement(query);
                prepSt.setString(1, keyStr);
                prepSt.setString(2, valueStr);

                ResultSet rs = prepSt.executeQuery();
                if (rs.next()) {
                    Product tempProduct = new Product(rs.getInt("ProductID"));
                    tempProduct.setName(rs.getString("Name"));
                    returnProducts.add(tempProduct);
                }
            } catch (SQLException e) {
                System.out.println("Database error regarding saving a customer object!");
            }
        }

        System.out.println(returnProducts);
        return returnProducts;
    }

    @Override
    public List<String> getAllSpecKeys() {
        ArrayList<String> returnStrings = new ArrayList<>();

        try {
            String query = "SELECT SpecKey FROM Spec;";
            Statement st = this.conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                returnStrings.add(rs.getString("SpecKey"));
            }
        } catch (SQLException e) {
            System.out.println("Database error regarding fetching all the specification keys!" + e);
        }

        return returnStrings;
    }
}
