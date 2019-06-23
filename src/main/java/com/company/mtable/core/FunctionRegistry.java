package com.company.mtable.core;

import com.company.mtable.core.functions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jxwr on 2019/6/23.
 */
public class FunctionRegistry {

    static Map<String, FunctionInfo> functions = new HashMap<>();

    static {
        functions.put("avg", new FunctionInfo(Avg.class));
        functions.put("count", new FunctionInfo(Count.class));
        functions.put("max", new FunctionInfo(Max.class));
        functions.put("min", new FunctionInfo(Min.class));
        functions.put("mul", new FunctionInfo(Mul.class));
        functions.put("mod", new FunctionInfo(Mod.class));
        functions.put("sum", new FunctionInfo(Sum.class));
        functions.put("tuple_add", new FunctionInfo(TupleAdd.class));
    }

    public static FunctionInfo get(String name) {
        return functions.get(name);
    }
}
