package com.company.subject;

public class SubjectType {
    private byte type;

    public SubjectType(byte type) {
        this.type = type;
    }

    byte value() {
        return this.type;
    }
}
