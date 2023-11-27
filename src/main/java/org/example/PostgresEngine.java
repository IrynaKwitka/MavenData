package org.example;

import java.sql.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

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
    public void createTable(Model model) {
        // GET TABLE NAME
        String tableName = model.getTableName();
        // GET TABLE COLUMNS
        Map<String, String> columns = model.getColumns();

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
    public void createRecord(Model model) {
        String keys = "";
        String tableName = model.getTableName();

        Fields[] values = model.getValues();

        for (Fields value : values) {
            keys += value.getName() + ", ";
        }

        keys = keys.substring(0, keys.length() - 2);

        String sql = "INSERT INTO " + tableName + " (" + keys + ") VALUES (";

        for (Fields value : values) {

            if (value.getType() == "int" || value.getType() == "float" || value.getType() == "double") {
                sql += value.getValue() + ", ";
                continue;
            }

            sql += "'" + value.getValue() + "', ";
        }

        sql = sql.substring(0, sql.length() - 2);
        sql += ");";

        executeUpdate(sql);
    }

    @Override
    public void updateRecord(String tableName, Model model, String where) {
        Fields[] values = model.getValues();

        String sql = "UPDATE " + tableName + " SET ";

        for (Fields value : values) {

            if (value.getType() == "int" || value.getType() == "float" || value.getType() == "double") {
                sql += value.getName() + "=" + value.getValue() + ", ";
                continue;
            }

            sql += value.getName() + "='" + value.getValue() + "', ";
        }

        sql = sql.substring(0, sql.length() - 2);
        sql += " WHERE " + where + ";";

        executeUpdate(sql);
    }

    @Override
    public void getRecord(Model model, String where) {

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.select(model.getSelectString());
        queryBuilder.from(model.getTableName());
        queryBuilder.where(where);
        String sql = queryBuilder.build();

        System.out.println(sql);

        try (Connection conn = DriverManager.getConnection(url_, username_, password_);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                for (Fields value : model.getFieldsOnly()) {
                    System.out.println(rs.getString(value.getName()));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getAll(Model model) {
        String tableName = model.getTableName();
        String sql = "SELECT * FROM " + tableName + ";";
        System.out.println(sql);

        try (Connection conn = DriverManager.getConnection(url_, username_, password_);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Model row = model.addRow(rs);
                System.out.println(row.toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecord(String tableName, String where) {
        String sql = "DELETE FROM " + tableName + " WHERE " + where + ";";
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

    @Override
    public void executeQuery(String query) {
        try (Connection conn = DriverManager.getConnection(url_, username_, password_);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            List<Map<String, Object>> list = null;
            if (rs != null) {
                list = new ArrayList<Map<String, Object>>();
                try {
                    ResultSetMetaData rsMetaData = rs.getMetaData();
                    int columnCount = rsMetaData.getColumnCount();
                    while (rs.next()) {
                        Map<String, Object> row = new HashMap<String, Object>(columnCount);
                        for (int i = 1; i <= columnCount; i++) {
                            row.put(rsMetaData.getColumnName(i), rs.getObject(i));
                        }
                        list.add(row);
                        System.out.println(row.toString());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

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
