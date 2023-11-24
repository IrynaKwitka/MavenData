package org.example;

public class Fields {
    private String name;
    private String type;

    private String value;

    public Fields(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public Fields() {
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}