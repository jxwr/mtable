package com.company.mtable.ql;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jxwr on 2019/6/16.
 */
public class DictData {
    private Map<Object, Object> map;

    private Set<FieldData> namedFields;

    public DictData() {
        this.map = new HashMap<>();
        namedFields = new HashSet<>();
    }

    public DictData getOrCreateDict(Object name) {
        Object obj = this.map.get(name);
        if (obj == null) {
            obj = new DictData();
            this.map.put(name, obj);
        }
        return (DictData) obj;
    }

    public ArrayData getOrCreateArray(Object name) {
        Object obj = this.map.get(name);
        if (obj == null) {
            obj = new ArrayData();
            this.map.put(name, obj);
        }
        return (ArrayData) obj;
    }

    public void putFieldData(String name, Object value) {
        this.map.put(name, value);
        namedFields.add(new FieldData(name, value));
    }

    public Map<Object, Object> toMap() {
        Map<Object, Object> resultMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : this.map.entrySet()) {
            if (entry.getValue() instanceof DictData) {
                resultMap.put(entry.getKey(), ((DictData) entry.getValue()).toMap());
            } else if (entry.getValue() instanceof ArrayData) {
                resultMap.put(entry.getKey(), ((ArrayData) entry.getValue())
                                .getArray().stream().map(DictData::toMap).collect(Collectors.toList()));
            } else {
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        return resultMap;
    }

    @Override
    public boolean equals(Object that) {
        return this.namedFields.equals(((DictData)that).namedFields);
    }

    @Override
    public int hashCode() {
        return namedFields.hashCode();
    }
}
