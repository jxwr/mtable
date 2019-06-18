package com.company.mtable.core;

import org.codehaus.janino.ClassBodyEvaluator;

import java.io.StringReader;

/**
 * Created by jxwr on 2019/6/16.
 */
public class Compiler {

    public Scanner compileAndLoadScanner(String code) {
        ClassBodyEvaluator evaluator = new ClassBodyEvaluator();

        evaluator.setParentClassLoader(this.getClass().getClassLoader());
        evaluator.setClassName("com.company.mtable.udf.GeneratedClass");
        evaluator.setExtendedType(Scanner.class);
        evaluator.setDefaultImports(new String[]{
                "com.company.mtable.core.*",
                "com.company.mtable.schema.*"
        });

        try {
            evaluator.cook("generated.java", new StringReader(code));
            Scanner obj = (Scanner) evaluator.getClazz().newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
