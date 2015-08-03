package hydrogen.vcode.variable;

import hydrogen.frontend.token.EDataType;

public class Constant
{
	public EDataType type;
	public Object value;
	
	public Constant(EDataType type, Object value)
	{
		this.type = type;
		this.value = value;
	}
}
