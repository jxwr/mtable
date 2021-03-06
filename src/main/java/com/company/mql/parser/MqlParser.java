// Generated from src/main/java/com/company/mql/parser/Mql.g4 by ANTLR 4.7.1
package com.company.mql.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.company.mql.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, STRING=23, FLOAT=24, 
		INT=25, NULL=26, BOOLEAN=27, QUERY=28, MUTATION=29, NAME=30, WS=31, COMMENT=32;
	public static final int
		RULE_schema = 0, RULE_typeDefinition = 1, RULE_inputTypeDefinition = 2, 
		RULE_enumDefinition = 3, RULE_interfaceDefinition = 4, RULE_unionDefinition = 5, 
		RULE_interfaceImplementation = 6, RULE_fieldDefinitions = 7, RULE_fieldDefinition = 8, 
		RULE_enumFields = 9, RULE_document = 10, RULE_definition = 11, RULE_operationDefinition = 12, 
		RULE_namedOperationDefinition = 13, RULE_operationType = 14, RULE_variableDefinitions = 15, 
		RULE_variableDefinition = 16, RULE_defaultValue = 17, RULE_variable = 18, 
		RULE_dataType = 19, RULE_namedType = 20, RULE_listType = 21, RULE_nonNullNamedType = 22, 
		RULE_nonNullListType = 23, RULE_selectionSet = 24, RULE_selection = 25, 
		RULE_field = 26, RULE_alias = 27, RULE_fragmentDefinition = 28, RULE_fragmentSpread = 29, 
		RULE_inlineFragment = 30, RULE_fragmentName = 31, RULE_typeCondition = 32, 
		RULE_directives = 33, RULE_directive = 34, RULE_arguments = 35, RULE_argument = 36, 
		RULE_value = 37, RULE_enumValue = 38, RULE_listValue = 39, RULE_objectValue = 40;
	public static final String[] ruleNames = {
		"schema", "typeDefinition", "inputTypeDefinition", "enumDefinition", "interfaceDefinition", 
		"unionDefinition", "interfaceImplementation", "fieldDefinitions", "fieldDefinition", 
		"enumFields", "document", "definition", "operationDefinition", "namedOperationDefinition", 
		"operationType", "variableDefinitions", "variableDefinition", "defaultValue", 
		"variable", "dataType", "namedType", "listType", "nonNullNamedType", "nonNullListType", 
		"selectionSet", "selection", "field", "alias", "fragmentDefinition", "fragmentSpread", 
		"inlineFragment", "fragmentName", "typeCondition", "directives", "directive", 
		"arguments", "argument", "value", "enumValue", "listValue", "objectValue"
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

	@Override
	public String getGrammarFileName() { return "Mql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	private AstFactory factory = new AstFactory();

	public Document getDocument() {
	    return factory.getDocument();
	}

	public MqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SchemaContext extends ParserRuleContext {
		public List<TypeDefinitionContext> typeDefinition() {
			return getRuleContexts(TypeDefinitionContext.class);
		}
		public TypeDefinitionContext typeDefinition(int i) {
			return getRuleContext(TypeDefinitionContext.class,i);
		}
		public List<InputTypeDefinitionContext> inputTypeDefinition() {
			return getRuleContexts(InputTypeDefinitionContext.class);
		}
		public InputTypeDefinitionContext inputTypeDefinition(int i) {
			return getRuleContext(InputTypeDefinitionContext.class,i);
		}
		public List<EnumDefinitionContext> enumDefinition() {
			return getRuleContexts(EnumDefinitionContext.class);
		}
		public EnumDefinitionContext enumDefinition(int i) {
			return getRuleContext(EnumDefinitionContext.class,i);
		}
		public List<InterfaceDefinitionContext> interfaceDefinition() {
			return getRuleContexts(InterfaceDefinitionContext.class);
		}
		public InterfaceDefinitionContext interfaceDefinition(int i) {
			return getRuleContext(InterfaceDefinitionContext.class,i);
		}
		public List<UnionDefinitionContext> unionDefinition() {
			return getRuleContexts(UnionDefinitionContext.class);
		}
		public UnionDefinitionContext unionDefinition(int i) {
			return getRuleContext(UnionDefinitionContext.class,i);
		}
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_schema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(87);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(82);
					typeDefinition();
					}
					break;
				case T__1:
					{
					setState(83);
					inputTypeDefinition();
					}
					break;
				case T__2:
					{
					setState(84);
					enumDefinition();
					}
					break;
				case T__3:
					{
					setState(85);
					interfaceDefinition();
					}
					break;
				case T__4:
					{
					setState(86);
					unionDefinition();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(89); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDefinitionContext extends ParserRuleContext {
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public FieldDefinitionsContext fieldDefinitions() {
			return getRuleContext(FieldDefinitionsContext.class,0);
		}
		public InterfaceImplementationContext interfaceImplementation() {
			return getRuleContext(InterfaceImplementationContext.class,0);
		}
		public TypeDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefinition; }
	}

	public final TypeDefinitionContext typeDefinition() throws RecognitionException {
		TypeDefinitionContext _localctx = new TypeDefinitionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_typeDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(T__0);
			setState(92);
			namedType();
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(93);
				interfaceImplementation();
				}
			}

			setState(96);
			fieldDefinitions();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InputTypeDefinitionContext extends ParserRuleContext {
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public FieldDefinitionsContext fieldDefinitions() {
			return getRuleContext(FieldDefinitionsContext.class,0);
		}
		public InputTypeDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputTypeDefinition; }
	}

	public final InputTypeDefinitionContext inputTypeDefinition() throws RecognitionException {
		InputTypeDefinitionContext _localctx = new InputTypeDefinitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_inputTypeDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__1);
			setState(99);
			namedType();
			setState(100);
			fieldDefinitions();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumDefinitionContext extends ParserRuleContext {
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public EnumFieldsContext enumFields() {
			return getRuleContext(EnumFieldsContext.class,0);
		}
		public EnumDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDefinition; }
	}

	public final EnumDefinitionContext enumDefinition() throws RecognitionException {
		EnumDefinitionContext _localctx = new EnumDefinitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_enumDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__2);
			setState(103);
			namedType();
			setState(104);
			enumFields();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceDefinitionContext extends ParserRuleContext {
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public FieldDefinitionsContext fieldDefinitions() {
			return getRuleContext(FieldDefinitionsContext.class,0);
		}
		public InterfaceDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDefinition; }
	}

	public final InterfaceDefinitionContext interfaceDefinition() throws RecognitionException {
		InterfaceDefinitionContext _localctx = new InterfaceDefinitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_interfaceDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__3);
			setState(107);
			namedType();
			setState(108);
			fieldDefinitions();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionDefinitionContext extends ParserRuleContext {
		public List<NamedTypeContext> namedType() {
			return getRuleContexts(NamedTypeContext.class);
		}
		public NamedTypeContext namedType(int i) {
			return getRuleContext(NamedTypeContext.class,i);
		}
		public UnionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionDefinition; }
	}

	public final UnionDefinitionContext unionDefinition() throws RecognitionException {
		UnionDefinitionContext _localctx = new UnionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_unionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__4);
			setState(111);
			namedType();
			setState(112);
			match(T__5);
			setState(113);
			namedType();
			setState(116); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(114);
				match(T__6);
				setState(115);
				namedType();
				}
				}
				setState(118); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__6 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceImplementationContext extends ParserRuleContext {
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public InterfaceImplementationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceImplementation; }
	}

	public final InterfaceImplementationContext interfaceImplementation() throws RecognitionException {
		InterfaceImplementationContext _localctx = new InterfaceImplementationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_interfaceImplementation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__7);
			setState(121);
			namedType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDefinitionsContext extends ParserRuleContext {
		public List<FieldDefinitionContext> fieldDefinition() {
			return getRuleContexts(FieldDefinitionContext.class);
		}
		public FieldDefinitionContext fieldDefinition(int i) {
			return getRuleContext(FieldDefinitionContext.class,i);
		}
		public FieldDefinitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDefinitions; }
	}

	public final FieldDefinitionsContext fieldDefinitions() throws RecognitionException {
		FieldDefinitionsContext _localctx = new FieldDefinitionsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fieldDefinitions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(T__8);
			setState(124);
			fieldDefinition();
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==NAME) {
				{
				{
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(125);
					match(T__9);
					}
					}
					setState(130);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(131);
				fieldDefinition();
				}
				}
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(137);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDefinitionContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public FieldDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDefinition; }
	}

	public final FieldDefinitionContext fieldDefinition() throws RecognitionException {
		FieldDefinitionContext _localctx = new FieldDefinitionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fieldDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(NAME);
			setState(140);
			match(T__11);
			setState(141);
			dataType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumFieldsContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(MqlParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(MqlParser.NAME, i);
		}
		public EnumFieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumFields; }
	}

	public final EnumFieldsContext enumFields() throws RecognitionException {
		EnumFieldsContext _localctx = new EnumFieldsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_enumFields);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__8);
			setState(144);
			match(NAME);
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==NAME) {
				{
				{
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(145);
					match(T__9);
					}
					}
					setState(150);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(151);
				match(NAME);
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(157);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DocumentContext extends ParserRuleContext {
		public DefinitionContext definition;
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_document);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(159);
				((DocumentContext)_localctx).definition = definition();
				 factory.addDefinition(((DocumentContext)_localctx).definition.result); 
				}
				}
				setState(164); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__18) | (1L << QUERY) | (1L << MUTATION))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionContext extends ParserRuleContext {
		public Definition result;
		public OperationDefinitionContext operationDefinition;
		public OperationDefinitionContext operationDefinition() {
			return getRuleContext(OperationDefinitionContext.class,0);
		}
		public FragmentDefinitionContext fragmentDefinition() {
			return getRuleContext(FragmentDefinitionContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_definition);
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
			case QUERY:
			case MUTATION:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				((DefinitionContext)_localctx).operationDefinition = operationDefinition();
				 ((DefinitionContext)_localctx).result =  ((DefinitionContext)_localctx).operationDefinition.result; 
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				fragmentDefinition();
				 ((DefinitionContext)_localctx).result =  null; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationDefinitionContext extends ParserRuleContext {
		public Definition result;
		public SelectionSetContext selectionSet;
		public SelectionSetContext selectionSet() {
			return getRuleContext(SelectionSetContext.class,0);
		}
		public NamedOperationDefinitionContext namedOperationDefinition() {
			return getRuleContext(NamedOperationDefinitionContext.class,0);
		}
		public OperationDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationDefinition; }
	}

	public final OperationDefinitionContext operationDefinition() throws RecognitionException {
		OperationDefinitionContext _localctx = new OperationDefinitionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operationDefinition);
		try {
			setState(178);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				((OperationDefinitionContext)_localctx).selectionSet = selectionSet();
				 ((OperationDefinitionContext)_localctx).result =  factory.createOperationDefinition(((OperationDefinitionContext)_localctx).selectionSet.result); 
				}
				break;
			case QUERY:
			case MUTATION:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				namedOperationDefinition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamedOperationDefinitionContext extends ParserRuleContext {
		public OperationTypeContext operationType() {
			return getRuleContext(OperationTypeContext.class,0);
		}
		public SelectionSetContext selectionSet() {
			return getRuleContext(SelectionSetContext.class,0);
		}
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public VariableDefinitionsContext variableDefinitions() {
			return getRuleContext(VariableDefinitionsContext.class,0);
		}
		public DirectivesContext directives() {
			return getRuleContext(DirectivesContext.class,0);
		}
		public NamedOperationDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedOperationDefinition; }
	}

	public final NamedOperationDefinitionContext namedOperationDefinition() throws RecognitionException {
		NamedOperationDefinitionContext _localctx = new NamedOperationDefinitionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_namedOperationDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			operationType();
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(181);
				match(NAME);
				}
			}

			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(184);
				variableDefinitions();
				}
			}

			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(187);
				directives();
				}
			}

			setState(190);
			selectionSet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationTypeContext extends ParserRuleContext {
		public TerminalNode QUERY() { return getToken(MqlParser.QUERY, 0); }
		public TerminalNode MUTATION() { return getToken(MqlParser.MUTATION, 0); }
		public OperationTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationType; }
	}

	public final OperationTypeContext operationType() throws RecognitionException {
		OperationTypeContext _localctx = new OperationTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_operationType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_la = _input.LA(1);
			if ( !(_la==QUERY || _la==MUTATION) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDefinitionsContext extends ParserRuleContext {
		public List<VariableDefinitionContext> variableDefinition() {
			return getRuleContexts(VariableDefinitionContext.class);
		}
		public VariableDefinitionContext variableDefinition(int i) {
			return getRuleContext(VariableDefinitionContext.class,i);
		}
		public VariableDefinitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDefinitions; }
	}

	public final VariableDefinitionsContext variableDefinitions() throws RecognitionException {
		VariableDefinitionsContext _localctx = new VariableDefinitionsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_variableDefinitions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(T__12);
			setState(195);
			variableDefinition();
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==T__14) {
				{
				{
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(196);
					match(T__9);
					}
					}
					setState(201);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(202);
				variableDefinition();
				}
				}
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(208);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDefinitionContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public VariableDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDefinition; }
	}

	public final VariableDefinitionContext variableDefinition() throws RecognitionException {
		VariableDefinitionContext _localctx = new VariableDefinitionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_variableDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			variable();
			setState(211);
			match(T__11);
			setState(212);
			dataType();
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(213);
				defaultValue();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(T__5);
			setState(217);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__14);
			setState(220);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataTypeContext extends ParserRuleContext {
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public ListTypeContext listType() {
			return getRuleContext(ListTypeContext.class,0);
		}
		public NonNullNamedTypeContext nonNullNamedType() {
			return getRuleContext(NonNullNamedTypeContext.class,0);
		}
		public NonNullListTypeContext nonNullListType() {
			return getRuleContext(NonNullListTypeContext.class,0);
		}
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_dataType);
		try {
			setState(226);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				namedType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(223);
				listType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(224);
				nonNullNamedType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(225);
				nonNullListType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamedTypeContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public NamedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedType; }
	}

	public final NamedTypeContext namedType() throws RecognitionException {
		NamedTypeContext _localctx = new NamedTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_namedType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListTypeContext extends ParserRuleContext {
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public ListTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listType; }
	}

	public final ListTypeContext listType() throws RecognitionException {
		ListTypeContext _localctx = new ListTypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_listType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(T__15);
			setState(231);
			dataType();
			setState(232);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonNullNamedTypeContext extends ParserRuleContext {
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public NonNullNamedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonNullNamedType; }
	}

	public final NonNullNamedTypeContext nonNullNamedType() throws RecognitionException {
		NonNullNamedTypeContext _localctx = new NonNullNamedTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_nonNullNamedType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			namedType();
			setState(235);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonNullListTypeContext extends ParserRuleContext {
		public ListTypeContext listType() {
			return getRuleContext(ListTypeContext.class,0);
		}
		public NonNullListTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonNullListType; }
	}

	public final NonNullListTypeContext nonNullListType() throws RecognitionException {
		NonNullListTypeContext _localctx = new NonNullListTypeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_nonNullListType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			listType();
			setState(238);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionSetContext extends ParserRuleContext {
		public SelectionSet result;
		public SelectionContext selection;
		public List<SelectionContext> selection() {
			return getRuleContexts(SelectionContext.class);
		}
		public SelectionContext selection(int i) {
			return getRuleContext(SelectionContext.class,i);
		}
		public SelectionSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectionSet; }
	}

	public final SelectionSetContext selectionSet() throws RecognitionException {
		SelectionSetContext _localctx = new SelectionSetContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_selectionSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(T__8);
			 SelectionSet selectionSet = factory.createSelectionSet(); 
			setState(242);
			((SelectionSetContext)_localctx).selection = selection();
			 selectionSet.addSelection(((SelectionSetContext)_localctx).selection.result); 
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__19) | (1L << NAME))) != 0)) {
				{
				{
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(244);
					match(T__9);
					}
					}
					setState(249);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(250);
				((SelectionSetContext)_localctx).selection = selection();
				 selectionSet.addSelection(((SelectionSetContext)_localctx).selection.result); 
				}
				}
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(258);
			match(T__10);
			 ((SelectionSetContext)_localctx).result =  selectionSet; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionContext extends ParserRuleContext {
		public Selection result;
		public FieldContext field;
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public FragmentSpreadContext fragmentSpread() {
			return getRuleContext(FragmentSpreadContext.class,0);
		}
		public InlineFragmentContext inlineFragment() {
			return getRuleContext(InlineFragmentContext.class,0);
		}
		public SelectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selection; }
	}

	public final SelectionContext selection() throws RecognitionException {
		SelectionContext _localctx = new SelectionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_selection);
		try {
			setState(270);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(261);
				((SelectionContext)_localctx).field = field();
				 ((SelectionContext)_localctx).result =  ((SelectionContext)_localctx).field.result; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(264);
				fragmentSpread();
				 ((SelectionContext)_localctx).result =  null; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(267);
				inlineFragment();
				 ((SelectionContext)_localctx).result =  null; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public Field result;
		public Token NAME;
		public SelectionSetContext selectionSet;
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public DirectivesContext directives() {
			return getRuleContext(DirectivesContext.class,0);
		}
		public SelectionSetContext selectionSet() {
			return getRuleContext(SelectionSetContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(272);
				alias();
				}
				break;
			}
			setState(275);
			((FieldContext)_localctx).NAME = match(NAME);
			 System.out.println(((FieldContext)_localctx).NAME); Field field = factory.createField(((FieldContext)_localctx).NAME); 
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(277);
				arguments();
				}
			}

			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(280);
				directives();
				}
			}

			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(283);
				((FieldContext)_localctx).selectionSet = selectionSet();
				 field.setSelectionSeet(((FieldContext)_localctx).selectionSet.result); 
				}
			}

			 ((FieldContext)_localctx).result =  field; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AliasContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public AliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alias; }
	}

	public final AliasContext alias() throws RecognitionException {
		AliasContext _localctx = new AliasContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(NAME);
			setState(291);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FragmentDefinitionContext extends ParserRuleContext {
		public FragmentNameContext fragmentName() {
			return getRuleContext(FragmentNameContext.class,0);
		}
		public TypeConditionContext typeCondition() {
			return getRuleContext(TypeConditionContext.class,0);
		}
		public SelectionSetContext selectionSet() {
			return getRuleContext(SelectionSetContext.class,0);
		}
		public DirectivesContext directives() {
			return getRuleContext(DirectivesContext.class,0);
		}
		public FragmentDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fragmentDefinition; }
	}

	public final FragmentDefinitionContext fragmentDefinition() throws RecognitionException {
		FragmentDefinitionContext _localctx = new FragmentDefinitionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_fragmentDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(T__18);
			setState(294);
			fragmentName();
			setState(295);
			typeCondition();
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(296);
				directives();
				}
			}

			setState(299);
			selectionSet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FragmentSpreadContext extends ParserRuleContext {
		public FragmentNameContext fragmentName() {
			return getRuleContext(FragmentNameContext.class,0);
		}
		public DirectivesContext directives() {
			return getRuleContext(DirectivesContext.class,0);
		}
		public FragmentSpreadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fragmentSpread; }
	}

	public final FragmentSpreadContext fragmentSpread() throws RecognitionException {
		FragmentSpreadContext _localctx = new FragmentSpreadContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_fragmentSpread);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			match(T__19);
			setState(302);
			fragmentName();
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(303);
				directives();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InlineFragmentContext extends ParserRuleContext {
		public SelectionSetContext selectionSet() {
			return getRuleContext(SelectionSetContext.class,0);
		}
		public TypeConditionContext typeCondition() {
			return getRuleContext(TypeConditionContext.class,0);
		}
		public DirectivesContext directives() {
			return getRuleContext(DirectivesContext.class,0);
		}
		public InlineFragmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineFragment; }
	}

	public final InlineFragmentContext inlineFragment() throws RecognitionException {
		InlineFragmentContext _localctx = new InlineFragmentContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_inlineFragment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(T__19);
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(307);
				typeCondition();
				}
			}

			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(310);
				directives();
				}
			}

			setState(313);
			selectionSet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FragmentNameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public FragmentNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fragmentName; }
	}

	public final FragmentNameContext fragmentName() throws RecognitionException {
		FragmentNameContext _localctx = new FragmentNameContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_fragmentName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeConditionContext extends ParserRuleContext {
		public NamedTypeContext namedType() {
			return getRuleContext(NamedTypeContext.class,0);
		}
		public TypeConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeCondition; }
	}

	public final TypeConditionContext typeCondition() throws RecognitionException {
		TypeConditionContext _localctx = new TypeConditionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_typeCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(T__20);
			setState(318);
			namedType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectivesContext extends ParserRuleContext {
		public List<DirectiveContext> directive() {
			return getRuleContexts(DirectiveContext.class);
		}
		public DirectiveContext directive(int i) {
			return getRuleContext(DirectiveContext.class,i);
		}
		public DirectivesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directives; }
	}

	public final DirectivesContext directives() throws RecognitionException {
		DirectivesContext _localctx = new DirectivesContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_directives);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			directive();
			setState(330);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(324);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__9) {
						{
						{
						setState(321);
						match(T__9);
						}
						}
						setState(326);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(327);
					directive();
					}
					} 
				}
				setState(332);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectiveContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public DirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive; }
	}

	public final DirectiveContext directive() throws RecognitionException {
		DirectiveContext _localctx = new DirectiveContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_directive);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			match(T__21);
			setState(334);
			match(NAME);
			setState(336);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(335);
				arguments();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			match(T__12);
			setState(339);
			argument();
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==NAME) {
				{
				{
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(340);
					match(T__9);
					}
					}
					setState(345);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(346);
				argument();
				}
				}
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(352);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			match(NAME);
			setState(355);
			match(T__11);
			setState(356);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(MqlParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(MqlParser.INT, 0); }
		public TerminalNode STRING() { return getToken(MqlParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(MqlParser.BOOLEAN, 0); }
		public TerminalNode NULL() { return getToken(MqlParser.NULL, 0); }
		public EnumValueContext enumValue() {
			return getRuleContext(EnumValueContext.class,0);
		}
		public ListValueContext listValue() {
			return getRuleContext(ListValueContext.class,0);
		}
		public ObjectValueContext objectValue() {
			return getRuleContext(ObjectValueContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_value);
		try {
			setState(367);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				variable();
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(359);
				match(FLOAT);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(360);
				match(INT);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(361);
				match(STRING);
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 5);
				{
				setState(362);
				match(BOOLEAN);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 6);
				{
				setState(363);
				match(NULL);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 7);
				{
				setState(364);
				enumValue();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 8);
				{
				setState(365);
				listValue();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 9);
				{
				setState(366);
				objectValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumValueContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(MqlParser.NAME, 0); }
		public EnumValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumValue; }
	}

	public final EnumValueContext enumValue() throws RecognitionException {
		EnumValueContext _localctx = new EnumValueContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_enumValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListValueContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public ListValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listValue; }
	}

	public final ListValueContext listValue() throws RecognitionException {
		ListValueContext _localctx = new ListValueContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_listValue);
		int _la;
		try {
			setState(389);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				match(T__15);
				setState(372);
				match(T__16);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				match(T__15);
				setState(374);
				value();
				setState(384);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__14) | (1L << T__15) | (1L << STRING) | (1L << FLOAT) | (1L << INT) | (1L << NULL) | (1L << BOOLEAN) | (1L << NAME))) != 0)) {
					{
					{
					setState(378);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__9) {
						{
						{
						setState(375);
						match(T__9);
						}
						}
						setState(380);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(381);
					value();
					}
					}
					setState(386);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(387);
				match(T__16);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectValueContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public ObjectValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectValue; }
	}

	public final ObjectValueContext objectValue() throws RecognitionException {
		ObjectValueContext _localctx = new ObjectValueContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_objectValue);
		int _la;
		try {
			setState(409);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(391);
				match(T__8);
				setState(392);
				match(T__10);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(393);
				match(T__8);
				setState(394);
				argument();
				setState(404);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9 || _la==NAME) {
					{
					{
					setState(398);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__9) {
						{
						{
						setState(395);
						match(T__9);
						}
						}
						setState(400);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(401);
					argument();
					}
					}
					setState(406);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(407);
				match(T__10);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"\u019e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\6\2Z\n\2\r\2\16\2[\3\3\3\3\3\3\5\3a\n\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\6\7w\n"+
		"\7\r\7\16\7x\3\b\3\b\3\b\3\t\3\t\3\t\7\t\u0081\n\t\f\t\16\t\u0084\13\t"+
		"\3\t\7\t\u0087\n\t\f\t\16\t\u008a\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\7\13\u0095\n\13\f\13\16\13\u0098\13\13\3\13\7\13\u009b\n\13\f"+
		"\13\16\13\u009e\13\13\3\13\3\13\3\f\3\f\3\f\6\f\u00a5\n\f\r\f\16\f\u00a6"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00af\n\r\3\16\3\16\3\16\3\16\5\16\u00b5"+
		"\n\16\3\17\3\17\5\17\u00b9\n\17\3\17\5\17\u00bc\n\17\3\17\5\17\u00bf\n"+
		"\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\7\21\u00c8\n\21\f\21\16\21\u00cb"+
		"\13\21\3\21\7\21\u00ce\n\21\f\21\16\21\u00d1\13\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\5\22\u00d9\n\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\5\25\u00e5\n\25\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\7\32\u00f8\n\32\f\32\16\32\u00fb\13"+
		"\32\3\32\3\32\3\32\7\32\u0100\n\32\f\32\16\32\u0103\13\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0111\n\33\3\34"+
		"\5\34\u0114\n\34\3\34\3\34\3\34\5\34\u0119\n\34\3\34\5\34\u011c\n\34\3"+
		"\34\3\34\3\34\5\34\u0121\n\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\5\36\u012c\n\36\3\36\3\36\3\37\3\37\3\37\5\37\u0133\n\37\3 \3 \5"+
		" \u0137\n \3 \5 \u013a\n \3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\7#\u0145\n#\f"+
		"#\16#\u0148\13#\3#\7#\u014b\n#\f#\16#\u014e\13#\3$\3$\3$\5$\u0153\n$\3"+
		"%\3%\3%\7%\u0158\n%\f%\16%\u015b\13%\3%\7%\u015e\n%\f%\16%\u0161\13%\3"+
		"%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u0172\n\'\3("+
		"\3(\3)\3)\3)\3)\3)\7)\u017b\n)\f)\16)\u017e\13)\3)\7)\u0181\n)\f)\16)"+
		"\u0184\13)\3)\3)\5)\u0188\n)\3*\3*\3*\3*\3*\7*\u018f\n*\f*\16*\u0192\13"+
		"*\3*\7*\u0195\n*\f*\16*\u0198\13*\3*\3*\5*\u019c\n*\3*\2\2+\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPR\2\3\3"+
		"\2\36\37\2\u01aa\2Y\3\2\2\2\4]\3\2\2\2\6d\3\2\2\2\bh\3\2\2\2\nl\3\2\2"+
		"\2\fp\3\2\2\2\16z\3\2\2\2\20}\3\2\2\2\22\u008d\3\2\2\2\24\u0091\3\2\2"+
		"\2\26\u00a4\3\2\2\2\30\u00ae\3\2\2\2\32\u00b4\3\2\2\2\34\u00b6\3\2\2\2"+
		"\36\u00c2\3\2\2\2 \u00c4\3\2\2\2\"\u00d4\3\2\2\2$\u00da\3\2\2\2&\u00dd"+
		"\3\2\2\2(\u00e4\3\2\2\2*\u00e6\3\2\2\2,\u00e8\3\2\2\2.\u00ec\3\2\2\2\60"+
		"\u00ef\3\2\2\2\62\u00f2\3\2\2\2\64\u0110\3\2\2\2\66\u0113\3\2\2\28\u0124"+
		"\3\2\2\2:\u0127\3\2\2\2<\u012f\3\2\2\2>\u0134\3\2\2\2@\u013d\3\2\2\2B"+
		"\u013f\3\2\2\2D\u0142\3\2\2\2F\u014f\3\2\2\2H\u0154\3\2\2\2J\u0164\3\2"+
		"\2\2L\u0171\3\2\2\2N\u0173\3\2\2\2P\u0187\3\2\2\2R\u019b\3\2\2\2TZ\5\4"+
		"\3\2UZ\5\6\4\2VZ\5\b\5\2WZ\5\n\6\2XZ\5\f\7\2YT\3\2\2\2YU\3\2\2\2YV\3\2"+
		"\2\2YW\3\2\2\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\\3\3\2\2\2]^"+
		"\7\3\2\2^`\5*\26\2_a\5\16\b\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\5\20\t\2"+
		"c\5\3\2\2\2de\7\4\2\2ef\5*\26\2fg\5\20\t\2g\7\3\2\2\2hi\7\5\2\2ij\5*\26"+
		"\2jk\5\24\13\2k\t\3\2\2\2lm\7\6\2\2mn\5*\26\2no\5\20\t\2o\13\3\2\2\2p"+
		"q\7\7\2\2qr\5*\26\2rs\7\b\2\2sv\5*\26\2tu\7\t\2\2uw\5*\26\2vt\3\2\2\2"+
		"wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\r\3\2\2\2z{\7\n\2\2{|\5*\26\2|\17\3\2"+
		"\2\2}~\7\13\2\2~\u0088\5\22\n\2\177\u0081\7\f\2\2\u0080\177\3\2\2\2\u0081"+
		"\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2"+
		"\2\2\u0084\u0082\3\2\2\2\u0085\u0087\5\22\n\2\u0086\u0082\3\2\2\2\u0087"+
		"\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2"+
		"\2\2\u008a\u0088\3\2\2\2\u008b\u008c\7\r\2\2\u008c\21\3\2\2\2\u008d\u008e"+
		"\7 \2\2\u008e\u008f\7\16\2\2\u008f\u0090\5(\25\2\u0090\23\3\2\2\2\u0091"+
		"\u0092\7\13\2\2\u0092\u009c\7 \2\2\u0093\u0095\7\f\2\2\u0094\u0093\3\2"+
		"\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\u0099\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009b\7 \2\2\u009a\u0096\3\2"+
		"\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u009f\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\7\r\2\2\u00a0\25\3\2\2"+
		"\2\u00a1\u00a2\5\30\r\2\u00a2\u00a3\b\f\1\2\u00a3\u00a5\3\2\2\2\u00a4"+
		"\u00a1\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\27\3\2\2\2\u00a8\u00a9\5\32\16\2\u00a9\u00aa\b\r\1\2\u00aa"+
		"\u00af\3\2\2\2\u00ab\u00ac\5:\36\2\u00ac\u00ad\b\r\1\2\u00ad\u00af\3\2"+
		"\2\2\u00ae\u00a8\3\2\2\2\u00ae\u00ab\3\2\2\2\u00af\31\3\2\2\2\u00b0\u00b1"+
		"\5\62\32\2\u00b1\u00b2\b\16\1\2\u00b2\u00b5\3\2\2\2\u00b3\u00b5\5\34\17"+
		"\2\u00b4\u00b0\3\2\2\2\u00b4\u00b3\3\2\2\2\u00b5\33\3\2\2\2\u00b6\u00b8"+
		"\5\36\20\2\u00b7\u00b9\7 \2\2\u00b8\u00b7\3\2\2\2\u00b8\u00b9\3\2\2\2"+
		"\u00b9\u00bb\3\2\2\2\u00ba\u00bc\5 \21\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc"+
		"\3\2\2\2\u00bc\u00be\3\2\2\2\u00bd\u00bf\5D#\2\u00be\u00bd\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\5\62\32\2\u00c1\35\3\2"+
		"\2\2\u00c2\u00c3\t\2\2\2\u00c3\37\3\2\2\2\u00c4\u00c5\7\17\2\2\u00c5\u00cf"+
		"\5\"\22\2\u00c6\u00c8\7\f\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb\3\2\2\2"+
		"\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00c9"+
		"\3\2\2\2\u00cc\u00ce\5\"\22\2\u00cd\u00c9\3\2\2\2\u00ce\u00d1\3\2\2\2"+
		"\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00cf"+
		"\3\2\2\2\u00d2\u00d3\7\20\2\2\u00d3!\3\2\2\2\u00d4\u00d5\5&\24\2\u00d5"+
		"\u00d6\7\16\2\2\u00d6\u00d8\5(\25\2\u00d7\u00d9\5$\23\2\u00d8\u00d7\3"+
		"\2\2\2\u00d8\u00d9\3\2\2\2\u00d9#\3\2\2\2\u00da\u00db\7\b\2\2\u00db\u00dc"+
		"\5L\'\2\u00dc%\3\2\2\2\u00dd\u00de\7\21\2\2\u00de\u00df\7 \2\2\u00df\'"+
		"\3\2\2\2\u00e0\u00e5\5*\26\2\u00e1\u00e5\5,\27\2\u00e2\u00e5\5.\30\2\u00e3"+
		"\u00e5\5\60\31\2\u00e4\u00e0\3\2\2\2\u00e4\u00e1\3\2\2\2\u00e4\u00e2\3"+
		"\2\2\2\u00e4\u00e3\3\2\2\2\u00e5)\3\2\2\2\u00e6\u00e7\7 \2\2\u00e7+\3"+
		"\2\2\2\u00e8\u00e9\7\22\2\2\u00e9\u00ea\5(\25\2\u00ea\u00eb\7\23\2\2\u00eb"+
		"-\3\2\2\2\u00ec\u00ed\5*\26\2\u00ed\u00ee\7\24\2\2\u00ee/\3\2\2\2\u00ef"+
		"\u00f0\5,\27\2\u00f0\u00f1\7\24\2\2\u00f1\61\3\2\2\2\u00f2\u00f3\7\13"+
		"\2\2\u00f3\u00f4\b\32\1\2\u00f4\u00f5\5\64\33\2\u00f5\u0101\b\32\1\2\u00f6"+
		"\u00f8\7\f\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2"+
		"\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc"+
		"\u00fd\5\64\33\2\u00fd\u00fe\b\32\1\2\u00fe\u0100\3\2\2\2\u00ff\u00f9"+
		"\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102"+
		"\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0105\7\r\2\2\u0105\u0106\b\32"+
		"\1\2\u0106\63\3\2\2\2\u0107\u0108\5\66\34\2\u0108\u0109\b\33\1\2\u0109"+
		"\u0111\3\2\2\2\u010a\u010b\5<\37\2\u010b\u010c\b\33\1\2\u010c\u0111\3"+
		"\2\2\2\u010d\u010e\5> \2\u010e\u010f\b\33\1\2\u010f\u0111\3\2\2\2\u0110"+
		"\u0107\3\2\2\2\u0110\u010a\3\2\2\2\u0110\u010d\3\2\2\2\u0111\65\3\2\2"+
		"\2\u0112\u0114\58\35\2\u0113\u0112\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115"+
		"\3\2\2\2\u0115\u0116\7 \2\2\u0116\u0118\b\34\1\2\u0117\u0119\5H%\2\u0118"+
		"\u0117\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2\2\2\u011a\u011c\5D"+
		"#\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u0120\3\2\2\2\u011d"+
		"\u011e\5\62\32\2\u011e\u011f\b\34\1\2\u011f\u0121\3\2\2\2\u0120\u011d"+
		"\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\b\34\1\2"+
		"\u0123\67\3\2\2\2\u0124\u0125\7 \2\2\u0125\u0126\7\16\2\2\u01269\3\2\2"+
		"\2\u0127\u0128\7\25\2\2\u0128\u0129\5@!\2\u0129\u012b\5B\"\2\u012a\u012c"+
		"\5D#\2\u012b\u012a\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\3\2\2\2\u012d"+
		"\u012e\5\62\32\2\u012e;\3\2\2\2\u012f\u0130\7\26\2\2\u0130\u0132\5@!\2"+
		"\u0131\u0133\5D#\2\u0132\u0131\3\2\2\2\u0132\u0133\3\2\2\2\u0133=\3\2"+
		"\2\2\u0134\u0136\7\26\2\2\u0135\u0137\5B\"\2\u0136\u0135\3\2\2\2\u0136"+
		"\u0137\3\2\2\2\u0137\u0139\3\2\2\2\u0138\u013a\5D#\2\u0139\u0138\3\2\2"+
		"\2\u0139\u013a\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\5\62\32\2\u013c"+
		"?\3\2\2\2\u013d\u013e\7 \2\2\u013eA\3\2\2\2\u013f\u0140\7\27\2\2\u0140"+
		"\u0141\5*\26\2\u0141C\3\2\2\2\u0142\u014c\5F$\2\u0143\u0145\7\f\2\2\u0144"+
		"\u0143\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2"+
		"\2\2\u0147\u0149\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014b\5F$\2\u014a\u0146"+
		"\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d"+
		"E\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150\7\30\2\2\u0150\u0152\7 \2\2"+
		"\u0151\u0153\5H%\2\u0152\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u0153G\3\2"+
		"\2\2\u0154\u0155\7\17\2\2\u0155\u015f\5J&\2\u0156\u0158\7\f\2\2\u0157"+
		"\u0156\3\2\2\2\u0158\u015b\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2"+
		"\2\2\u015a\u015c\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u015e\5J&\2\u015d\u0159"+
		"\3\2\2\2\u015e\u0161\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0162\3\2\2\2\u0161\u015f\3\2\2\2\u0162\u0163\7\20\2\2\u0163I\3\2\2\2"+
		"\u0164\u0165\7 \2\2\u0165\u0166\7\16\2\2\u0166\u0167\5L\'\2\u0167K\3\2"+
		"\2\2\u0168\u0172\5&\24\2\u0169\u0172\7\32\2\2\u016a\u0172\7\33\2\2\u016b"+
		"\u0172\7\31\2\2\u016c\u0172\7\35\2\2\u016d\u0172\7\34\2\2\u016e\u0172"+
		"\5N(\2\u016f\u0172\5P)\2\u0170\u0172\5R*\2\u0171\u0168\3\2\2\2\u0171\u0169"+
		"\3\2\2\2\u0171\u016a\3\2\2\2\u0171\u016b\3\2\2\2\u0171\u016c\3\2\2\2\u0171"+
		"\u016d\3\2\2\2\u0171\u016e\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0170\3\2"+
		"\2\2\u0172M\3\2\2\2\u0173\u0174\7 \2\2\u0174O\3\2\2\2\u0175\u0176\7\22"+
		"\2\2\u0176\u0188\7\23\2\2\u0177\u0178\7\22\2\2\u0178\u0182\5L\'\2\u0179"+
		"\u017b\7\f\2\2\u017a\u0179\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2"+
		"\2\2\u017c\u017d\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u017c\3\2\2\2\u017f"+
		"\u0181\5L\'\2\u0180\u017c\3\2\2\2\u0181\u0184\3\2\2\2\u0182\u0180\3\2"+
		"\2\2\u0182\u0183\3\2\2\2\u0183\u0185\3\2\2\2\u0184\u0182\3\2\2\2\u0185"+
		"\u0186\7\23\2\2\u0186\u0188\3\2\2\2\u0187\u0175\3\2\2\2\u0187\u0177\3"+
		"\2\2\2\u0188Q\3\2\2\2\u0189\u018a\7\13\2\2\u018a\u019c\7\r\2\2\u018b\u018c"+
		"\7\13\2\2\u018c\u0196\5J&\2\u018d\u018f\7\f\2\2\u018e\u018d\3\2\2\2\u018f"+
		"\u0192\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0193\3\2"+
		"\2\2\u0192\u0190\3\2\2\2\u0193\u0195\5J&\2\u0194\u0190\3\2\2\2\u0195\u0198"+
		"\3\2\2\2\u0196\u0194\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0199\3\2\2\2\u0198"+
		"\u0196\3\2\2\2\u0199\u019a\7\r\2\2\u019a\u019c\3\2\2\2\u019b\u0189\3\2"+
		"\2\2\u019b\u018b\3\2\2\2\u019cS\3\2\2\2+Y[`x\u0082\u0088\u0096\u009c\u00a6"+
		"\u00ae\u00b4\u00b8\u00bb\u00be\u00c9\u00cf\u00d8\u00e4\u00f9\u0101\u0110"+
		"\u0113\u0118\u011b\u0120\u012b\u0132\u0136\u0139\u0146\u014c\u0152\u0159"+
		"\u015f\u0171\u017c\u0182\u0187\u0190\u0196\u019b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}