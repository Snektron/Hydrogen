package hydrogen.frontend.token;

public enum EOperator
{
	LOGICAL_AND("&&", 0),
	LOGICAL_OR("||", 0, "\\|\\|"),
	MULTIPLY("*", 4, "\\*"),
	DIVIDE("/", 4, "\\/"),
	ADD("+", 3, "\\+"),
	SUBTRACT("-", 3),
	MODULO("%", 3),
	BITWISE_AND("&", 2),
	BITWISE_OR("|", 2, "\\|"),
	BITWISE_XOR("^", 2, "\\^"),
	EQUALS("==", 1),
	LESS("<", 1),
	GREATER(">", 1),
	LESS_OR_EQUALS("<=", 1),
	GREATER_OR_EQUALS(">=", 1),
	NOT_EQUALS("!=", 1);
	
	String operator, regex;
	public int precedence;
	
	private EOperator(String operator, int precedence)
	{
		this.operator = operator;
		this.regex = operator;
		this.precedence = precedence;
	}
	
	private EOperator(String operator, int precedence, String specialRegex)
	{
		this.operator = operator;
		this.regex = specialRegex;
		this.precedence = precedence;
	}
}
