package com.company.mtable.core;

import com.company.mtable.core.types.DataType;
import com.company.mtable.core.types.Types;
import com.company.mtable.schema.Column;

import java.lang.reflect.Method;
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

    public FunctionInfo(Class funcClass) {
        this.funcClass = funcClass;
        this.dataType = returnType();
        this.inputTypes = cacheInputTypes(funcClass);
        this.isAggregate = cacheIsAggregateFunction();
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

    private boolean cacheIsAggregateFunction() {
        Method[] methods = funcClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("finish")) {
                return true;
            }
        }
        return false;
    }

    private DataType returnType() {
        Method[] methods = funcClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("call") || method.getName().equals("finish")) {
                Class returnType = method.getReturnType();
                System.out.println(method.getName() + " =====> " + returnType);
                return Types.fromClass(returnType);
            }
        }
        return null;
    }

    private List<DataType> cacheInputTypes(Class funcClass) {
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

    private boolean checkInputTypes(List<Object> params) {
        for (int i = 0; i < this.inputTypes.size(); i++) {
            Object param = params.get(i);
            DataType rightType = this.inputTypes.get(i);
            if (param instanceof Column) {
                DataType leftType = ((Column)param).getType();
                if (!leftType.equals(rightType)) {
                    return false;
                }
            } else {
                DataType leftType = Types.fromClass(param.getClass());
                if (!leftType.equals(rightType)) {
                    return false;
                }
            }
        }
        return true;
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
