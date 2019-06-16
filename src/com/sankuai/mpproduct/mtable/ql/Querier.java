package com.sankuai.mpproduct.mtable.ql;

import com.sankuai.mpproduct.mtable.core.*;
import com.sankuai.mpproduct.mtable.core.Scanner;
import com.sankuai.mpproduct.mtable.schema.Schema;

import java.util.*;

/**
 * Created by jxwr on 2019/6/13.
 */
public class Querier {

    private Schema schema;

    private Bucket bucket;

    public Querier(Schema schema, Bucket bucket) {
        this.schema = schema;
        this.bucket = bucket;
    }

    private void putDictValue(DictData outer, Record record, NamedDictArray namedDictArray) {
        ArrayData inner = outer.getOrCreateArray(namedDictArray.getName());

        DictData valueDict = new DictData();

        for (Node node : namedDictArray.getNodes()) {
            if (node.getType() == NodeType.FIELD) {
                NamedField c = node.asNamedField();
                valueDict.putFieldData(c.getName(), record.getValue(schema.cid(c.getName())));
            }
        }

        valueDict = inner.findOrCreate(valueDict);

        for (Node node : namedDictArray.getNodes()) {
            switch (node.getType()) {
                case DICT_BY_KEY:
                    NamedDictByKey km = node.asNamedDictByKey();
                    putDictValue(valueDict, record, km);
                    break;
                case DICT_ARRAY:
                    NamedDictArray ml = node.asNamedDictArray();
                    putDictValue(valueDict, record, ml);
                    break;
            }
        }
    }

    private void putDictValue(DictData outer, Record record, NamedDictByKey namedDictByKey) {
        DictData inner = outer.getOrCreateDict(namedDictByKey.getName());

        String key = namedDictByKey.getKey();
        Object keyData = record.getValue(schema.cid(key));

        DictData valueDict = inner.getOrCreateDict(keyData);

        for (Node node : namedDictByKey.getNodes()) {
            switch (node.getType()) {
                case FIELD:
                    NamedField c = node.asNamedField();
                    valueDict.putFieldData(c.getName(), record.getValue(schema.cid(c.getName())));
                    break;
                case DICT_BY_KEY:
                    NamedDictByKey km = node.asNamedDictByKey();
                    putDictValue(valueDict, record, km);
                    break;
                case DICT_ARRAY:
                    NamedDictArray ml = node.asNamedDictArray();
                    putDictValue(valueDict, record, ml);
                    break;
            }
        }
    }

    public Map<Object, Object> query(Node root) {
        ResultSet resultSet = bucket.scan(schema, Collections.emptyList(), new Scanner() {
            private DictData resultDict;
            private ResultSet resultSet;

            @Override
            public void init(Schema schema) {
                resultDict = new DictData();
                resultSet = new ResultSet();
            }

            @Override
            public void handle(Schema schema, Record record) {
                if (root.getType() == NodeType.DICT_BY_KEY)
                    putDictValue(resultDict, record, root.asNamedDictByKey());
                else
                    putDictValue(resultDict, record, root.asNamedDictArray());
            }

            @Override
            public ResultSet finish(Schema schema) {
                resultSet.setResults(Collections.singletonList(resultDict.toMap()));
                return resultSet;
            }
        });

        return (Map<Object, Object>)resultSet.getResults().get(0);
    }
}
