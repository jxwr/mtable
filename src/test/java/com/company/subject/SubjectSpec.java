package com.company.subject;

/**
 * 定义合法主体类型组合
 */
public class SubjectSpec {
    private Class<? extends Id> id1;
    private Class<? extends Id> id2;
    private Class<? extends Id> id3;
    private Class<? extends Id> id4;

    public SubjectSpec(Class<? extends Id> id1) {
        this.id1 = id1;
    }

    public SubjectSpec(Class<? extends Id> id1, Class<? extends Id> id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    public SubjectSpec(Class<? extends Id> id1, Class<? extends Id> id2, Class<? extends Id> id3) {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
    }

    public SubjectSpec(Class<? extends Id> id1, Class<? extends Id> id2, Class<? extends Id> id3, Class<? extends Id> id4) {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
        this.id4 = id4;
    }

    @Override
    public int hashCode() {
        int hash = id1.hashCode();
        if (id2 != null) {
            hash = 31 * hash + id2.hashCode();
        }
        if (id3 != null) {
            hash = 31 * hash + id3.hashCode();
        }
        if (id4 != null) {
            hash = 31 * hash + id4.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        SubjectSpec that = (SubjectSpec)o;
        return this.id1 == that.id1 && this.id2 == that.id2 && this.id3 == that.id3 && this.id4 == that.id4;
    }
}
