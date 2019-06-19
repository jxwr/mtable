package com.company.mtable.core;

import com.company.mtable.schema.Schema;

public interface Scanner {

    void init(Schema schema);

    boolean handle(Schema schema, Record record);

    void finish(Schema schema);
}
