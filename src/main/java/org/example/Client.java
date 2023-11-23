package org.example;
public class Client {

    private int client_id;
    private String name;
    private String surname;
    private String email;
    private String registration_date;
    private String tel_number;

    public Client(int client_id, String name, String surname, String email, String registration_date, String tel_number) {
        this.client_id = client_id;
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