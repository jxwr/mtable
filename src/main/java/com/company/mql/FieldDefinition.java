package com.company.mql;

import java.util.List;

/**
 * Created by jxwr on 2019/7/7.
 */
public class FieldDefinition {

    private final String name;
    private final List<Argument> args;
    private final Type type;
    private final boolean required;
    private DataFetcher dataFetcher;

    public FieldDefinition(String name, List<Argument> args, Type type, boolean required) {
        this.name = name;
        this.args = args;
        this.type = type;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public List<Argument> getArgs() {
        return args;
    }

    public Type getType() {
        return type;
    }

    public boolean isRequired() {
        return required;
    }

    public DataFetcher getDataFetcher() {
        return dataFetcher;
    }

    public void setDataFetcher(DataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }
}
