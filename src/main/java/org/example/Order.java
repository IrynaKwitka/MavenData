package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Order implements Model {
        private int order_id;
        private int client_id;
        private String date;
        private float price;
        private int order_quantity;

    public static String tableName = "orders";
    public Order() {
    }
    public Order (int client_id, String date, float price, int order_quantity) {
        this.client_id = client_id;
        this.date = date;
        this.price = price;
        this.order_quantity = order_quantity;
    }
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getDate() {
        return date;
    }

    public void setData(String data) {
        this.date = data;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity) {
        this.order_quantity = order_quantity;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Map<String, String> getColumns() {
        Map<String, String> fields = new HashMap<>(5);
        fields.put("order_id", "serial PRIMARY KEY");
        fields.put("client_id", "int REFERENCES clients(client_id) ON DELETE SET NULL ON UPDATE CASCADE");
        fields.put("date", "date DEFAULT NULL");
        fields.put("price", "float DEFAULT NULL");
        fields.put("order_quantity", "int DEFAULT NULL");
        return fields;
    }

    /**
     * @return
     */
    @Override
    public Fields[] getValues() {
        Fields[] fields = new Fields[4];
        fields[0] = new Fields("client_id", "String", String.valueOf(client_id));
        fields[1] = new Fields("date", "Date", String.valueOf(date));
        fields[2] = new Fields("price", "float", String.valueOf (price));
        fields[3] = new Fields("order_quantity", "int", String.valueOf (order_quantity));
        return fields;
    }

    /**
     * @return
     */
    @Override
    public Fields[] getFieldsOnly() {
        Fields[] fields = new Fields[5];
        fields[0] = new Fields("order_id");
        fields[1] = new Fields("client_id");
        fields[2] = new Fields("date");
        fields[3] = new Fields("price");
        fields[4] = new Fields("order_quantity");
        return fields;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", client_id=" + client_id + ", date=" + date + ", price=" + price + ", order_quantity=" + order_quantity + '}';
    }

    /**
     * @param rs
     * @return
     */
    @Override
    public Model addRow(ResultSet rs) {
        try {
            order_id = rs.getInt("order_id");
            client_id = Integer.parseInt(rs.getString("client_id"));
            date = rs.getString("date");
            price = Float.parseFloat(rs.getString("price"));
            order_quantity = Integer.parseInt(rs.getString("order_quantity"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
