package org.example;

import java.sql.ResultSet;
import java.util.Map;

public interface Model {
    Map<String, String> getFields();

    Fields[] getValues();

    Fields[] getFieldsOnly();

    @Override
    String toString();

    Model addRow(ResultSet rs);
}
