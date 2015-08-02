package hydrogen.vcode.variable;

public enum EDataType
{
	BOOLEAN("boolean", false),
	BYTE("byte", false),
	INTEGER("int", false),
	FLOAT("float", false),
	VOID("void", true);
	
	public String syntax;
	public boolean returnOnly;
	
	EDataType(String syntax, boolean returnOnly)
	{
		this.syntax = syntax;
		this.returnOnly = returnOnly;
	}
}
