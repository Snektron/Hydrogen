package hydrogen.frontend.token;

public enum EDataType
{
	//TODO sizes in bytes
	
	BOOLEAN("boolean", false)
	{
		@Override
		public Object parse(Token tok)
		{
			return Boolean.parseBoolean(tok.sequence);
		}
	},
	
	INTEGER("int", false)
	{
		@Override
		public Object parse(Token tok)
		{
			return Integer.parseInt(tok.sequence);
		}
	},
	
	FLOAT("float", false)
	{
		@Override
		public Object parse(Token tok)
		{
			return Float.parseFloat(tok.sequence);
		}
	},
	
	VOID("void", true);
	
	public String syntax;
	public boolean returnOnly;
	
	EDataType(String syntax, boolean returnOnly)
	{
		this.syntax = syntax;
		this.returnOnly = returnOnly;
	}
	
	public Object parse(Token tok)
	{
		return null;
	}
	
	public static String getModifierTypes()
	{
		StringBuilder sb = new StringBuilder(")");
		for (int i=0; i<values().length; i++)
			if (!values()[i].returnOnly)
				sb.insert(0, values()[i].syntax).insert(0, "|");
		return "(" + sb.substring(1).toString();
	}
	
	public static String getReturnTypes()
	{
		StringBuilder sb = new StringBuilder(")");
		for (int i=0; i<values().length; i++)
			sb.insert(0, values()[i].syntax).insert(0, "|");
		return "(" + sb.substring(1).toString();
	}
}
