package org.example;

import java.sql.*;
import java.util.Map;

public class PostgresEngine extends DatabaseEngine {

    protected String url_;

    protected String username_;

    protected String password_;

    public PostgresEngine(String url, String username, String password) {
        super(url, username, password);
        url_ = url;
        username_ = username;
        password_ = password;
    }

    @Override
    public void createDatabase(String databaseName) {
        executeUpdate("CREATE DATABASE " + databaseName + ";");
    }

    @Override
    public void dropDatabase(String databaseName) {
        executeUpdate("DROP DATABASE " + databaseName + ";");
    }

    @Override
    public void createTable(String tableName, Map<String, String> columns) {
        String sql = "CREATE TABLE " + tableName + " (";
        for (Map.Entry<String, String> entry : columns.entrySet()) {
            sql += entry.getKey() + " " + entry.getValue() + ", ";
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += ");";
        executeUpdate(sql);
    }

    @Override
    public void dropTable(String tableName) {
        executeUpdate("DROP TABLE " + tableName + ";");
    }

    @Override
    public void createColumn(String tableName, String columnName, String columnType) {
        executeUpdate("ALTER TABLE " + tableName + " ADD " + columnName + " " + columnType + ";");
    }

    @Override
    public void dropColumn(String tableName, String columnName) {
        executeUpdate("ALTER TABLE " + tableName + " DROP COLUMN " + columnName + ";");
    }

    @Override
    public void createRow(String tableName, String rowName) {

    }

    @Override
    public void dropRow(String tableName, String rowName) {

    }

    @Override
    public void createRecord(String tableName, Fields[] values) {
        String keys = "";

        for (Fields value : values) {
            keys += value.getName() + ", ";
        }

        keys = keys.substring(0, keys.length() - 2);

        String sql = "INSERT INTO " + tableName + "(" + keys + ") VALUES (";

        for (Fields value : values) {

            if(value.getType() == "int") {
                sql += value.getValue() + ", ";
                continue;
            }

            sql +=  "'" + value.getValue() + "', ";
        }

        sql = sql.substring(0, sql.length() - 3);
        sql += ");";

        executeUpdate(sql);
    }

    @Override
    public void createForeignKey(String tableName, String foreignKeyName, String foreignKeyTable, String foreignKeyColumn) {
        String sql = "ALTER TABLE " + tableName + " ADD CONSTRAINT " + foreignKeyName + "_fkey FOREIGN KEY (" + foreignKeyName + ") REFERENCES " + foreignKeyName + " (" + foreignKeyName + ");";
        executeUpdate(sql);
    }

    @Override
    public void dropForeignKey(String tableName, String foreignKeyName) {
        executeUpdate("ALTER TABLE " + tableName + " DROP CONSTRAINT " + foreignKeyName + "_fkey;");
    }

    @Override
    public void createPrimaryKey(String tableName, String primaryKeyName) {
        String sql = "ALTER TABLE " + tableName + " ADD CONSTRAINT " + primaryKeyName + "pkey PRIMARY KEY (" + primaryKeyName + ");";
        executeUpdate(sql);
    }

    @Override
    public void dropPrimaryKey(String primaryKeyName) {

    }

    @Override
    public void createIndex(String indexName) {

    }

    @Override
    public void dropIndex(String indexName) {

    }

    private void executeUpdate(String sql) {
        System.out.println(sql);

        try (Connection conn = DriverManager.getConnection(url_, username_, password_);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
