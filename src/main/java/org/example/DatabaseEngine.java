package org.example;

import java.util.Map;

public abstract class DatabaseEngine {
    public DatabaseEngine(String url, String username, String password) {
    }

    public abstract void createDatabase (String databaseName);
    public abstract void dropDatabase (String databaseName);

    public abstract void createTable(Model model);

    public abstract void dropTable(String tableName);

    public abstract void createColumn(String table, String columnName, String columnType);

    public abstract void dropColumn(String tableName, String columnName);

    public abstract void getRecord(Model model, String where);

    public abstract void createForeignKey(String tableName, String foreignKeyName, String foreignKeyTable, String foreignKeyColumn);

    public abstract void dropForeignKey(String tableName, String foreignKeyName);

    public abstract void createPrimaryKey(String tableName, String primaryKeyName);

    public abstract void dropPrimaryKey(String primaryKeyName);

    public abstract void createIndex(String indexName);

    public abstract void dropIndex(String indexName);

    public abstract void createRecord(Model model);

    public abstract void updateRecord(String tableName, Model model, String where);

    public abstract void deleteRecord(String tableName, String where);

    public abstract void getAll(Model model);
}
