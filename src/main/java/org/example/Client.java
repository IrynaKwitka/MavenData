package org.example;

import java.util.HashMap;
import java.util.Map;

public class Client {

    private int client_id;
    private String name;
    private String surname;
    private String email;
    private String registration_date;
    private String tel_number;

    public Client(String name, String surname, String email, String registration_date, String tel_number) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.registration_date = registration_date;
        this.tel_number = tel_number;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public static Map<String, String> getFields() {
        Map<String, String> fields = new HashMap<>(6);
        fields.put("client_id", "serial PRIMARY KEY");
        fields.put("name", "varchar(255) DEFAULT NULL");
        fields.put("surname", "varchar(255) DEFAULT NULL");
        fields.put("email", "varchar(255) DEFAULT NULL");
        fields.put("tel_number", "varchar(255) DEFAULT NULL");
        fields.put("registration_date", "date");
        return fields;
    }

    public Fields[] getValues() {
        Fields[] fields;
        fields = new Fields[6];
        fields[1] = new Fields("name", "String", name);
        fields[2] = new Fields("surname", "String", surname);
        fields[3] = new Fields("email", "String", email);
        fields[4] = new Fields("tel_number", "String", tel_number);
        fields[5] = new Fields("registration_date", "String", registration_date);
        return fields;
    }
    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", registration_date='" + registration_date + '\'' +
                ", tel_number='" + tel_number + '\'' +
                '}';
    }
}