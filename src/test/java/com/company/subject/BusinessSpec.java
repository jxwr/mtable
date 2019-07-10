package com.company.subject;

import java.util.*;

public class BusinessSpec {

    private Map<Class<? extends Id>, Integer> subjectIdBits = new HashMap<>();

    private Set<SubjectSpec> validSubjectSpecs = new HashSet<>();

    public Set<SubjectSpec> getValidSubjectSpecs() {
        return validSubjectSpecs;
    }

    private void addSubjectSpec(SubjectSpec def) {
        validSubjectSpecs.add(def);
    }

    public <T1 extends Id>
    TypeSignature1<T1>  declareValidSubjectSpec(Class<T1> id1) {
        addSubjectSpec(new SubjectSpec(id1));
        return new TypeSignature1<>();
    }

    public <T1 extends Id, T2 extends Id>
    TypeSignature2<T1, T2> declareValidSubjectSpec(Class<T1> id1, Class<T2> id2) {
        addSubjectSpec(new SubjectSpec(id1, id2));
        return new TypeSignature2<>();
    }

    public <T1 extends Id, T2 extends Id, T3 extends Id>
    TypeSignature3<T1, T2, T3> declareValidSubjectSpec(Class<T1> id1, Class<T2> id2, Class<T3> id3) {
        addSubjectSpec(new SubjectSpec(id1, id2, id3));
        return new TypeSignature3<>();
    }

    public <T1 extends Id, T2 extends Id, T3 extends Id, T4 extends Id>
    TypeSignature4<T1, T2, T3, T4> declareValidSubjectSpec(Class<T1> id1, Class<T2> id2, Class<T3> id3, Class<T4> id4) {
        addSubjectSpec(new SubjectSpec(id1, id2, id3, id4));
        return new TypeSignature4<>();
    }

    public void checkSubjectSpec(Class<? extends Id> id1) {
        if (!validSubjectSpecs.contains(new SubjectSpec(id1)))
            throw new RuntimeException("Invalid subject arguments");
    }

    public void checkSubjectSpec(Class<? extends Id> id1, Class<? extends Id> id2) {
        if (!validSubjectSpecs.contains(new SubjectSpec(id1, id2)))
            throw new RuntimeException("Invalid subject arguments");
    }

    public void checkSubjectSpec(Class<? extends Id> id1, Class<? extends Id> id2, Class<? extends Id> id3) {
        if (!validSubjectSpecs.contains(new SubjectSpec(id1, id2, id3)))
            throw new RuntimeException("Invalid subject arguments");
    }

    public void checkSubjectSpec(Class<? extends Id> id1, Class<? extends Id> id2, Class<? extends Id> id3, Class<? extends Id> id4) {
        if (!validSubjectSpecs.contains(new SubjectSpec(id1, id2, id3, id4)))
            throw new RuntimeException("Invalid subject arguments");
    }

    public void declareIdBitInSubjectType(Class<? extends Id> idClass, int subjectIdBit) {
        subjectIdBits.put(idClass, subjectIdBit);
    }

    public int bitInSubjectType(Class<? extends Id> idClass) {
        Integer val = subjectIdBits.get(idClass);
        if (val == null)
            throw new RuntimeException("bit in subject type not declared.");
        return val;
    }

    private static BusinessSpec instance = null;

    public static BusinessSpec getInstance() {
        if (instance == null)
            instance = new BusinessSpec();
        return instance;
    }
}

