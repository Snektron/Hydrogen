package hydrogen.vcode.data;

import java.util.ArrayList;

import hydrogen.frontend.token.EDataType;

public class ConstantAllocator
{
	ArrayList<Constant> constants;
	int constLocCounter;
	
	public ConstantAllocator()
	{
		constants = new ArrayList<Constant>();
		constLocCounter = 0;
	}
	
	public int register(EDataType dataType, Object value)
	{
		for (int i = 0; i<constants.size(); i++)
			if (constants.get(i).is(dataType, value))
				return i;
		constants.add(new Constant(dataType, value, nextLocation()));
		return constants.size()-1;
	}
	
	public Constant get(int id)
	{
		return constants.get(id);
	}
	
	public int nextLocation()
	{
		return constLocCounter++;
	}
}
