package com.company.mtable.core.scanners;

import com.company.mtable.core.Record;
import com.company.mtable.core.Scanner;
import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;

import java.util.List;

/**
 * Created by jxwr on 2019/6/16.
 */
public class TablePrinter implements Scanner {
    private boolean firstline = true;
    private boolean hideHeader = false;

    private int minRowLen(Column c) {
        int len = (c.getName().length()+2);
        return len < 10 ? 10 : len;
    }

    @Override
    public void init(Schema schema) {

    }

    @Override
    public boolean handle(Schema schema, Record record) {
        if (record == null) return true;

        List<Column> cs = schema.getColumns();

        if (firstline && !hideHeader) {
            System.out.printf("\nResults of table %s:\n", schema.getTableName());
            printline(cs, '-');

            for (Column c : cs) {
                System.out.printf("%" + minRowLen(c) + "s|", c.getName());
            }
            println();

            printline(cs, '|');
            firstline = false;
        }

        for (int cid = 0; cid < cs.size(); cid++) {
            System.out.printf("%" + minRowLen(cs.get(cid)) + "s|", record.get(cid) + "");
        }
        println();

        printline(cs, '|');
        return true;
    }

    @Override
    public void finish(Schema schema) {

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

    public void setHideHeader(boolean hideHeader) {
        this.hideHeader = hideHeader;
    }
}
