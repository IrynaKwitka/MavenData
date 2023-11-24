package org.example;

import java.sql.*;
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
        manager.dropDatabase(databaseName);

        manager.createNewDatabase(databaseName);

        String url = url_ + databaseName;
        engine = new PostgresEngine(url, username_, password_);
        manager = new DatabaseManager(engine);

        createDatabaseClients(manager);

        Client client = new Client("Hello", "World!", "hello@gmail.com", "2023-11-23", "+37060631938");

        Fields[] values = client.getValues();
        manager.createRecord("clients", values);
    }

    private static void createDatabaseClients(DatabaseManager manager) {
        Map<String, String> columns = Client.getFields();
        manager.createTable("clients", columns);
    }

    public static String getMessage() {
        return "Hello World!";
    }
}