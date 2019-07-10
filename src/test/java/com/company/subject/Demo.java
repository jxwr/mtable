package com.company.subject;

import com.company.subject.ids.*;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    static SubjectType DAY_ROOM = new SubjectType((byte)1);
    static SubjectType HOUR_ROOM = new SubjectType((byte)2);

    static {
        // 业务Spec全局定义
        BusinessSpec hotelBizSpec = BusinessSpec.getInstance();

        // 每个Id类型占一位，低8位留给自定义类型，一旦定义，不能改变
        hotelBizSpec.declareIdBitInSubjectType(ProductId.class, 1<<8);
        hotelBizSpec.declareIdBitInSubjectType(SkuId.class, 1<<9);
        hotelBizSpec.declareIdBitInSubjectType(CustomerId.class, 1<<10);
        hotelBizSpec.declareIdBitInSubjectType(PoiId.class, 1<<11);
        hotelBizSpec.declareIdBitInSubjectType(RoomId.class, 1<<12);
        hotelBizSpec.declareIdBitInSubjectType(ChannelId.class, 1<<13);

        // 声明哪些主体组合是合法的
        hotelBizSpec.declareValidSubjectSpec(PoiId.class, CustomerId.class);
        hotelBizSpec.declareValidSubjectSpec(RoomId.class, CustomerId.class);
        TypeSignature3<RoomId, PoiId, CustomerId> SIG_LOGIC_ROOM = hotelBizSpec.declareValidSubjectSpec(RoomId.class, PoiId.class, CustomerId.class);
    }

    public static void main(String[] args) {
        SkuId skuId = new SkuId(100);
        ProductId productId = new ProductId(101);
        PoiId poiId = new PoiId(200);
        CustomerId customerId = new CustomerId(300);
        ChannelId channelId = new ChannelId(1);
        RoomId roomId = new RoomId(400);

        Map<SkuId, Subjects> subjectsBySkuId= new HashMap<>();

        Subjects subjects = subjectsBySkuId.computeIfAbsent(skuId, k -> new Subjects());

        // 纯ID主体
        subjects.add(Subject.of(productId));
        subjects.add(Subject.of(poiId, customerId));
        subjects.add(Subject.of(roomId, customerId));
        subjects.add(Subject.of(roomId, customerId));

        // 带类型主体
        subjects.add(Subject.of(DAY_ROOM, roomId, customerId));
        subjects.add(Subject.of(HOUR_ROOM, roomId, customerId));
        subjects.add(Subject.of(DAY_ROOM, roomId, poiId, customerId));

        TypeSignature3<RoomId, PoiId, CustomerId> TS_LOGIC_ROOM = new TypeSignature3<>();

        // 类型合法
        subjects.add(Subject.of(TS_LOGIC_ROOM, DAY_ROOM, roomId, poiId, customerId));
        subjects.add(Subject.of(TS_LOGIC_ROOM, roomId, poiId, customerId));

        // 类型不合法, productId
        // subjects.add(Subject.of(LOGIC_ROOM_SUBJECT, DAY_ROOM, roomId, productId, customerId));

        try {
            subjects.add(Subject.of(poiId, customerId, channelId));
        } catch (Exception e) {
            System.out.println("invalid");
        }

        for (Subject subject : subjects) {
            System.out.println(subject);
        }
    }
}
