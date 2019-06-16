package com.sankuai.mpproduct.mtable.ql;

import java.util.*;

/**
 * Created by jxwr on 2019/6/16.
 */
public class ArrayData {
    private Set<DictData> dicts;

    public ArrayData() {
        this.dicts = new HashSet<>();
    }

    public DictData findOrCreate(DictData dict) {
        for (DictData d : dicts) {
            if (d.equals(dict)) {
                return d;
            }
        }
        dicts.add(dict);
        return dict;
    }

    public Collection<DictData> getArray() {
        return dicts;
    }
}
