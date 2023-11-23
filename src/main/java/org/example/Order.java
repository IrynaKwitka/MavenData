package org.example;

import java.util.Date;

public class Order {
        private int order_id;
        private int client_id;
        private Date data;
        private float price;
        private int order_quantity;

        public Order (int order_id, int client_id, Date data, float price,int order_quantity) {
            this.order_id = order_id;
            this.client_id = client_id;
            this.data = data;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", client_id=" + client_id + ", data=" + data + ", price=" + price + ", order_quantity=" + order_quantity + '}';
    }
}
