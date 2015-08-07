package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class PushConstant extends Instruction
{
	/**
	 * This instruction pushes a constant variable to the stack.
	 */
	
	public int constant;
	
	public PushConstant(int constant)
	{
		this.constant = constant;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + constant;
	}
}
