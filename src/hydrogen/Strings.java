package hydrogen;

public enum Strings
{
	PARSER_DUMP("Virtual Code dump:"),
	UNEXPECTED_INPUT("Unknown token: %s"),
	UNEXPECTED_TOKEN("Unexpected Token: %s");
	
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
