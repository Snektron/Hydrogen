package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class Return extends Instruction
{
	/**
	 * Return to the code location on the stack.
	 */
	
	public Return()
	{

	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName();
	}
}
