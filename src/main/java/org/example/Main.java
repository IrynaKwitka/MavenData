package org.example;

import java.util.*;

/**
 * @author Your name
 */
public class Main {

    protected static String url_;
    protected static String username_;
    protected static String password_;

    public static void main(String... param) {
        url_ = "jdbc:postgresql://localhost:5432/";
        username_ = "postgres";
        password_ = "postgres";

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

        // USE DATABASE
        String url = url_ + databaseName;
        engine = new PostgresEngine(url, username_, password_);
        manager = new DatabaseManager(engine);

        // CREATE TABLE CLIENTS USING CLIENT MODEL AND DATABASE MANAGER
        manager.createTable(new Client());

        // CREATE NEW CLIENT RECORD
        Client client = new Client("Hello", "World!", "hello@gmail.com", "2023-11-23", "+37060631938");
        manager.createRecord("clients", client);

        // UPDATE CLIENT RECORD
        client = new Client("Jonas", "Jonaitis", "jonas.jonaitis@gmail.com", "2023-11-25", "+37060631938");
        manager.updateRecord("clients", client, "client_id = 1");

        // GET CLIENT RECORD
        manager.getRecord("clients", client.getFieldsOnly(), "client_id = 1");

        // CREATE NEW CLIENT RECORD
        client = new Client("Jozas", "Budraitis", "Jozas.Budraitis@gmail.com", "2023-11-24", "+37060633111");
        manager.createRecord("clients", client);

        // GET CLIENT RECORD
        manager.getRecord("clients", client.getFieldsOnly(), "client_id = 2");

        // GET ALL CLIENT RECORDS
        manager.getAll("clients", new Client());

        // DELETE CLIENT RECORD
        manager.deleteRecord("clients", "client_id = 2");

        // GET ALL CLIENT RECORDS
        manager.getAll("clients", new Client());

        // DELETE CLIENTS
        manager.deleteRecord("clients", "client_id = 1");

        manager.getAll("clients", new Client());
    }
}