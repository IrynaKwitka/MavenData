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

        DatabaseEngine engine = new PostgresEngine(url_, username_, password_);
        DatabaseManager manager = new DatabaseManager(engine);

        try {
            manager.dropDatabase(databaseName);
        } catch (Exception e) {
            System.out.println("Database does not exist");
        }

        manager.dropDatabase(databaseName);

        manager.createNewDatabase(databaseName);

        String url = url_ + databaseName;
        engine = new PostgresEngine(url, username_, password_);
        manager = new DatabaseManager(engine);

        createDatabaseClients(manager);

        Client client = new Client("Hello", "World!", "hello@gmail.com", "2023-11-23", "+37060631938");
        manager.createRecord("clients", client.getValues());

        client = new Client("Jonas", "Jonaitis", "jonas.jonaitis@gmail.com", "2023-11-25", "+37060631938");
        manager.updateRecord("clients", client.getValues(), "client_id = 1");

        manager.getRecord("clients", client.getFieldsOnly(), "client_id = 1");

        client = new Client("Jozas", "Budraitis", "Jozas.Budraitis@gmail.com", "2023-11-24", "+37060633111");
        manager.createRecord("clients", client.getValues());

        manager.getRecord("clients", client.getFieldsOnly(), "client_id = 2");
        manager.getAll("clients", new Client());

        manager.deleteRecord("clients", "client_id = 2");

        manager.getAll("clients", new Client());

        manager.deleteRecord("clients", "client_id = 1");

        manager.getAll("clients", new Client());
    }

    private static void createDatabaseClients(DatabaseManager manager) {
        Map<String, String> columns = new Client().getFields();
        manager.createTable("clients", columns);
    }
}