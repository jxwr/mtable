package com.company.mtable;

import com.company.mtable.core.Compiler;
import com.company.mtable.functions.*;
import com.company.mtable.core.ifn.*;
import com.company.mtable.exception.UDFCompileException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jxwr on 2019/6/23.
 */
public class FunctionRegistry {

    private static Map<String, FunctionInfo> functions = new HashMap<>();

    static {
        register("avg", Avg.class);
        register("count", Count.class);
        register("max", Max.class);
        register("min", Min.class);
        register("mul", Mul.class);
        register("mod", Mod.class);
        register("sum", Sum.class);
    }

    public static FunctionInfo get(String name) {
        return functions.get(name);
    }

    public static FunctionInfo register(String name, Class funcClass) {
        return functions.put(name, FunctionInfo.from(funcClass));
    }

    public static FunctionInfo registerUDF(String name, String iface, String code) throws UDFCompileException {
        Class funcClass = Compiler.compile(name, code, getFnIface(iface));
        return register(name, funcClass);
    }

    private static Class getFnIface(String name) throws UDFCompileException {
        switch (name) {
            case "Fn":
                return Fn.class;
            case "Fn0":
                return Fn0.class;
            case "Fn1":
                return Fn1.class;
            case "Fn2":
                return Fn2.class;
            case "Fn3":
                return Fn3.class;
            case "Fn4":
                return Fn4.class;
            case "Fn5":
                return Fn5.class;
            case "AFn":
                return AFn.class;
            case "AFn0":
                return AFn0.class;
            case "AFn1":
                return AFn1.class;
            case "AFn2":
                return AFn2.class;
            case "AFn3":
                return AFn3.class;
            case "AFn4":
                return AFn4.class;
            case "AFn5":
                return AFn5.class;
        }

        throw new UDFCompileException("Unknown interface type " + name);
    }
}
