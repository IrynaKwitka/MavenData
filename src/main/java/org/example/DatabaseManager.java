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

    public void updateRecord(String tableName, Fields[] values, String where) {
        engine_.updateRecord(tableName, values, where);
    }

    public void getRecord (String tableName, Fields[] fields, String where) {
        engine_.getRecord(tableName, fields, where);
    }

    public void deleteRecord(String tableName, String where) {
        engine_.deleteRecord(tableName, where);
    }

    public void getAllRecords(String tableName, Fields [] fields) {
        engine_.getAllRecords(tableName, fields);
    }

}
