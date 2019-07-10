package com.company.subject;

public class InternalSubject implements Subject {

    public InternalSubject(int type, long value1, long value2, long value3, long value4) {
        this.type = type;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    private int type;
    private long value1;
    private long value2;
    private long value3;
    private long value4;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getValue1() {
        return value1;
    }

    public void setValue1(long value1) {
        this.value1 = value1;
    }

    public long getValue2() {
        return value2;
    }

    public void setValue2(long value2) {
        this.value2 = value2;
    }

    public long getValue3() {
        return value3;
    }

    public void setValue3(long value3) {
        this.value3 = value3;
    }

    public long getValue4() {
        return value4;
    }

    public void setValue4(long value4) {
        this.value4 = value4;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append('(')
                .append(type).append(',')
                .append(value1).append(',')
                .append(value2).append(',')
                .append(value3).append(',')
                .append(value4)
                .append(')')
                .toString();
    }
}