package org.example;

import java.util.Map;

public class MySqlEngine extends DatabaseEngine{
    public MySqlEngine(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public void createDatabase(String databaseName) {

    }

    @Override
    public void dropDatabase(String databaseName) {

    }

    @Override
    public void createTable(Model model) {

    }

    @Override
    public void dropTable(String tableName) {

    }

    @Override
    public void createColumn(String table, String columnName, String columnType) {

    }

    @Override
    public void dropColumn(String tableName, String columnName) {

    }

    @Override
    public void getRecord(Model model, String where) {

    }

    @Override
    public void createForeignKey(String tableName, String foreignKeyName, String foreignKeyTable, String foreignKeyColumn) {

    }

    @Override
    public void dropForeignKey(String tableName, String foreignKeyName) {

    }

    @Override
    public void createPrimaryKey(String tableName, String primaryKeyName) {

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

    @Override
    public void createRecord(Model model) {

    }

    @Override
    public void updateRecord(String tableName, Model model, String where) {

    }

    @Override
    public void deleteRecord(String tableName, String where) {

    }

    @Override
    public void getAll(Model model) {

    }

    @Override
    public void executeQuery(String query) {

    }
}
