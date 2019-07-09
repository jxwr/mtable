package com.company.mtable.core;

import com.company.mtable.FunctionCall;
import com.company.mtable.core.types.DataType;

/**
 * Created by jxwr on 2019/6/23.
 */
public interface Selection {

    String name();

    DataType dataType();

    SelectionHandler getHandler(boolean aggregateQuery);
}
