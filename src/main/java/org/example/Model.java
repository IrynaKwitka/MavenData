package org.example;

import java.sql.ResultSet;
import java.util.Map;

public interface Model {

    String tableName = null;
    String getTableName();

    Map<String, String> getColumns();

    Fields[] getValues();

    Fields[] getFieldsOnly();

    @Override
    String toString();

    Model addRow(ResultSet rs);
}
