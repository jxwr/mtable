package com.company.mql.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jxwr on 2019/7/1.
 */
public class MqlParserTest {

    @Test
    public void testParse() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("ql/t01.mql");
        MqlLexer lexer = new MqlLexer(CharStreams.fromStream(in));
        MqlParser parser = new MqlParser(new CommonTokenStream(lexer));
        lexer.removeErrorListeners();
        parser.removeErrorListeners();

        parser.document();

        System.out.println(parser.selectionSet());
    }
}