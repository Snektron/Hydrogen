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
	UNRESOLVED_VARIABLE_ERROR("Variable %s can not be found."),
	VARIABLE_REDEFINITION("Duplicate variable %s."),
	ILLEGAL_TOKEN("Illegal token %s in expression."),
	TOKEN_ERROR("Error under token %s."),
	FUNCTION_REDEFINITION("Duplicate function %s"),
	UNRESOLVED_FUNCTION_ERROR("Function %s can not be found."),
	END_OF_FILE("Unexpected end of file."),
	ERROR("Unexpected error."),
	DATATYPE_PARSE_ERROR("Sequence '%s' is not a data type.");
	
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
