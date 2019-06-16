package com.company.mtable.core;

import com.company.mtable.schema.ColumnMeta;
import com.company.mtable.schema.Schema;

import java.util.List;

/**
 * Created by jxwr on 2019/6/16.
 */
public class TablePrinter extends Scanner {
    private boolean firstline = true;

    private int minRowLen(ColumnMeta c) {
        int len = (c.getName().length()+2);
        return len < 10 ? 10 : len;
    }

    @Override
    public void handle(Schema schema, Record record) {
        if (record == null) return;

        List<ColumnMeta> cs = schema.getColumns();

        if (firstline) {
            System.out.printf("\nResults of table %s:\n", schema.getTableName());
            printline(cs, '-');

            for (ColumnMeta c : cs) {
                System.out.printf("%" + minRowLen(c) + "s|", c.getName());
            }
            println();

            printline(cs, '|');
            firstline = false;
        }

        for (int cid = 0; cid < cs.size(); cid++) {
            System.out.printf("%" + minRowLen(cs.get(cid)) + "s|", record.getValue(cid) + "");
        }
        println();

        printline(cs, '|');
    }

    private void printline(List<ColumnMeta> cs, char ch) {
        for (ColumnMeta c : cs) {
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
