package com.company.mtable.core;

import com.company.mtable.core.types.DataType;
import com.company.mtable.schema.Column;

/**
 * Created by jxwr on 2019/6/23.
 */
public class Projection implements Selection {
    private Column col;
    private String as;

    public Projection(Column col, String as) {
        this.col = col;
        this.as = as;
    }

    @Override
    public String name() {
        return as == null ? col.getName() : as;
    }

    @Override
    public DataType dataType() {
        return col.getType();
    }

    @Override
    public SelectionHandler getHandler(boolean aggregateQuery) {
        return new Handler(col.getCid());
    }

    private class Handler implements SelectionHandler {
        private int cid;
        private Record currentRecord = null;

        Handler(int cid) {
            this.cid = cid;
        }

        @Override
        public Object handle(Record record) {
            if (this.currentRecord == null)
                this.currentRecord = record;
            return record.get(cid);
        }

        @Override
        public Object finish() {
            return currentRecord.get(cid);
        }
    }
}
