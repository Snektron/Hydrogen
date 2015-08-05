package hydrogen.vcode.data;

import hydrogen.frontend.token.EDataType;

public class Function
{
	String name;
	int arguments;
	
	public Function(String name, int arguments, EDataType returnType)
	{
		this.name = name;
		this.arguments = arguments;
	}
	
	public boolean is(Function f)
	{
		return name.equals(f.name) && arguments == f.arguments;
	}
	
	public boolean is(String otherName, int otherArguments)
	{
		return otherName.equals(otherName) && arguments == otherArguments;
	}
}
