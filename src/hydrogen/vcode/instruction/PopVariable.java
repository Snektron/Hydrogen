package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class PopVariable extends Instruction
{
	
	/**
	 * Assigns the value at the top of the stack to the variable
	 */
	
	int variableID;
	
	public PopVariable(int variableID)
	{
		this.variableID = variableID;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + vcode.valloc().get(variableID).name;
	}
}
