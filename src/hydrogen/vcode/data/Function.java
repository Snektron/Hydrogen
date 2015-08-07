package hydrogen.vcode.data;

public class Function
{
	String name;
	int arguments;
	
	public Function(String name, int arguments)
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
