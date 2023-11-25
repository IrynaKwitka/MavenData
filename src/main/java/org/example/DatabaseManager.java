package org.example;

import java.util.Map;

public class DatabaseManager {
    private final DatabaseEngine engine_;

    public DatabaseManager(DatabaseEngine engine) {
        engine_ = engine;
    }

    public void createNewDatabase(String databaseName) {
        engine_.createDatabase(databaseName);
    }

    public void dropDatabase(String databaseName) {
        engine_.dropDatabase(databaseName);
    }

    public void createTable(Model model) {
        engine_.createTable(model);
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

    public void createRecord(String tableName, Fields[] values) {
        createRecord(tableName, values);
    }

    public void createRecord(Model model) {
        engine_.createRecord(model);
    }

    public void updateRecord(String tableName, Model model, String where) {
        engine_.updateRecord(tableName, model, where);
    }

    public void getRecord(Model model, String where) {
        engine_.getRecord(model, where);
    }

    public void deleteRecord(String tableName, String where) {
        engine_.deleteRecord(tableName, where);
    }

    public void getAll(Model model) {
        engine_.getAll(model);
    }

}
