package com.company.mtable;

import com.company.mtable.core.types.DataType;
import com.company.mtable.core.types.Types;
import com.company.mtable.schema.Column;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
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
        DataType dataType = getReturnType(funcClass);
        List<DataType> inputTypes = getInputTypes(funcClass);
        boolean isAggregate = isAggregateFunction(funcClass);

        return new FunctionInfo(funcClass, dataType, inputTypes, isAggregate);
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

    private static DataType getReturnType(Class funcClass) {
        Method[] methods = funcClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("call") || method.getName().equals("finish")) {
                Class returnType = method.getReturnType();
                return Types.fromJavaType(returnType);
            }
        }
        return null;
    }

    /**
     * 不太灵，很多类型直接传成了Object，即变成AnyType了
     * @param funcClass
     * @return
     */
    private static List<DataType> getInputTypes(Class funcClass) {
        List<DataType> types = new ArrayList<>();
        Method[] methods = funcClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("call") || method.getName().equals("handle")) {
                Class<?>[] typeClasses = method.getParameterTypes();
                for (int i = 0; i < typeClasses.length; i++) {
                    Class<?> typeClass = typeClasses[i];
                    types.add(Types.fromJavaType(typeClass));
                }
                break;
            }
        }
        return types;
    }

    public static List<DataType> paramsTypes(List<Object> params) {
        List<DataType> types = new ArrayList<>(params.size());
        for (Object param : params) {
            DataType type;
            if (param instanceof Column) {
                type = ((Column) param).getType();
            } else if (param == FunctionCall.PARAM_RECORD) {
                type = Types.RecordType;
            } else {
                type = Types.fromJavaType(param.getClass());
            }
            types.add(type);
        }
        return types;
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

    public String checkInputTypes(List<Object> params) {
        for (int i = 0; i < this.inputTypes.size(); i++) {
            Object param = params.get(i);
            DataType leftType = null;
            DataType rightType = this.inputTypes.get(i);

            if (param instanceof Column) {
                leftType = ((Column) param).getType();
            } else if (param == FunctionCall.PARAM_RECORD) {
                leftType = Types.RecordType;
            } else {
                leftType = Types.fromJavaType(param.getClass());
            }
            System.out.println("==>" + leftType + " ---> " + rightType);
            if (!rightType.acceptsType(leftType)) {
                return makeErrorMessage(i, leftType, rightType);
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

    private String makeErrorMessage(int i, DataType left, DataType right) {
        return "Expected " + right.typeName() + " at " + i + ", got " + left.typeName();
    }
}
