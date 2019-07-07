grammar Mql;

@parser::header
{
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.company.mql.ast.*;
}

@parser::members
{
private AstFactory factory = new AstFactory();

public Document getDocument() {
    return factory.getDocument();
}
}

schema
    : (typeDefinition | inputTypeDefinition | enumDefinition | interfaceDefinition | unionDefinition)+
    ;

typeDefinition
    : 'type' namedType interfaceImplementation? fieldDefinitions
    ;

inputTypeDefinition
    : 'input' namedType fieldDefinitions
    ;

enumDefinition
    : 'enum' namedType enumFields
    ;

interfaceDefinition
    : 'interface' namedType fieldDefinitions
    ;

unionDefinition
    : 'union' namedType '=' namedType ('|' namedType)+
    ;

interfaceImplementation
    : 'implements' namedType
    ;

fieldDefinitions
    : '{' fieldDefinition (','* fieldDefinition)* '}'
    ;

fieldDefinition
    : NAME ':' dataType
    ;

enumFields
    : '{' NAME (','* NAME)* '}'
    ;


/* Query document rules */

document
    :(
       definition { factory.addDefinition($definition.result); }
     )+
    ;

definition returns [Definition result]
    : operationDefinition { $result = $operationDefinition.result; }
    | fragmentDefinition { $result = null; }
    ;

operationDefinition returns [Definition result]
    : selectionSet { $result = factory.createOperationDefinition($selectionSet.result); }
    | namedOperationDefinition
    ;

namedOperationDefinition
    : operationType NAME? variableDefinitions? directives? selectionSet
    ;

operationType
    : QUERY | MUTATION
    ;

variableDefinitions
    : '(' variableDefinition (','* variableDefinition)* ')'
    ;

variableDefinition
    : variable ':' dataType defaultValue?
    ;

defaultValue
    : '=' value
    ;

variable
    : '$' NAME
    ;

dataType
    : namedType | listType | nonNullNamedType | nonNullListType
    ;

namedType
    : NAME
    ;

listType
    : '[' dataType ']'
    ;

nonNullNamedType
    : namedType '!'
    ;

nonNullListType
    : listType '!'
    ;

selectionSet returns [SelectionSet result]
    : '{' { SelectionSet selectionSet = factory.createSelectionSet(); }
      selection { selectionSet.addSelection($selection.result); }
      (','*
      selection { selectionSet.addSelection($selection.result); }
      )*
      '}' { $result = selectionSet; }
    ;

selection returns [Selection result]
    : field { $result = $field.result; }
    | fragmentSpread { $result = null; }
    | inlineFragment { $result = null; }
    ;

field returns [Field result]
    : alias? NAME { System.out.println($NAME); Field field = factory.createField($NAME); }
      arguments?
      directives?
      (selectionSet { field.setSelectionSeet($selectionSet.result); })?
      { $result = field; }
    ;

alias
    : NAME ':'
    ;

fragmentDefinition
    : 'fragment' fragmentName typeCondition directives? selectionSet
    ;

fragmentSpread
    : '...' fragmentName directives?
    ;

inlineFragment
    : '...' typeCondition? directives? selectionSet
    ;

fragmentName
    : NAME
    ;

typeCondition
    : 'on' namedType
    ;

directives
    : directive (','* directive)*
    ;

directive
    : '@' NAME arguments?
    ;

arguments
    : '(' argument (','* argument)* ')'
    ;

argument
    : NAME ':' value
    ;

value
    : variable | FLOAT | INT | STRING | BOOLEAN | NULL | enumValue | listValue | objectValue
    ;

enumValue
    : NAME
    ;

listValue
    : '[' ']' | '[' value (','* value)* ']'
    ;

objectValue
    : '{' '}' | '{' argument (','* argument)* '}'
    ;

STRING
    : '"' (ESCAPED_CHAR | ESCAPED_UNICODE | ~["\\])* '"'
    ;
fragment ESCAPED_CHAR
    : '\\' ["\\/bfnrt]
    ;
fragment ESCAPED_UNICODE
    : '\\u' HEX_CHAR HEX_CHAR HEX_CHAR HEX_CHAR
    ;
fragment HEX_CHAR
    : [0-9a-fA-F]
    ;
FLOAT
    : INT '.' [0-9]+ EXPONENT? | INT EXPONENT | INT
    ;
INT
    : '-'? ('0' | [1-9][0-9]*)
    ;
fragment EXPONENT
    : [Ee] '+'? INT
    ;
NULL
    : 'null'
    ;
BOOLEAN
    : 'true' | 'false'
    ;
QUERY
    : 'query'
    ;
MUTATION
    : 'mutation'
    ;
NAME
    : [_A-Za-z][_0-9A-Za-z]*
    ;
WS
    : [ \t\n\r]+ -> channel(HIDDEN)
    ;
COMMENT
    : '#' ~[\r\n]* -> channel(HIDDEN)
    ;