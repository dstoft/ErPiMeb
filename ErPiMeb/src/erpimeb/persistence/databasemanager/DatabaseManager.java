/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpimeb.persistence.databasemanager;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import erpimeb.domain.commoditymanager.Category;
import erpimeb.domain.commoditymanager.CommodityManager;
import erpimeb.domain.commoditymanager.CommodityManagerFacade;
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
        try (Scanner fileInput = new Scanner(new File("databaseInfo.txt"))) {
            this.port = Integer.parseInt(fileInput.nextLine());
            this.host = fileInput.nextLine();
            this.databaseName = fileInput.nextLine();
            this.username = fileInput.nextLine();
            this.password = fileInput.nextLine();
        } catch (FileNotFoundException ex) {
            System.out.println("Database info file not found.");
        }

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
    
    @Override
    public boolean saveCustomer(Customer currentUser) {

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate("Begin;");
        } catch (SQLException e) {
            System.out.println("Something went wrong with beginning a commit for saving a customer!");
        }

        try {
            String query = "INSERT INTO Customer(Phone, Name, Email) VALUES (?, ?, ?) RETURNING CustomerID;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, Integer.parseInt(currentUser.getPhoneNumber())); //Fix this line when phone in the DB is a varchar!!!!
            prepSt.setString(2, currentUser.getName());
            prepSt.setString(3, currentUser.getEmail());
            ResultSet rs = prepSt.executeQuery();
            
            int newUserID = -1;
            if(rs.next()) {
                newUserID = rs.getInt("CustomerID");
            }
            
            if(!currentUser.isTempCustomer() && newUserID >= 0) {
                query = "INSERT INTO Login(CustomerID, Password) VALUES (?, ?);";
                prepSt = this.conn.prepareStatement(query);
                prepSt.setInt(1, newUserID);
                prepSt.setString(2, currentUser.getPassword());
                prepSt.executeUpdate();
            }

            
        } catch (SQLException e) {
            System.out.println("Database error regarding saving a customer object!" + e);
        }

        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate("COMMIT;");
            return true;
        } catch (SQLException e) {
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
        if (userId < 0) {
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
            while (rs.next()) {
                returnCustomer.addAddress(new Address(rs.getString("Address"), rs.getInt("Zip"), rs.getString("Country")));
            }
        } catch (SQLException ex) {
            System.out.println("Database error regarding fetching customer addresses from a resultset!");
            return null;
        }

        return returnCustomer;
    }

    private void fillProduct(Product product, Category category) {
        product.setCategory(category);
        this.fillProduct(product);
    }
    
    @Override
    public void fillProduct(Product product) {
        int id = product.getId();
        if (id < 0) {
            throw new IllegalArgumentException("There is not a valid id in the product!");
        }
        
        ResultSet rs = this.getProductInfo(id);
        try {
            rs.next();
            product.setName(rs.getString("Name"));
            product.setDescription(rs.getString("Description"));
            product.setErpSn(rs.getInt("erp_sn"));
            if(product.getCategory() == null) {
                product.setCategory(this.fillCategory(rs.getString("ProductCategory_Name")));
            }
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
            while (rs.next()) {
                product.addRelatedProduct(new Product(rs.getInt("ProductID_2")));
            }
        } catch (SQLException e) {
            System.out.println("Database error regarding fetching product related products from a resultset!");
            return;
        }
        
        // Images
        try {
            String query = "SELECT URL FROM Contains NATURAL JOIN Image WHERE ProductID=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                product.addImage(rs.getString("URL"));
            }
        } catch (SQLException e) {
            System.out.println("Database error regarding fetching product images from a resultset!" + e);
            return;
        }
        
        // Specifications
        try {
            String query = "SELECT SpecKey, Value FROM Has NATURAL JOIN Spec WHERE ProductID=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                product.addSpecification(rs.getString("SpecKey"), rs.getString("Value"));
            }
        } catch (SQLException e) {
            System.out.println("Database error regarding fetching product specifications from a resultset!" + e);
            return;
        }

        // There is no data regarding videos in the database!
    }

    @Override
    public Category fillCategory(String categoryName) {
//        connect();
        //Should probably check whether the name exists in the db first, but oh well

        // Calling this.getCategoryInfo is useless, since Category only holds a name
        ResultSet rs;
        Category returnCategory = new Category();
        returnCategory.setName(categoryName);

        // Subcategories
        try {
            ArrayList<Category> tempCategories = new ArrayList<>();
            String query = "SELECT categoryname_2 FROM subcategory WHERE categoryname_1 = '" + categoryName + "';";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            //prepSt.setString(1, categoryName);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setName(rs.getString("categoryname_2"));
                tempCategories.add(newCategory);
            }

            for (Category category : tempCategories) {
                this.fillSubCategory(category);
                returnCategory.addSubcategory(category);
            }
        } catch (SQLException e) {
            System.out.println("Database error regarding fetching category's subcategories from a resultset!");
            return null;
        }

        // Products
        try {
            ArrayList<Product> tempProducts = new ArrayList<>();
            //String query = "SELECT productid FROM product NATURAL JOIN subcategory WHERE productcategory_name = subcategory.categoryname_2 AND categoryname_1 = '" + categoryName + "';";
            String query = "SELECT ProductID FROM Product WHERE ProductCategory_Name = ?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setString(1, categoryName);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                tempProducts.add(new Product(rs.getInt("productid")));
            }

            for (Product product : tempProducts) {
                this.fillProduct(product, returnCategory);
                returnCategory.addProduct(product);
            }
        } catch (SQLException e) {
            System.out.println("Database error regarding fetching category's products from a resultset!" + e);
            return null;
        }

        // Tags
        try {
            ArrayList<Category> tempCategories = new ArrayList<>();
            String query = "SELECT TagName FROM Includes NATURAL JOIN Tag WHERE Name=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setString(1, categoryName);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                returnCategory.addTag(rs.getString("TagName"));
            }
        } catch (SQLException e) {
            System.out.println("Database error regarding fetching category's subcategories from a resultset!");
            return null;
        }
        return returnCategory;
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
                String query = "INSERT INTO Product(Name, Description, ProductCategory_Name, ERP_SN) VALUES (?, ?, ?, ?) RETURNING ProductID;";
                PreparedStatement prepSt = this.conn.prepareStatement(query);

                prepSt.setString(1, product.getName());
                prepSt.setString(2, product.getDescription());
                prepSt.setString(3, product.getCategory().getName());
                prepSt.setInt(4, product.getErpSn());

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
            String query = "UPDATE Product SET Name=?, Description=?, ProductCategory_Name=?, ERP_SN=? WHERE ProductID=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);

            prepSt.setString(1, product.getName());
            prepSt.setString(2, product.getDescription());
            prepSt.setString(3, product.getCategory().getName());
            prepSt.setInt(4, product.getErpSn());
            prepSt.setInt(5, product.getId());

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

    // implementeres når testdata er færdig
    @Override
    public boolean saveCategory(Category category) {
        boolean whatToReturn = false;
        
        try {
            String query = "SELECT CategoryName FROM ProductCategory WHERE CategoryName=?;";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setString(1, category.getName());
            ResultSet rs = prepSt.executeQuery();
            if(rs.next()) {
                System.out.println("The category already exists!");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Database error regarding figuring out whether a category already exists!");
        }
        
        try {
            this.conn.createStatement().execute("Begin;");
        } catch (SQLException ex) {
            System.out.println("Database error regarding beginning a transaction!");
        }

        try {
            String query = "INSERT INTO ProductCategory(CategoryName) VALUES (?);";
            PreparedStatement prepSt = this.conn.prepareStatement(query);

            prepSt.setString(1, category.getName());
            
            prepSt.executeUpdate();
            
            ResultSet rs;
            for(String tagStr : category.getTagList()) {
                query = "INSERT INTO Tag(TagName) VALUES (?) RETURNING TagID;";
                prepSt = this.conn.prepareStatement(query);
                prepSt.setString(1, tagStr);
                rs = prepSt.executeQuery();
                rs.next();
                int tagID = rs.getInt("TagID");
                
                query = "INSERT INTO Includes VALUES (?, ?);";
                prepSt = this.conn.prepareStatement(query);
                prepSt.setInt(1, tagID);
                prepSt.setString(2, category.getName());
                prepSt.executeUpdate();
            }
            
            for(Product product : category.getProductList()) {
                query = "UPDATE Product SET ProductCategory_Name = ? WHERE ProductID = ?;";
                prepSt = this.conn.prepareStatement(query);
                prepSt.setString(1, category.getName());
                prepSt.setInt(2, product.getId());
                prepSt.executeUpdate();
            }
            
            for(Category subcategory : category.getSubcategories()) {
                query = "INSERT INTO Subcategory VALUES (?, ?);";
                prepSt = this.conn.prepareStatement(query);
                prepSt.setString(1, category.getName());
                prepSt.setString(2, subcategory.getName());
                prepSt.executeUpdate();
            }
            
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
    public List<Category> getMainCategories() {
        List<Category> categories = new ArrayList<>();

        ResultSet rs;
        String query = "SELECT DISTINCT categoryname FROM productcategory NATURAL JOIN subcategory WHERE categoryname = subcategory.categoryname_1;";
        try {
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                categories.add(this.fillCategory(rs.getString("categoryname")));
            }
        } catch (SQLException e) {
            System.out.println("Database error regarding fetching ");
        }

        return categories;
    }

    @Override
    public List<Category> getSubcategories(String categoryName) {
        List<Category> subCategories = new ArrayList<>();

        ResultSet rs;
        String query = "SELECT DISTINCT categoryname_2 FROM subcategory WHERE categoryname_1 = '" + categoryName + "';";
        try {
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setName(rs.getString("categoryname_2"));
                this.fillSubCategory(newCategory);
                subCategories.add(newCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subCategories;
    }

    private ResultSet getCustomerInfo(int id) {
        String query = "SELECT Name, Phone, Email FROM Product WHERE CustomerID=?";
        try {
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, id);
            return prepSt.executeQuery();
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
    public List<Long> getOrderTimestamps(String status, long since) {
        System.out.println("db test!");
        ArrayList<Long> returnList = new ArrayList<>();

        returnList.add(1494757676748L);
        returnList.add(1494757676748L);
        returnList.add(1494584876748L);
        returnList.add(1495448876748L);
        returnList.add(1493288876748L);
        returnList.add(1493375276748L);
        returnList.add(1493029676748L);

        return returnList;
    }

    @Override
    public void fillSubCategory(Category category) {
        // Calling this.getCategoryInfo is useless, since Category only holds a name
        ResultSet rs;

        // Products
        try {
            ArrayList<Product> tempProducts = new ArrayList<>();
            String query = "SELECT DISTINCT productid FROM product NATURAL JOIN subcategory WHERE productcategory_name = '" + category.getName() + "';";
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            //prepSt.setString(1, categoryName);
            rs = prepSt.executeQuery();
            while (rs.next()) {
                tempProducts.add(new Product(rs.getInt("productid")));
            }

            for (Product product : tempProducts) {
                this.fillProduct(product);
                category.addProduct(product);
            }
        } catch (SQLException e) {
            System.out.println("Database error regarding fetching category's products from a resultset!");
            return;
        }
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
        String query = "SELECT Name, erp_sn, Description, ProductCategory_Name FROM Product WHERE ProductID=?";
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
                String query = "SELECT ProductID, Name FROM Spec NATURAL JOIN Has NATURAL JOIN Product WHERE RelateAble = true AND SpecKey = ? AND Value = ?;";
                PreparedStatement prepSt = this.conn.prepareStatement(query);
                prepSt.setString(1, keyStr);
                prepSt.setString(2, valueStr);

                ResultSet rs = prepSt.executeQuery();
                while (rs.next()) {
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

    @Override
    public List<Category> getAllCategories() {
        String query = "SELECT CategoryName FROM ProductCategory;";
        Statement st;
        try {
            st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            ArrayList<Category> returnCategories = new ArrayList<>();
            while(rs.next()) {
                Category newCategory = new Category();
                newCategory.setName(rs.getString("CategoryName"));
                returnCategories.add(newCategory);
            }
            return returnCategories;
        } catch (SQLException ex) {
            System.out.println("Database error regarding fetching all of the categories.");
            return new ArrayList<>();
        }
    }

    @Override
    public List<Category> getNonMainCategories() {
        String query = "SELECT CategoryName FROM ProductCategory WHERE NOT CategoryName IN (SELECT CategoryName_1 FROM Subcategory);";
        Statement st;
        try {
            st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            ArrayList<Category> returnCategories = new ArrayList<>();
            while(rs.next()) {
                Category newCategory = new Category();
                newCategory.setName(rs.getString("CategoryName"));
                returnCategories.add(newCategory);
            }
            return returnCategories;
        } catch (SQLException ex) {
            System.out.println("Database error regarding fetching all of the categories.");
            return new ArrayList<>();
        }
    }

    @Override
    public boolean isErpSnAssigned(int erpSn) {
        String query = "SELECT ERP_SN FROM Product WHERE ERP_SN=?";
        try {
            PreparedStatement prepSt = this.conn.prepareStatement(query);
            prepSt.setInt(1, erpSn);
            ResultSet rs = prepSt.executeQuery();
            return rs.next();
        } catch(SQLException e) {
            System.out.println("Something went wrong with fetching product info from the database!" + e);
            return false;
        }
    }
    
    @Override
    //Dummy data.
    public Order fillOrder(int orderId){ 
//        ArrayList<Product> dummyData = new ArrayList<>();
//        Order filledOrder = new Order(1," name", "email", 
//        "phoneNumber",  1,  "status",  true, 
//        1, dummyData, 1, 
//        "paymentMethod",  "deliveryInformation" , true);
//        return filledOrder; 
//
// return null, is only so i dont get error before merge. 
        return null; 
    }
    @Override
    public void submitReturnForm(ReturnCase returnCase){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
