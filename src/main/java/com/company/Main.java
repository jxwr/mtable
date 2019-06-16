package com.company;

import com.company.mtable.core.Compiler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.company.mtable.core.*;
import com.company.mtable.core.Scanner;
import com.company.mtable.ql.AST;
import com.company.mtable.ql.Node;
import com.company.mtable.ql.Querier;
import com.company.mtable.schema.Schema;

import java.util.*;

public class Main {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void testDictByKey(Schema schema, Bucket bucket) {
        Node root = AST.makeKMap("products",
                "product_id",
                AST.makeKMap("dates",
                        "date",
                        AST.makeColumn("selling_price")));

        Map<Object, Object> m = new Querier(schema, bucket).query(root);
        System.out.println(gson.toJson(m));
    }

    public static void testDictArray(Schema schema, Bucket bucket) {
        Node root = AST.makeMapList("products",
                AST.makeColumn("product_id"),
                AST.makeMapList("dates",
                        AST.makeColumn("date"),
                        AST.makeColumn("selling_price")));

        Map<Object, Object> m = new Querier(schema, bucket).query(root);
        System.out.println(gson.toJson(m));
    }

    private static Schema newSchema() {
        Schema schema = new Schema("product_info");

        schema.addColumn("poi_id", Integer.class);
        schema.addColumn("product_id", Integer.class);
        schema.addColumn("customer_id", Integer.class);
        schema.addColumn("date", Integer.class);
        schema.addColumn("trade_type", Integer.class);
        schema.addColumn("selling_price", Integer.class);

        schema.setPartitionKey("poi_id");
        schema.setUniqueIndexKeys(Arrays.asList("poi_id", "date", "product_id"));
        return schema;
    }

    private static Bucket newBucket(Schema schema) {
        Bucket bucket = new Bucket();
        for (int i = 1; i < 11; i++) {
            Record record = Record.newRecord(schema);

            record.setValue(schema.cid("poi_id"), 100*i);
            record.setValue(schema.cid("product_id"), 601*(15-i));
            record.setValue(schema.cid("customer_id"), 302*i);
            record.setValue(schema.cid("date"), 20190500+i);
            record.setValue(schema.cid("trade_type"), 404*i);
            record.setValue(schema.cid("selling_price"), 205*i);

            bucket.put(schema, record);
        }

        for (int j = 0; j < 30; j++) {
            Record record = Record.newRecord(schema);

            record.setValue(schema.cid("poi_id"), 200);
            record.setValue(schema.cid("product_id"), 7813);
            record.setValue(schema.cid("customer_id"), 604);
            record.setValue(schema.cid("date"), 20190601 + j);
            record.setValue(schema.cid("trade_type"), 1);
            record.setValue(schema.cid("selling_price"), 35+j);
            bucket.put(schema, record);
        }

        for (int j = 0; j < 3; j++) {
            Record record = Record.newRecord(schema);

            record.setValue(schema.cid("poi_id"), 200);
            record.setValue(schema.cid("product_id"), 6611);
            record.setValue(schema.cid("customer_id"), 604);
            record.setValue(schema.cid("date"), 20190801 + j);
            record.setValue(schema.cid("trade_type"), 1);
            record.setValue(schema.cid("selling_price"), 35+j);
            bucket.put(schema, record);
        }

        return bucket;
    }

    public static void testBucketScan() {
        Schema schema = newSchema();
        Bucket bucket = newBucket(schema);

        bucket.scan(schema, Collections.emptyList(), new TablePrinter());

        bucket.scan(schema, Arrays.asList(
                        new Filter(0, OpType.GT, 340),
                        new Filter(1, OpType.LT, 3006)),
                new TablePrinter());

        bucket.scan(schema, Arrays.asList(
                new Filter(0, OpType.EQ, 300)),
                new TablePrinter());

        bucket.scan(schema, Arrays.asList(
                new Filter(1, OpType.EQ, 7813),
                new Filter(3, OpType.GTE, 20190601),
                new Filter(3, OpType.LTE, 20190605)),
                new TablePrinter());

        ResultSet resultSet = bucket.scan(schema, Arrays.asList(
                new Filter(1, OpType.EQ, 7813),
                new Filter(3, OpType.GTE, 20190601),
                new Filter(3, OpType.LTE, 20190605)),
                new Scanner() {
                    private int sum;
                    private int count;
                    private int price_cid;

                    @Override
                    public void init(Schema schema) {
                        sum = 0;
                        count = 0;
                        price_cid = schema.cid("selling_price");
                    }

                    @Override
                    public void handle(Schema schema, Record record) {
                        sum += (Integer) record.getValue(price_cid);
                        count++;
                    }

                    @Override
                    public ResultSet finish(Schema schema) {
                        getResultSet().setResults(Collections.singletonList(sum/count));
                        return getResultSet();
                    }
                });
        System.out.println("avg_price:" + resultSet.getResults().get(0));
    }

    public static void testBucketUpdate() {
        Schema schema = newSchema();
        Bucket bucket = newBucket(schema);
        Maker m = new Maker(schema);

        bucket.printTable(schema);

        List<ColValue> vals = Collections.singletonList(m.colValue("trade_type", 1));
        List<Filter> filters = Arrays.asList(
                m.filter("poi_id", OpType.EQ, 100),
                m.filter("date", OpType.EQ, 303),
                m.filter("product_id", OpType.EQ, 8414)
        );

        bucket.printTable(schema,
                m.filter("poi_id", OpType.EQ, 100),
                m.filter("date", OpType.EQ, 303),
                m.filter("product_id", OpType.EQ, 8414));
        bucket.update(schema, vals, filters);
        bucket.printTable(schema);

        vals = Collections.singletonList(m.colValue("selling_price", 78));
        System.out.println(bucket.update(schema, vals, filters));
        bucket.printTable(schema);

        filters = Collections.singletonList(
                m.filter("poi_id", OpType.GT, 200));

        vals = Collections.singletonList(m.colValue("trade_type", 2));
        bucket.update(schema, vals, filters);
        bucket.printTable(schema);

        //testDictByKey(schema, bucket);
        //testDictArray(schema, bucket);
    }

    public static void testCodegen() {
         Compiler compiler = new Compiler();
         Scanner s = compiler.compileAndLoadScanner("" +
                 "private int sum = 0;" +
                 "public void init(Schema schema) { " +
                 "    System.out.println(\"Init\"); " +
                 "    sum++;" +
                 "} " +
                 "public void handle(Schema schema, Record record) { " +
                 "    System.out.println(\"Sum: \" + sum); " +
                 "} "
         );
        s.init(null);
        s.handle(null, null);
    }

    public static void main(String[] args) {
        //testKMap();
        //testMapList();

        // testBucketScan();
        // testBucketUpdate();
        testCodegen();
    }
}
