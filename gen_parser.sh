#!/usr/bin/env bash

#!/bin/bash

if [ "$1" == "-d" ]; then
    echo "Downloading antlr... "
    curl -O https://www.antlr.org/download/antlr-4.7.1-complete.jar
fi

java -cp antlr-4.7.1-complete.jar org.antlr.v4.Tool \
     -package com.company.mtable.mql.parser \
     -no-listener src/main/java/com/company/mtable/mql/parser/Mql.g4
