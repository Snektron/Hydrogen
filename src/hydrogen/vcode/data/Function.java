package hydrogen.vcode.data;

public class Function
{
	public String name;
	public int arguments;
	public boolean returnsValue;
	
	public Function(String name, int arguments, boolean returnsValue)
	{
		this.name = name;
		this.arguments = arguments;
		this.returnsValue = returnsValue;
	}
	
	public boolean is(Function f)
	{
		return name.equals(f.name) && arguments == f.arguments;
	}
	
	public boolean is(String otherName, int otherArguments)
	{
		return name.equals(otherName) && arguments == otherArguments;
	}
}
