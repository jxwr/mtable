package com.company.mtable.core;

import com.company.mtable.exception.InvalidPartitionFilterException;
import com.company.mtable.schema.Schema;

import java.util.List;

/**
 * Created by jxwr on 2019/6/14.
 */
public class Filters {

    public static IndexValue getFullIndexValue(Schema schema, List<Filter> filters) throws InvalidPartitionFilterException {
        int[] indexCids = schema.getUniqueIndexCids();

        if (filters.size() != indexCids.length) {
            throw new InvalidPartitionFilterException();
        }

        IndexValue ival = schema.newIndexValue();

        int i = 0;
        for (int cid : indexCids) {
            Filter hit = null;
            for (Filter f : filters) {
                if (f.getCid() == cid && (f.getOp() == OpType.EQ)) {
                    hit = f;
                    break;
                }
            }
            if (hit != null) {
                ival.setValue(i++, hit.getValue());
            } else {
                throw new InvalidPartitionFilterException();
            }
        }

        return ival;
    }

    public static IndexValue getLowestPrefix(Schema schema, List<Filter> filters) {
        int[] indexCids = schema.getUniqueIndexCids();
        IndexValue ival = schema.newIndexValue();

        boolean fillTail = false;
        int i = 0;
        for (int cid : indexCids) {
            Filter hit = null;

            if (!fillTail) {
                for (Filter f : filters) {
                    if (f.getCid() == cid && (f.getOp() == OpType.EQ || f.getOp() == OpType.GT)) {
                        hit = f;
                        break;
                    }
                }
                if (hit != null) {
                    ival.setValue(i++, hit.getValue());
                } else {
                    ival.setValue(i++, 0);
                    fillTail = true;
                }
            } else {
                ival.setValue(i++, 0);
            }
        }

        return ival;
    }

    public static IndexValue getUpperPrefix(Schema schema, List<Filter> filters) {
        int[] indexCids = schema.getUniqueIndexCids();
        IndexValue ival = schema.newIndexValue();

        boolean fillTail = false;
        int i = 0;
        for (int cid : indexCids) {
            Filter hit = null;

            if (!fillTail) {
                for (Filter f : filters) {
                    if (f.getCid() == cid && (f.getOp() == OpType.EQ || f.getOp() == OpType.LT)) {
                        hit = f;
                        break;
                    }
                }
                if (hit != null) {
                    ival.setValue(i++, hit.getValue());
                } else {
                    ival.setValue(i++, Integer.MAX_VALUE);
                    fillTail = true;
                }
            } else {
                ival.setValue(i++, Integer.MAX_VALUE);
            }
        }

        return ival;
    }

    public static boolean filterAll(Schema schema, Record record, List<Filter> filters) {
        for (Filter filter : filters) {
            int cid = filter.getCid();
            Object val = record.get(cid);
            if (!filter.check(val)) {
                return false;
            }
        }

        return true;
    }
}
