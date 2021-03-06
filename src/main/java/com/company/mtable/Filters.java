package com.company.mtable;

import com.company.mtable.core.Filter;
import com.company.mtable.core.IndexValue;
import com.company.mtable.core.OpType;
import com.company.mtable.core.Record;
import com.company.mtable.core.types.DataType;
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
                    DataType type = schema.column(cid).getType();
                    ival.setValue(i++, type.minValue());
                    fillTail = true;
                }
            } else {
                DataType type = schema.column(cid).getType();
                ival.setValue(i++, type.minValue());
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
                    DataType type = schema.column(cid).getType();
                    ival.setValue(i++, type.maxValue());
                    fillTail = true;
                }
            } else {
                DataType type = schema.column(cid).getType();
                ival.setValue(i++, type.maxValue());
            }
        }

        return ival;
    }

    public static boolean checkAll(Schema schema, Record record, List<Filter> filters) {
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
