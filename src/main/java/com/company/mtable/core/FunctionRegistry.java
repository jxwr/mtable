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
        functions.put("avg", FunctionInfo.from(Avg.class));
        functions.put("count", FunctionInfo.from(Count.class));
        functions.put("max", FunctionInfo.from(Max.class));
        functions.put("min", FunctionInfo.from(Min.class));
        functions.put("mul", FunctionInfo.from(Mul.class));
        functions.put("mod", FunctionInfo.from(Mod.class));
        functions.put("sum", FunctionInfo.from(Sum.class));
    }

    public static FunctionInfo get(String name) {
        return functions.get(name);
    }

    public static FunctionInfo register(String name, Class funcClass) {
        return functions.put(name, FunctionInfo.from(funcClass));
    }
}
