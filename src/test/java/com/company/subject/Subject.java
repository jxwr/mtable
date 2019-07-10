package com.company.subject;

import com.company.subject.*;

public interface Subject {

    static Subject of(SubjectType type, Id id1) {
        int typeValue = type.value() | id1.bit();

        return new InternalSubject(typeValue, id1.value(), 0, 0, 0);
    }

    static Subject of(SubjectType type, Id id1, Id id2) {
        BusinessSpec.getInstance().checkSubjectSpec(id1.getClass(), id2.getClass());

        int typeValue = type.value() | id1.bit() | id2.bit();

        return new InternalSubject(typeValue, id1.value(), id2.value(), 0, 0);
    }

    static Subject of(SubjectType type, Id id1, Id id2, Id id3) {
        BusinessSpec.getInstance().checkSubjectSpec(id1.getClass(), id2.getClass(), id3.getClass());

        int typeValue = type.value() | id1.bit() | id2.bit() | id3.bit();

        return new InternalSubject(typeValue, id1.value(), id2.value(), id3.value(), 0);
    }

    static Subject of(SubjectType type, Id id1, Id id2, Id id3, Id id4) {
        BusinessSpec.getInstance().checkSubjectSpec(id1.getClass(), id2.getClass(), id3.getClass(), id4.getClass());

        int typeValue = type.value() | id1.bit() | id2.bit() | id3.bit() | id4.bit();

        return new InternalSubject(typeValue, id1.value(), id2.value(), id3.value(), id4.value());
    }

    static Subject of(Id id1) {
        int typeValue = id1.bit();
        return new InternalSubject(typeValue, id1.value(), 0, 0, 0);
    }

    static Subject of(Id id1, Id id2) {
        BusinessSpec.getInstance().checkSubjectSpec(id1.getClass(), id2.getClass());

        int typeValue = id1.bit() | id2.bit();

        return new InternalSubject(typeValue, id1.value(), id2.value(), 0, 0);
    }

    static Subject of(Id id1, Id id2, Id id3) {
        BusinessSpec.getInstance().checkSubjectSpec(id1.getClass(), id2.getClass(), id3.getClass());

        int typeValue = id1.bit() | id2.bit() | id3.bit();

        return new InternalSubject(typeValue, id1.value(), id2.value(), id3.value(), 0);
    }

    static Subject of(Id id1, Id id2, Id id3, Id id4) {
        BusinessSpec.getInstance().checkSubjectSpec(id1.getClass(), id2.getClass(), id3.getClass(), id4.getClass());

        int typeValue = id1.bit() | id2.bit() | id3.bit() | id4.bit();

        return new InternalSubject(typeValue, id1.value(), id2.value(), id3.value(), id4.value());
    }

    // 类型安全接口
    static <T1 extends Id>
    Subject of1(TypeSignature1<T1> sig, SubjectType type, T1 id1) {
        return of(type, id1);
    }

    // 类型安全接口
    static <T1 extends Id, T2 extends Id>
    Subject of(TypeSignature2<T1, T2> sig, SubjectType type, T1 id1, T2 id2) {
        return of(type, id1, id2);
    }

    // 类型安全接口
    static <T1 extends Id, T2 extends Id, T3 extends Id>
    Subject of(TypeSignature3<T1, T2, T3> sig, SubjectType type, T1 id1, T2 id2, T3 id3) {
        return of(type, id1, id2, id3);
    }

    // 类型安全接口
    static <T1 extends Id, T2 extends Id, T3 extends Id, T4 extends Id>
    Subject of(TypeSignature4<T1, T2, T3, T4> sig, SubjectType type, T1 id1, T2 id2, T3 id3, T4 id4) {
        return of(type, id1, id2, id3, id4);
    }

    static <T1 extends Id>
    Subject of(TypeSignature1<T1> sig, T1 id1) {
        return of(id1);
    }

    static <T1 extends Id, T2 extends Id>
    Subject of(TypeSignature2<T1, T2> sig, T1 id1, T2 id2) {
        return of(id1, id2);
    }

    static <T1 extends Id, T2 extends Id, T3 extends Id>
    Subject of(TypeSignature3<T1, T2, T3> sig, T1 id1, T2 id2, T3 id3) {
        return of(id1, id2, id3);
    }

    static <T1 extends Id, T2 extends Id, T3 extends Id, T4 extends Id>
    Subject of(TypeSignature4<T1, T2, T3, T4> sig, T1 id1, T2 id2, T3 id3, T4 id4) {
        return of(id1, id2, id3, id4);
    }
}
