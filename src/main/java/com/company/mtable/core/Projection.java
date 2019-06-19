package com.company.mtable.core;

import com.company.mtable.schema.Column;
import com.company.mtable.schema.Schema;

import java.util.List;

/**
 * Created by jxwr on 2019/6/19.
 */
public class Projection {

    private String as;

    private List<Column> cols;

    private Func func;

    private boolean stop = false;

    public void init(Schema schema) {
    }

    public boolean shouldStop() {
        return this.stop;
    }

    public boolean handle(Schema schema, Record record) {
        return true;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Object finish(Schema schema) {
        return 0;
    }
}
