package hydrogen.vcode.variable;

import hydrogen.vcode.instruction.IInstruction;

public class Constant implements IInstruction
{
	EDataType type;
	String value;
	
	public Constant(EDataType type, String value)
	{
		this.type = type;
		this.value = value;
	}
}
