package com.company.mtable;

import com.company.mtable.schema.Schema;

import java.util.HashMap;
import java.util.Map;

public class MTable {

    private Map<String, Table> tableByName = new HashMap<>();

    public Table createTable(Schema schema) {
        Table table = new Table(schema);
        tableByName.put(schema.getTableName(), table);
        return table;
    }

    public Table createTable(String name, Class<?> klass) {
        Schema schema = Schema.fromClass(name, klass);
        return createTable(schema);
    }

    public Table getTable(String name) {
        return tableByName.get(name);
    }
}
