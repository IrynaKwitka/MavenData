package org.example;

import java.util.*;

/**
 * @author Your name
 */
public class Main {
    // DATABASE CONNECTION PARAMETERS
    protected static String url_;
    protected static String username_;
    protected static String password_;

    public static void main(String... param) {
        // SET DATABASE CONNECTION PARAMETERS
        url_ = "jdbc:postgresql://localhost:5432/";
        username_ = "postgres";
        password_ = "postgres";

        // SET DATABASE NAME
        String databaseName = "test";

        // SELECT ENGINE
        DatabaseEngine engine = new PostgresEngine(url_, username_, password_);
        // USE ENGINE IN DATABASE MANAGER
        DatabaseManager manager = new DatabaseManager(engine);

        try {
            // TRY TO DROP DATABASE IF IT EXISTS
            manager.dropDatabase(databaseName);
        } catch (Exception e) {
            System.out.println("Database does not exist");
        }

        // CREATE DATABASE
        manager.createNewDatabase(databaseName);

        // USE CREATED DATABASE
        String url = url_ + databaseName;
        engine = new PostgresEngine(url, username_, password_);
        manager = new DatabaseManager(engine);

        // CREATE TABLE CLIENTS USING CLIENT MODEL AND DATABASE MANAGER
        manager.createTable(new Client());

        // CREATE NEW CLIENT RECORD
        Client client1 = new Client("Jonas", "Jonaitis", "jonaitis@com.com", "2023-11-25", "+37060631938");
        manager.createRecord(client1);

        // UPDATE CLIENT RECORD
        manager.updateRecord("clients", new Client("Jonas", "Jonaitis", "jonas.jonaitis@gmail.com", "2023-11-25", "+37060631938"), "client_id = 1");

        // GET CLIENT RECORD
        manager.getRecord(new Client(), "client_id = 1");

        // CREATE NEW CLIENT RECORD
        manager.createRecord(new Client("Jozas", "Budraitis", "Jozas.Budraitis@gmail.com", "2023-11-24", "+37060633111"));

        // GET CLIENT RECORD
        manager.getRecord(new Client(), "client_id = 2");

        // GET ALL CLIENT RECORDS
        manager.getAll(new Client());

        // DELETE CLIENT RECORD
        manager.deleteRecord("clients", "client_id = 2");

        // GET ALL CLIENT RECORDS
        manager.getAll(new Client());

        // DELETE CLIENTS
        manager.deleteRecord("clients", "client_id = 1");

        manager.getAll(new Client());

        manager.createTable(new Product());

        // CREATE NEW PRODUCT RECORD
        manager.createRecord(new Product("Apple", 1.99f, 100, "Red apple"));

        // CREATE NEW PRODUCT RECORD
        manager.createRecord(new Product("Orange", 2.99f, 100, "Orange orange"));

        // CREATE NEW PRODUCT RECORD
        manager.createRecord(new Product("Banana", 3.99f, 100, "Yellow banana"));

        manager.getAll(new Product());

        // UPDATE PRODUCT RECORD
        manager.updateRecord("products", new Product("Apple", 1.99f, 100, "Green apple"), "product_id = 1");

        // GET PRODUCT RECORD
        manager.getRecord(new Product(), "product_id = 1");

        // DELETE PRODUCT RECORD
        manager.deleteRecord("products", "product_id = 1");

        // GET ALL PRODUCT RECORDS
        manager.getAll(new Product());

        // CREATE TABLE ORDERS USING ORDER MODEL AND DATABASE MANAGER
        manager.createTable(new Order());

        // CREATE NEW 3 CLIENTS RECORDS
        manager.createRecord(new Client("Jonas", "Jonaitis", "info@info.com", "2023-11-25", "+37060631938"));
        manager.createRecord(new Client("Jozas", "Budraitis", "info3@com", "2023-11-24", "+37060633456"));
        manager.createRecord(new Client("Petras", "Petraitis", "info2@com", "2023-11-24", "+3706063367"));

        //GET ALL CLIENTS RECORDS
        manager.getAll(new Client());

        // CREATE NEW ORDER RECORD
        manager.createRecord(new Order(3, "2023-11-25", 1.99f, 100));

        // CREATE NEW ORDER RECORD
        manager.createRecord(new Order(4, "2023-11-25", 2.99f, 50));

        // CREATE NEW ORDER RECORD
        manager.createRecord(new Order(5, "2023-11-25", 3.99f, 35));

        // GET ALL ORDER RECORDS
        manager.getAll(new Order());

        // UPDATE ORDER RECORD
        manager.updateRecord("orders", new Order(3, "2023-11-25", 1.99f, 100), "order_id = 1");

        // GET ORDER RECORD
        manager.getRecord(new Order(), "order_id = 1");

        // DELETE ORDER RECORD
        manager.deleteRecord("orders", "order_id = 1");

        // GET ALL ORDER RECORDS
        manager.getAll(new Order());

        // DELETE ORDER RECORDS
        manager.deleteRecord("orders", "order_id = 1");
        manager.deleteRecord("orders", "order_id = 2");
        manager.deleteRecord("orders", "order_id = 3");

        // DELETE PRODUCT RECORDS
        manager.deleteRecord("products", "product_id = 1");
        manager.deleteRecord("products", "product_id = 2");
        manager.deleteRecord("products", "product_id = 3");

        // DELETE CLIENT RECORDS
        manager.deleteRecord("clients", "client_id = 1");
        manager.deleteRecord("clients", "client_id = 2");
        manager.deleteRecord("clients", "client_id = 3");

        // DROP TABLES
        manager.dropTable("orders");
        manager.dropTable("products");
        manager.dropTable("clients");

    }
}