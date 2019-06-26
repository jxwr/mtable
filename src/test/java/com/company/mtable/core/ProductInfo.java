package com.company.mtable.core;

import com.company.mtable.core.annotation.MData;

@MData(
    uniqueIndexFields = {"poiId", "date", "productId"},
    partitionField = "poiId"
)
public class ProductInfo {

    public long productId;

    public String productName;

    public long poiId;

    public long customerId;

    public byte tradeType;

    public int sellingPrice;

    public int date;
}
