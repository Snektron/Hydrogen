package hydrogen.vcode.instruction;

import hydrogen.vcode.variable.Constant;

public class PushConstant extends Instruction
{
	/**
	 * This instruction pushes a constant variable to the stack.
	 */
	
	public Constant constant;
	
	public PushConstant(Constant constant)
	{
		this.constant = constant;
	}
}
