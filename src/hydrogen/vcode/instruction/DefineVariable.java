package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class DefineVariable extends Instruction
{
	/**
	 * Define a new variable at this location.
	 */
	
	int variableID;
	
	public DefineVariable(int variableID)
	{
		this.variableID = variableID;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + vcode.valloc().get(variableID).name;
	}
}
