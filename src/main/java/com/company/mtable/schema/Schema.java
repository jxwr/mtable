package com.company.mtable.schema;

import com.company.mtable.core.IndexValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schema {
    private String tableName;

    public Schema(String tableName) {
        this.tableName = tableName;
    }

    private Map<Integer, ColumnMeta> columnIdMap = new HashMap<>();

    private Map<String, ColumnMeta> columnNameMap = new HashMap<>();

    private List<ColumnMeta> columns = new ArrayList<>();

    private ColumnMeta partitionColumn;

    private ColumnMeta[] uniqueIndexColumns;

    private int[] uniqueIndexCids;

    private int uniqueIndexBufferSize = 0;

    public ColumnMeta addColumn(String name, Class<?> type) {
        ColumnMeta col = new ColumnMeta(columns.size(), name, type);
        columnIdMap.put(columns.size(), col);
        columnNameMap.put(name, col);
        columns.add(col);
        return col;
    }

    public ColumnMeta getPartitionColumn() {
        return partitionColumn;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionColumn = columnNameMap.get(partitionKey);
    }

    public ColumnMeta[] getUniqueIndexColumns() {
        return uniqueIndexColumns;
    }

    public void setUniqueIndexKeys(List<String> uniqueIndexKeys) {
        int count = uniqueIndexKeys.size();
        this.uniqueIndexColumns = new ColumnMeta[count];
        this.uniqueIndexCids = new int[count];

        for (int i = 0; i < count; i++) {
            String key = uniqueIndexKeys.get(i);
            ColumnMeta col = columnNameMap.get(key);
            uniqueIndexColumns[i] = col;
            uniqueIndexCids[i] = col.getCid();

            if (col.getType().equals(Integer.class)) {
                uniqueIndexBufferSize += 4;
            } else if (col.getType().equals(Long.class)) {
                uniqueIndexBufferSize += 8;
            } else if (col.getType().equals(Short.class)) {
                uniqueIndexBufferSize += 2;
            } else if (col.getType().equals(Byte.class)) {
                uniqueIndexBufferSize += 1;
            } else {
                throw new RuntimeException("Unsupported type for unique index.");
            }
        }
    }

    public IndexValue newIndexValue() {
        return new IndexValue(new Object[uniqueIndexCids.length]);
    }

    public int cid(String name) {
        return columnNameMap.get(name).getCid();
    }

    public ColumnMeta getColumn(int cid) {
        return columnIdMap.get(cid);
    }

    public ColumnMeta getColumn(String cname) {
        return columnNameMap.get(cname);
    }

    public String getTableName() {
        return tableName;
    }

    public int[] getUniqueIndexCids() {
        return this.uniqueIndexCids;
    }

    public List<ColumnMeta> getColumns() {
        return columns;
    }
}
