package hydrogen.vcode.variable;

public enum DataType
{
	BOOLEAN("boolean", false),
	BYTE("byte", false),
	INT("int", false),
	FLOAT("float", false),
	VOID("void", true);
	
	public String syntax;
	public boolean returnOnly;
	
	DataType(String syntax, boolean returnOnly)
	{
		this.syntax = syntax;
		this.returnOnly = returnOnly;
	}
}
