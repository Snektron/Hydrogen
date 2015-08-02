package hydrogen.vcode.instruction;

import hydrogen.vcode.variable.Constant;

public class PushConstant implements IInstruction
{
	/**
	 * This instruction pushes a constant variable to the stack.
	 */
	
	public Constant value;
	
	public PushConstant(Constant value)
	{
		this.value = value;
	}
}
