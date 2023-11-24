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

        Fields[] fields = new Fields[5];
        fields[0] = new Fields("client_id");
        fields[1] = new Fields("name");
        fields[2] = new Fields("surname");
        fields[3] = new Fields("email");
        fields[4] = new Fields("registration_date");

        manager.getRecord("clients", fields , "client_id = 1");

        client = new Client("Jozas", "Budraitis", "Jozas.Budraitis@gmail.com", "2023-11-24", "+37060633111");
        manager.createRecord("clients", client.getValues());

        manager.getRecord("clients", fields , "client_id = 2");

        manager.deleteRecord("clients", "client_id = 2");

        manager.getAllRecords("clients", fields);

        manager.deleteRecord("clients", "client_id = 1");

        manager.getAllRecords("clients", fields);
    }

    private static void createDatabaseClients(DatabaseManager manager) {
        Map<String, String> columns = Client.getFields();
        manager.createTable("clients", columns);
    }

    public static String getMessage() {
        return "Hello World!";
    }
}