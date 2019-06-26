package com.company.mtable.core.scanners;

import com.company.mtable.core.Filter;
import com.company.mtable.core.OpType;
import com.company.mtable.core.Record;
import com.company.mtable.core.Scanner;
import com.company.mtable.core.types.Types;
import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxwr on 2019/6/16.
 */
public class TablePrinter implements Scanner {
    private boolean firstline = true;

    private boolean hideHeader = false;

    private List<Filter> filters;

    private int minRowLen(Column c) {
        int len = (c.getName().length() + 3);
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
            System.out.printf("\nResults of %s:\n", schema.getTableName());
            printline(cs, '-');

            for (Column c : cs) {
                System.out.printf("%" + minRowLen(c) + "s|", c.getName() + "[" + Types.typeChar(c.getType()));
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

    @Override
    public List<Filter> getFilters() {
        if (filters == null)
            filters = new ArrayList<>();
        return filters;
    }

    public void addFilter(Filter filter) {
        if (filters == null)
            filters = new ArrayList<>();
        filters.add(filter);
    }

    public void addFilter(int cid, OpType op, Comparable value) {
        addFilter(new Filter(cid, op, value));
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
