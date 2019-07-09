package com.company.mtable.schema;

import com.company.mtable.core.IndexValue;
import com.company.mtable.core.Record;
import com.company.mtable.core.annotation.MData;
import com.company.mtable.core.types.DataType;
import com.company.mtable.core.types.Types;

import java.lang.reflect.Field;
import java.util.*;

public class Schema {

    private String tableName;
    private Map<Integer, Column> columnById = new HashMap<>();
    private Map<String, Column> columnByName = new HashMap<>();
    private List<Column> columns = new ArrayList<>();
    private Column partitionColumn;
    private Column[] uniqueIndexColumns;
    private int[] uniqueIndexCids;

    // 仅用JavaClass定义Schema时使用
    private Class<?> recordClass;

    public Schema(String tableName) {
        this.tableName = tableName;
    }

    public Schema addColumn(String name, DataType type) {
        if (!type.isConcreteType())
            throw new RuntimeException("Column type is not concrete");

        Column col = new Column(columns.size(), name, type);
        columnById.put(columns.size(), col);
        columnByName.put(name, col);
        columns.add(col);
        return this;
    }

    public Column getPartitionColumn() {
        return partitionColumn;
    }

    public Schema setPartitionKey(String partitionKey) {
        this.partitionColumn = columnByName.get(partitionKey);
        return this;
    }

    /**
     * TODO: 正经抛异常
     * @param tableName
     * @param klass
     * @return
     */
    public static Schema fromClass(String tableName, Class<?> klass) {
        if (!klass.isAnnotationPresent(MData.class)) {
            throw new RuntimeException("MData annotation not present");
        }

        String partitionField = klass.getAnnotation(MData.class).partitionField();
        String[] uniqueIndexFields = klass.getAnnotation(MData.class).uniqueIndexFields();

        Schema schema = new Schema(tableName);
        Field[] fields = klass.getDeclaredFields();

        for (String annoField : uniqueIndexFields) {
            if (Arrays.stream(fields).noneMatch(f -> f.getName().equals(annoField))) {
                throw new RuntimeException("Field name " + annoField + " not exists.");
            }
        }
        if (Arrays.stream(fields).noneMatch(f -> f.getName().equals(partitionField))) {
            throw new RuntimeException("Field name " + partitionField + " not exists.");
        }

        for (Field field : fields) {
            String name = field.getName();
            Class<?> javaType = field.getType();
            DataType dataType = Types.fromJavaType(javaType);
            if (!dataType.isConcreteType())
                throw new RuntimeException("Column type is not concrete");
            schema.addColumn(name, dataType);
        }

        schema.setUniqueIndexKeys(uniqueIndexFields);
        schema.setPartitionKey(partitionField);
        schema.setRecordClass(klass);
        return schema;
    }

    public Column[] getUniqueIndexColumns() {
        return uniqueIndexColumns;
    }

    private void setUniqueIndexKeys(String[] uniqueIndexFields) {
        setUniqueIndexKeys(Arrays.asList(uniqueIndexFields));
    }

    public void setUniqueIndexKeys(List<String> uniqueIndexKeys) {
        int count = uniqueIndexKeys.size();
        this.uniqueIndexColumns = new Column[count];
        this.uniqueIndexCids = new int[count];

        for (int i = 0; i < count; i++) {
            String key = uniqueIndexKeys.get(i);
            Column col = columnByName.get(key);
            uniqueIndexColumns[i] = col;
            uniqueIndexCids[i] = col.getCid();
        }
    }

    public IndexValue newIndexValue() {
        return new IndexValue(new Object[uniqueIndexCids.length]);
    }

    public int cid(String name) {
        return columnByName.get(name).getCid();
    }

    public Column column(int cid) {
        return columnById.get(cid);
    }

    public Column column(String cname) {
        return columnByName.get(cname);
    }

    public String getTableName() {
        return tableName;
    }

    public int[] getUniqueIndexCids() {
        return this.uniqueIndexCids;
    }

    public List<Column> getColumns() {
        return columns;
    }


    public Class<?> getRecordClass() {
        return recordClass;
    }

    public void setRecordClass(Class<?> recordClass) {
        this.recordClass = recordClass;
    }

    public Record toRecord(Object object) {
        if (object.getClass() != getRecordClass()) {
            throw new RuntimeException("Schema not match.");
        }

        try {
            Record record = Record.newRecord(this);
            for (Column col : this.getColumns()) {
                Field field = object.getClass().getDeclaredField(col.getName());
                Object value = field.get(object);
                record.set(col, value);
            }
            return record;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}
