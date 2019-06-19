package com.company.mtable.core;

public abstract class AggragateFunc implements Func {
    @Override
    public boolean isAggr() {
        return true;
    }
}
