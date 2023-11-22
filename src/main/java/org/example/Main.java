package org.example;

import java.sql.*;

/**
 *
 * @author Your name
 */
public class Main {
    public static void main(String... param) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "";

        System.out.println("Starting application...");

        try (Connection conn = DriverManager.getConnection(url, username, password);

             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clients")) {

            while (rs.next()) {
                int client_id = rs.getInt("client_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String telNumber = rs.getString("tel_number");
                String registrationDate = rs.getString("registration_date");
                Client client = new Client(client_id, name, surname, email, registrationDate, telNumber);

                System.out.println(client);

            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getMessage() {
        return "Hello World!";
    }
}