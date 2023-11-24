package org.example;

import java.util.Map;

public class DatabaseManager {
    private final DatabaseEngine engine_;

    public DatabaseManager(DatabaseEngine engine) {
        engine_ = engine;
    }

    public void createNewDatabase (String databaseName) {
        engine_.createDatabase (databaseName);
    }

    public void dropDatabase (String databaseName) {
        engine_.dropDatabase (databaseName);
    }

    public void createTable(String tableName, Map<String, String> columns) {
        engine_.createTable(tableName, columns);
    }

    public void dropTable(String tableName) {
        engine_.dropTable(tableName);
    }

    public void createColumn(String table, String columnName, String columnType) {
        engine_.createColumn(table, columnName, columnType);
    }

    public void createPrimaryKey(String tableName, String primaryKeyName) {
        engine_.createPrimaryKey(tableName, primaryKeyName);
    }

    public void createRow(String tableName, String rowName) {
        engine_.createRow(tableName, rowName);
    }

    public void createRecord(String tableName, Fields[] values) {
        engine_.createRecord(tableName, values);
    }

}
