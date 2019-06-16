package com.company.mtable.ql;

/**
 * Created by jxwr on 2019/6/16.
 */
public class FieldData {
    public FieldData(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    private String name;

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    private Object value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldData fieldData = (FieldData) o;

        if (name != null ? !name.equals(fieldData.name) : fieldData.name != null) return false;
        return value != null ? value.equals(fieldData.value) : fieldData.value == null;
    }
}
