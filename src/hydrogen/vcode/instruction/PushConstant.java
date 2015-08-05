package hydrogen.vcode.instruction;

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
}
