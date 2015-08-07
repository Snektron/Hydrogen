package hydrogen.frontend.token;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.error.SyntaxError;

public enum EOperator
{
	MINUS("-", -1, null, true),
	LOGICAL_AND("&&", 0, Associativity.LEFT, true),
	LOGICAL_OR("\\|\\|", 0, Associativity.LEFT, true),
	NEGATION("_", 4, Associativity.RIGHT, false),
	BITWISE_NOT("!", 4, Associativity.RIGHT, true),
	MULTIPLY("\\*", 3, Associativity.LEFT, true),
	DIVIDE("\\/", 3, Associativity.LEFT, true),
	ADD("\\+", 2, Associativity.LEFT, true),
	SUBTRACT("=", 2, Associativity.LEFT, false),
	MODULO("%", 2, Associativity.LEFT, true),
	BITWISE_AND("&", 2, Associativity.LEFT, true),
	BITWISE_OR("\\|", 2, Associativity.LEFT, true),
	BITWISE_XOR("\\^", 2, Associativity.LEFT, true),
	EQUALS("==", 1, Associativity.LEFT, true),
	LESS("<", 1, Associativity.LEFT, true),
	GREATER(">", 1, Associativity.LEFT, true),
	LESS_OR_EQUALS("<=", 1, Associativity.LEFT, true),
	GREATER_OR_EQUALS(">=", 1, Associativity.LEFT, true),
	NOT_EQUALS("!=", 1, Associativity.LEFT, true);
	
	public String regex;
	int precedence;
	Associativity associativity;
	boolean canMatch;
	
	private EOperator(String regex, int precedence, Associativity associativity, boolean canMatch)
	{
		this.regex = regex;
		this.precedence = precedence;
		this.associativity = associativity;
		this.canMatch = canMatch;
	}
	
	public boolean isLeftAssociative()
	{
		return associativity == Associativity.LEFT;
	}
	
	public static boolean comparePrecedence(EOperator o1, EOperator o2)
	{
		return (o1.associativity == Associativity.LEFT && o1.precedence <= o2.precedence) || (o1.associativity == Associativity.RIGHT && o1.precedence < o2.precedence);
	}
	
	public static EOperator getOperator(Token token)
	{
		if (token.token != EToken.OPERATOR)
			throw new SyntaxError(Strings.OPERATOR_SYNTAX_ERROR.f(token.name()));
		
		for (int i=0; i<values().length; i++)
			if (token.sequence.matches(values()[i].regex))
				return values()[i];
		
		// this shouldn't happen
		throw new ParseError(Strings.OPERATOR_PARSE_ERROR.f(token.sequence));
	}
	
	public static String getOperators()
	{
		StringBuilder sb = new StringBuilder(")");
		for (int i=0; i<values().length; i++)
			if (values()[i].canMatch)
				sb.insert(0, values()[i].regex).insert(0, "|");
		return "(" + sb.substring(1).toString();
	}
	
	public static boolean allowedAfterOperator(Token tok)
	{
		return !(tok.is(EToken.OPERATOR) && getOperator(tok).isLeftAssociative());
	}
	
	public enum Associativity
	{
		LEFT, RIGHT;
	}
}
