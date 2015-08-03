package hydrogen;

public enum Strings
{
	PARSER_DUMP("Virtual Code dump:"),
	UNEXPECTED_INPUT("Unknown token: %s"),
	UNEXPECTED_TOKEN("Unexpected Token: %s"),
	SYNTAX_ERROR("%s"),
	OPERATOR_SYNTAX_ERROR("Token '%s' is not an operator."),
	OPERATOR_PARSE_ERROR("Sequence '%s' is not an operator."),
	UNRESOLVED_VARIABLE("%s can not be resolved."),
	UNRESOLVED_VARIABLE_ERROR("%s can not be found."),
	DUPLICATE_VARIABLE("Duplicate variable %s."),
	ILLEGAL_TOKEN("Illegal token %s in expression."),
	TOKEN_ERROR("Error under token %s.");
	
	public String msg;
	
	Strings(String msg)
	{
		this.msg = msg;
	}
	
	public String f(Object... format)
	{
		return String.format(msg, format);
	}
}
