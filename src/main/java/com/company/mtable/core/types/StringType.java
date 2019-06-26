package com.company.mtable.core.types;

/**
 * Created by jxwr on 2019/6/19.
 */
public class StringType extends AbstractDataType {
    @Override
    public String typeName() {
        return "String";
    }

    @Override
    public Class<?> typeClass() {
        return String.class;
    }

    @Override
    public boolean acceptsType(DataType type) {
        return true;
    }

    @Override
    public Object value(Object value) {
        return String.valueOf(value);
    }

    @Override
    public boolean acceptsValue(Object value) {
        return true;
    }

    @Override
    public Object minValue() {
        return "";
    }

    /**
     * TODO：字符串比较怎么处理？
     * @return
     */
    @Override
    public Object maxValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isConcreteType() {
        return true;
    }
}
