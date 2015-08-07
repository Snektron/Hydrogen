package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class Assignment extends Instruction
{
	
	/**
	 * Assigns the value at the top of the stack to the variable
	 */
	
	int variableID;
	
	public Assignment(int variableID)
	{
		this.variableID = variableID;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + vcode.valloc().get(variableID).name;
	}
}
