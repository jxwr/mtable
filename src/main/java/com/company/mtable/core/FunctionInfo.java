package com.company.mtable.core;

import com.company.mtable.core.types.DataType;
import com.company.mtable.core.types.Types;
import com.company.mtable.schema.Column;

import javax.xml.crypto.Data;
import java.lang.reflect.Method;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxwr on 2019/6/23.
 */
public class FunctionInfo {

    private Class funcClass;

    private DataType dataType;
    private List<DataType> inputTypes;
    private boolean isAggregate;

    private FunctionInfo(Class funcClass, DataType dataType, List<DataType> inputTypes, boolean isAggregate) {
        this.funcClass = funcClass;
        this.dataType = dataType;
        this.inputTypes = inputTypes;
        this.isAggregate = isAggregate;
    }

    public static FunctionInfo from(Class funcClass) {
        DataType dataType = returnType(funcClass);
        List<DataType> inputTypes = getInputTypes(funcClass);
        boolean isAggregate = isAggregateFunction(funcClass);

        return new FunctionInfo(funcClass, dataType, inputTypes, isAggregate);
    }

    public String name() {
        return this.funcClass.getSimpleName().toLowerCase();
    }

    public DataType dataType() {
        return this.dataType;
    }

    public List<DataType> inputTypes() {
        return this.inputTypes;
    }

    private static boolean isAggregateFunction(Class funcClass) {
        Method[] methods = funcClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("finish")) {
                return true;
            }
        }
        return false;
    }

    private static DataType returnType(Class funcClass) {
        Method[] methods = funcClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("call") || method.getName().equals("finish")) {
                Class returnType = method.getReturnType();
                return Types.fromClass(returnType);
            }
        }
        return null;
    }

    private static List<DataType> getInputTypes(Class funcClass) {
        List<DataType> types = new ArrayList<>();
        Method[] methods = funcClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("call") || method.getName().equals("handle")) {
                Class<?>[] typeClasses = method.getParameterTypes();
                for (int i = 0; i < typeClasses.length; i++) {
                    Class<?> typeClass = typeClasses[i];
                    types.add(Types.fromClass(typeClass));
                }
                break;
            }
        }
        return types;
    }

    private String makeErrorMessage(int i, DataType left, DataType right) {
        return "Expected " + right.typeName() + " at " + i + ", got " + left.typeName();
    }

    public String checkInputTypes(List<Object> params) {
        for (int i = 0; i < this.inputTypes.size(); i++) {
            Object param = params.get(i);
            DataType rightType = this.inputTypes.get(i);
            if (param instanceof Column) {
                DataType leftType = ((Column)param).getType();
                if (!rightType.acceptsType(leftType)) {
                    return makeErrorMessage(i, leftType, rightType);
                }
            } else {
                DataType leftType = Types.fromClass(param.getClass());
                if (!rightType.acceptsType(leftType)) {
                    return makeErrorMessage(i, leftType, rightType);
                }
            }
        }
        return null;
    }

    public Object func() {
        try {
            return funcClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAggregateFunction() {
        return isAggregate;
    }
}
