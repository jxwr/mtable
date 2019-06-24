package com.company.mtable.schema;

import com.company.mtable.core.IndexValue;
import com.company.mtable.core.types.DataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schema {

    private String tableName;
    private Map<Integer, Column> columnIdMap = new HashMap<>();
    private Map<String, Column> columnNameMap = new HashMap<>();
    private List<Column> columns = new ArrayList<>();
    private Column partitionColumn;
    private Column[] uniqueIndexColumns;
    private int[] uniqueIndexCids;

    public Schema(String tableName) {
        this.tableName = tableName;
    }

    public Schema addColumn(String name, DataType type) {
        Column col = new Column(columns.size(), name, type);
        columnIdMap.put(columns.size(), col);
        columnNameMap.put(name, col);
        columns.add(col);
        return this;
    }

    public Column getPartitionColumn() {
        return partitionColumn;
    }

    public Schema setPartitionKey(String partitionKey) {
        this.partitionColumn = columnNameMap.get(partitionKey);
        return this;
    }

    public Column[] getUniqueIndexColumns() {
        return uniqueIndexColumns;
    }

    public void setUniqueIndexKeys(List<String> uniqueIndexKeys) {
        int count = uniqueIndexKeys.size();
        this.uniqueIndexColumns = new Column[count];
        this.uniqueIndexCids = new int[count];

        for (int i = 0; i < count; i++) {
            String key = uniqueIndexKeys.get(i);
            Column col = columnNameMap.get(key);
            uniqueIndexColumns[i] = col;
            uniqueIndexCids[i] = col.getCid();
        }
    }

    public IndexValue newIndexValue() {
        return new IndexValue(new Object[uniqueIndexCids.length]);
    }

    public int cid(String name) {
        return columnNameMap.get(name).getCid();
    }

    public Column column(int cid) {
        return columnIdMap.get(cid);
    }

    public Column column(String cname) {
        return columnNameMap.get(cname);
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
}
