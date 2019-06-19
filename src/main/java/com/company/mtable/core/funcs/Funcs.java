package com.company.mtable.core.funcs;

import com.company.mtable.core.AggragateFunc;
import com.company.mtable.core.SimpleFunc;

/**
 * Created by jxwr on 2019/6/19.
 */
public interface Funcs {
    SimpleFunc Mod = new Mod();

    SimpleFunc Mul = new Mul();

    AggragateFunc Sum = new Sum();

    AggragateFunc Count = new Count();

    AggragateFunc Avg = new Avg();

    AggragateFunc Min = new Min();

    AggragateFunc Max = new Max();
}
