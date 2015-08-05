package hydrogen.vcode.data;

import hydrogen.frontend.token.EDataType;

public class Constant
{
	public EDataType type;
	public Object value;
	public int location;
	
	public Constant(EDataType type, Object value, int location)
	{
		this.type = type;
		this.value = value;
		this.location = location;
	}
	
	public boolean is(Constant c)
	{
		return type.equals(c.type) && value.equals(c.value);
	}
	
	public boolean is(EDataType otherType, Object otherValue)
	{
		return type.equals(otherType) && value.equals(otherValue);
	}
}
