// Generated from src/main/java/com/company/mql/parser/Mql.g4 by ANTLR 4.7.1
package com.company.mql.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MqlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, STRING=23, FLOAT=24, 
		INT=25, NULL=26, BOOLEAN=27, QUERY=28, MUTATION=29, NAME=30, WS=31, COMMENT=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "STRING", "ESCAPED_CHAR", 
		"ESCAPED_UNICODE", "HEX_CHAR", "FLOAT", "INT", "EXPONENT", "NULL", "BOOLEAN", 
		"QUERY", "MUTATION", "NAME", "WS", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'type'", "'input'", "'enum'", "'interface'", "'union'", "'='", 
		"'|'", "'implements'", "'{'", "','", "'}'", "':'", "'('", "')'", "'$'", 
		"'['", "']'", "'!'", "'fragment'", "'...'", "'on'", "'@'", null, null, 
		null, "'null'", null, "'query'", "'mutation'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "STRING", 
		"FLOAT", "INT", "NULL", "BOOLEAN", "QUERY", "MUTATION", "NAME", "WS", 
		"COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MqlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u0111\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\7\30\u00a5\n\30\f\30\16\30\u00a8\13\30\3\30\3\30\3\31\3\31\3\31\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\6\34\u00bc"+
		"\n\34\r\34\16\34\u00bd\3\34\5\34\u00c1\n\34\3\34\3\34\3\34\3\34\5\34\u00c7"+
		"\n\34\3\35\5\35\u00ca\n\35\3\35\3\35\3\35\7\35\u00cf\n\35\f\35\16\35\u00d2"+
		"\13\35\5\35\u00d4\n\35\3\36\3\36\5\36\u00d8\n\36\3\36\3\36\3\37\3\37\3"+
		"\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u00ea\n \3!\3!\3!\3!\3!\3"+
		"!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\7#\u00fd\n#\f#\16#\u0100\13"+
		"#\3$\6$\u0103\n$\r$\16$\u0104\3$\3$\3%\3%\7%\u010b\n%\f%\16%\u010e\13"+
		"%\3%\3%\2\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\2\63\2\65\2\67"+
		"\329\33;\2=\34?\35A\36C\37E G!I\"\3\2\f\4\2$$^^\n\2$$\61\61^^ddhhpptt"+
		"vv\5\2\62;CHch\3\2\62;\3\2\63;\4\2GGgg\5\2C\\aac|\6\2\62;C\\aac|\5\2\13"+
		"\f\17\17\"\"\4\2\f\f\17\17\2\u011b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3K"+
		"\3\2\2\2\5P\3\2\2\2\7V\3\2\2\2\t[\3\2\2\2\13e\3\2\2\2\rk\3\2\2\2\17m\3"+
		"\2\2\2\21o\3\2\2\2\23z\3\2\2\2\25|\3\2\2\2\27~\3\2\2\2\31\u0080\3\2\2"+
		"\2\33\u0082\3\2\2\2\35\u0084\3\2\2\2\37\u0086\3\2\2\2!\u0088\3\2\2\2#"+
		"\u008a\3\2\2\2%\u008c\3\2\2\2\'\u008e\3\2\2\2)\u0097\3\2\2\2+\u009b\3"+
		"\2\2\2-\u009e\3\2\2\2/\u00a0\3\2\2\2\61\u00ab\3\2\2\2\63\u00ae\3\2\2\2"+
		"\65\u00b6\3\2\2\2\67\u00c6\3\2\2\29\u00c9\3\2\2\2;\u00d5\3\2\2\2=\u00db"+
		"\3\2\2\2?\u00e9\3\2\2\2A\u00eb\3\2\2\2C\u00f1\3\2\2\2E\u00fa\3\2\2\2G"+
		"\u0102\3\2\2\2I\u0108\3\2\2\2KL\7v\2\2LM\7{\2\2MN\7r\2\2NO\7g\2\2O\4\3"+
		"\2\2\2PQ\7k\2\2QR\7p\2\2RS\7r\2\2ST\7w\2\2TU\7v\2\2U\6\3\2\2\2VW\7g\2"+
		"\2WX\7p\2\2XY\7w\2\2YZ\7o\2\2Z\b\3\2\2\2[\\\7k\2\2\\]\7p\2\2]^\7v\2\2"+
		"^_\7g\2\2_`\7t\2\2`a\7h\2\2ab\7c\2\2bc\7e\2\2cd\7g\2\2d\n\3\2\2\2ef\7"+
		"w\2\2fg\7p\2\2gh\7k\2\2hi\7q\2\2ij\7p\2\2j\f\3\2\2\2kl\7?\2\2l\16\3\2"+
		"\2\2mn\7~\2\2n\20\3\2\2\2op\7k\2\2pq\7o\2\2qr\7r\2\2rs\7n\2\2st\7g\2\2"+
		"tu\7o\2\2uv\7g\2\2vw\7p\2\2wx\7v\2\2xy\7u\2\2y\22\3\2\2\2z{\7}\2\2{\24"+
		"\3\2\2\2|}\7.\2\2}\26\3\2\2\2~\177\7\177\2\2\177\30\3\2\2\2\u0080\u0081"+
		"\7<\2\2\u0081\32\3\2\2\2\u0082\u0083\7*\2\2\u0083\34\3\2\2\2\u0084\u0085"+
		"\7+\2\2\u0085\36\3\2\2\2\u0086\u0087\7&\2\2\u0087 \3\2\2\2\u0088\u0089"+
		"\7]\2\2\u0089\"\3\2\2\2\u008a\u008b\7_\2\2\u008b$\3\2\2\2\u008c\u008d"+
		"\7#\2\2\u008d&\3\2\2\2\u008e\u008f\7h\2\2\u008f\u0090\7t\2\2\u0090\u0091"+
		"\7c\2\2\u0091\u0092\7i\2\2\u0092\u0093\7o\2\2\u0093\u0094\7g\2\2\u0094"+
		"\u0095\7p\2\2\u0095\u0096\7v\2\2\u0096(\3\2\2\2\u0097\u0098\7\60\2\2\u0098"+
		"\u0099\7\60\2\2\u0099\u009a\7\60\2\2\u009a*\3\2\2\2\u009b\u009c\7q\2\2"+
		"\u009c\u009d\7p\2\2\u009d,\3\2\2\2\u009e\u009f\7B\2\2\u009f.\3\2\2\2\u00a0"+
		"\u00a6\7$\2\2\u00a1\u00a5\5\61\31\2\u00a2\u00a5\5\63\32\2\u00a3\u00a5"+
		"\n\2\2\2\u00a4\u00a1\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5"+
		"\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2"+
		"\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\7$\2\2\u00aa\60\3\2\2\2\u00ab\u00ac"+
		"\7^\2\2\u00ac\u00ad\t\3\2\2\u00ad\62\3\2\2\2\u00ae\u00af\7^\2\2\u00af"+
		"\u00b0\7w\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\5\65\33\2\u00b2\u00b3\5"+
		"\65\33\2\u00b3\u00b4\5\65\33\2\u00b4\u00b5\5\65\33\2\u00b5\64\3\2\2\2"+
		"\u00b6\u00b7\t\4\2\2\u00b7\66\3\2\2\2\u00b8\u00b9\59\35\2\u00b9\u00bb"+
		"\7\60\2\2\u00ba\u00bc\t\5\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2"+
		"\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf\u00c1"+
		"\5;\36\2\u00c0\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c7\3\2\2\2\u00c2"+
		"\u00c3\59\35\2\u00c3\u00c4\5;\36\2\u00c4\u00c7\3\2\2\2\u00c5\u00c7\59"+
		"\35\2\u00c6\u00b8\3\2\2\2\u00c6\u00c2\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7"+
		"8\3\2\2\2\u00c8\u00ca\7/\2\2\u00c9\u00c8\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00d3\3\2\2\2\u00cb\u00d4\7\62\2\2\u00cc\u00d0\t\6\2\2\u00cd\u00cf\t"+
		"\5\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00cb\3\2"+
		"\2\2\u00d3\u00cc\3\2\2\2\u00d4:\3\2\2\2\u00d5\u00d7\t\7\2\2\u00d6\u00d8"+
		"\7-\2\2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00da\59\35\2\u00da<\3\2\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7w\2\2\u00dd"+
		"\u00de\7n\2\2\u00de\u00df\7n\2\2\u00df>\3\2\2\2\u00e0\u00e1\7v\2\2\u00e1"+
		"\u00e2\7t\2\2\u00e2\u00e3\7w\2\2\u00e3\u00ea\7g\2\2\u00e4\u00e5\7h\2\2"+
		"\u00e5\u00e6\7c\2\2\u00e6\u00e7\7n\2\2\u00e7\u00e8\7u\2\2\u00e8\u00ea"+
		"\7g\2\2\u00e9\u00e0\3\2\2\2\u00e9\u00e4\3\2\2\2\u00ea@\3\2\2\2\u00eb\u00ec"+
		"\7s\2\2\u00ec\u00ed\7w\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7t\2\2\u00ef"+
		"\u00f0\7{\2\2\u00f0B\3\2\2\2\u00f1\u00f2\7o\2\2\u00f2\u00f3\7w\2\2\u00f3"+
		"\u00f4\7v\2\2\u00f4\u00f5\7c\2\2\u00f5\u00f6\7v\2\2\u00f6\u00f7\7k\2\2"+
		"\u00f7\u00f8\7q\2\2\u00f8\u00f9\7p\2\2\u00f9D\3\2\2\2\u00fa\u00fe\t\b"+
		"\2\2\u00fb\u00fd\t\t\2\2\u00fc\u00fb\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe"+
		"\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ffF\3\2\2\2\u0100\u00fe\3\2\2\2"+
		"\u0101\u0103\t\n\2\2\u0102\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0102"+
		"\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\b$\2\2\u0107"+
		"H\3\2\2\2\u0108\u010c\7%\2\2\u0109\u010b\n\13\2\2\u010a\u0109\3\2\2\2"+
		"\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010f"+
		"\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0110\b%\2\2\u0110J\3\2\2\2\20\2\u00a4"+
		"\u00a6\u00bd\u00c0\u00c6\u00c9\u00d0\u00d3\u00d7\u00e9\u00fe\u0104\u010c"+
		"\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}