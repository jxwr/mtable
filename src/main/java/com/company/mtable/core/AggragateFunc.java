package com.company.mtable.core;

public abstract class AggragateFunc implements Func {
    @Override
    public boolean isAggr() {
        return true;
    }

    public abstract void init();

    public abstract boolean handle(Object[] params);

    public abstract Object finish();

    public abstract AggragateFunc copy();
}
