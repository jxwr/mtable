package com.company.mtable;

import com.company.mtable.core.ImmutableRecord;
import com.company.mtable.core.types.Types;
import com.company.mtable.schema.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxwr on 2019/6/16.
 */
public class ResultSet {

    private List<Column> columns;
    private List<ImmutableRecord> resultRecords;
    private boolean firstline = true;

    public ResultSet() {
        this.resultRecords = new ArrayList<>();
    }

    public List<Column> columns() {
        return columns;
    }

    public void addColumn(Column column) {
        if (this.columns == null) {
            this.columns = new ArrayList<>();
        }
        this.columns.add(column);
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void addResultRecord(ImmutableRecord resultRow) {
        this.resultRecords.add(resultRow);
    }

    public List<ImmutableRecord> resultRecords() {
        return resultRecords;
    }

    public int resultSize() {
        return resultRecords.size();
    }

    private int minRowLen(Column c) {
        int len = (c.getName().length() + 3);
        return len < 10 ? 10 : len;
    }

    public void printTable() {
        System.out.println("Results:");
        for (ImmutableRecord row : resultRecords()) {
            printRecord(row);
        }
    }

    public void printRecord(ImmutableRecord row) {
        if (row == null) return;

        List<Column> cs = columns();

        if (firstline) {
            printline(cs, '-');

            for (Column c : cs) {
                System.out.printf("%" + minRowLen(c) + "s|", c.getName() + '[' + Types.typeChar(c.getType()));
            }
            println();

            printline(cs, '|');
            firstline = false;
        }

        for (int cid = 0; cid < cs.size(); cid++) {
            System.out.printf("%" + minRowLen(cs.get(cid)) + "s|", row.get(cid) + "");
        }
        println();

        printline(cs, '|');
    }

    private void printline(List<Column> cs, char ch) {
        for (Column c : cs) {
            print(minRowLen(c), '-');
            print(1, ch);
        }
        System.out.println();
    }

    private void print(int n, char ch) {
        for (int i = 0; i < n; i++) {
            System.out.print(ch);
        }
    }

    private void println() {
        System.out.println();
    }
}
