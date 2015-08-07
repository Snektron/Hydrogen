package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class PushConstant extends Instruction
{
	/**
	 * This instruction pushes a constant variable to the stack.
	 */
	
	public int constantID;
	
	public PushConstant(int constantID)
	{
		this.constantID = constantID;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + vcode.calloc().get(constantID).value;
	}
}
