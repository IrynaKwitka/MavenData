package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Product implements Model {
    private int product_id;
    private String product_name;
    private String product_description;
    private float product_price;
    private int product_quantity;

    public static String tableName = "products";

    public Product() {
    }
    public Product(String product_name, float product_price, int product_quantity, String product_description) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_description = product_description;
    }
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    /**
     * @return
     */
    @Override
    public String getTableName() {
        return tableName;
    }

    /**
     * @return
     */
    @Override
    public Map<String, String> getColumns() {
        Map<String, String> fields = new HashMap<>(5);
        fields.put("product_id", "serial PRIMARY KEY");
        fields.put("product_name", "varchar(255) DEFAULT NULL");
        fields.put("product_description", "varchar(255) DEFAULT NULL");
        fields.put("product_price", "float DEFAULT NULL");
        fields.put("product_quantity", "int DEFAULT NULL");
        return fields;
    }
    /**
     * @return
     */
    @Override
    public Fields[] getValues() {
        Fields[] fields = new Fields[4];
        fields[0] = new Fields("product_name", "String", product_name);
        fields[1] = new Fields("product_description", "String", product_description);
        fields[2] = new Fields("product_price", "float", String.valueOf(product_price));
        fields[3] = new Fields("product_quantity", "int", String.valueOf(product_quantity));
        return fields;
    }

    /**
     * @return
     */
    @Override
    public Fields[] getFieldsOnly() {
        Fields[] fields = new Fields[5];
        fields[0] = new Fields("product_id");
        fields[1] = new Fields("product_name");
        fields[2] = new Fields("product_description");
        fields[3] = new Fields("product_price");
        fields[4] = new Fields("product_quantity");
        return fields;
    }

    @Override
    public String getSelectString() {
        return "product_id, product_name, product_description, product_price, product_quantity";
    }

    @Override
    public String toString() {
        return "Product {" + "product_id=" + product_id + ", product_name=" + product_name + ", product_description=" + product_description + ", product_price=" + product_price + ", product_quantity=" + product_quantity + '}';
    }

    /**
     * @param rs
     * @return
     */
    @Override
    public Model addRow(ResultSet rs) {
        try {
            product_id = rs.getInt("product_id");
            product_name = rs.getString("product_name");
            product_description = rs.getString("product_description");
            product_price = rs.getFloat("product_price");
            product_quantity = rs.getInt("product_quantity");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
