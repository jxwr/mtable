package com.company.mtable.core;

import com.company.mtable.exception.UDFCompileException;
import com.company.mtable.core.fn.*;
import org.codehaus.janino.*;
import org.codehaus.janino.Scanner;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by jxwr on 2019/6/16.
 */
public class Compiler {

    public static Class compile(String name, String code, Class iface) throws UDFCompileException {
        ClassBodyEvaluator evaluator = new ClassBodyEvaluator();

        evaluator.setParentClassLoader(Compiler.class.getClassLoader());
        evaluator.setClassName("com.company.mtable.udf.GeneratedClass");
        evaluator.setImplementedTypes(new Class[]{iface});
        evaluator.setDefaultImports(new String[]{
                "com.company.mtable.core.*",
                "com.company.mtable.schema.*"
        });

        try {
            evaluator.cook("generated.java", new StringReader(code));
            return evaluator.getClazz();
        } catch (Exception e) {
            throw new UDFCompileException(e);
        }
    }
}
