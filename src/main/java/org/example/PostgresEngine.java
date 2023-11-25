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
    public void createTable(Model model) {
        String tableName = model.tableName;
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
    public void createRecord(String tableName, Model model) {
        String keys = "";

        Fields[] values = model.getValues();

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

        sql = sql.substring(0, sql.length() - 2);
        sql += ");";

        executeUpdate(sql);
    }

    @Override
    public void updateRecord(String tableName, Model model, String where) {
        Fields [] values = model.getValues();

        String sql = "UPDATE " + tableName + " SET ";

        for (Fields value : values) {

            if(value.getType() == "int") {
                sql += value.getName() + "=" + value.getValue() + ", ";
                continue;
            }

            sql += value.getName() + "='" + value.getValue() + "', ";
        }

        sql = sql.substring(0, sql.length() - 2);
        sql += " WHERE " + where + ";" ;

        executeUpdate(sql);
    }

    @Override
    public void getRecord(String tableName, Fields [] fields, String where) {
        String sql = "SELECT ";

        for (Fields value : fields) {
            sql += value.getName() + ", ";
        }

        sql = sql.substring(0, sql.length() - 2);
        sql += " FROM " + tableName + " WHERE " + where + ";" ;

        System.out.println(sql);

        try (Connection conn = DriverManager.getConnection(url_, username_, password_);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                for (Fields value : fields) {
                    System.out.println(rs.getString(value.getName()));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getAllRecords(String tableName, Fields [] fields) {
        String sql = "SELECT * FROM " + tableName + ";" ;

        System.out.println(sql);

        try (Connection conn = DriverManager.getConnection(url_, username_, password_);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                for (Fields value : fields) {
                    System.out.println(rs.getString(value.getName()));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getAll(String tableName, Model model) {
        String sql = "SELECT * FROM " + tableName + ";" ;
        System.out.println(sql);

        try(Connection conn = DriverManager.getConnection(url_, username_, password_);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Model row = model.addRow(rs);
                System.out.println(row.toString());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecord(String tableName, String where) {
        String sql = "DELETE FROM " + tableName + " WHERE " + where + ";" ;
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
