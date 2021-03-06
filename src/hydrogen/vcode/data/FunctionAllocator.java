package hydrogen.vcode.data;

import java.util.ArrayList;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.error.RedefinitionError;

public class FunctionAllocator
{
	ArrayList<Function> functions;
	
	public FunctionAllocator()
	{
		functions = new ArrayList<Function>();
	}
	
	public int register(String name, int arguments, boolean returnsValue)
	{
		if (isRegistered(name, arguments))
			throw new RedefinitionError(Strings.FUNCTION_REDEFINITION.f(name));
		
		functions.add(new Function(name, arguments, returnsValue));
		return functions.size()-1;
	}
	
	public boolean isRegistered(String name, int arguments)
	{
		for (Function f:functions)
			if (f.is(name, arguments))
				return true;
		return false;
	}
	
	public int getByNameAndArgs(String name, int arguments)
	{
		for (int i = 0; i < functions.size(); i++)
			if (functions.get(i).is(name, arguments))
				return i;
		throw new ParseError(Strings.UNRESOLVED_FUNCTION.f(name, arguments+""));
	}
	
	public Function get(int id)
	{
		return functions.get(id);
	}
	
	public static String makeLabel(String name, int arguments)
	{
		return "FUNCTION_"+name+"_"+arguments;
	}
}
