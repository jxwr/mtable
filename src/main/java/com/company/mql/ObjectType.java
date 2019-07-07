package com.company.mql;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jxwr on 2019/7/7.
 */
public class ObjectType implements Type {

    private final String name;
    private Map<String, FieldDefinition> fieldsByName = new HashMap<>();

    public ObjectType(String name) {
        this.name = name;
    }

    public ObjectType addField(FieldDefinition field) {
        fieldsByName.put(field.getName(), field);
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    FieldDefinition getField(String name) {
        return fieldsByName.get(name);
    }
}
