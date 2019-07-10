package com.company.subject.ids;

import com.company.subject.BusinessSpec;
import com.company.subject.Id;

public abstract class AbstractId implements Id {
    protected long id;
    protected int bitInSubjectType = -1;

    public AbstractId(long id) {
        this.id = id;
    }

    @Override
    public long value() {
        return id;
    }

    public int bit() {
        if (bitInSubjectType < 0) {
            bitInSubjectType = BusinessSpec.getInstance().bitInSubjectType(this.getClass());
        }
        return bitInSubjectType;
    }
}
