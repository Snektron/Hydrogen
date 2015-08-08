package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class Return extends Instruction
{
	/**
	 * Return to the code location on the stack.
	 * @param hasValue
	 *  if this return statement should return a value from the stack.
	 */
	
	boolean hasValue;
	
	public Return(boolean hasValue)
	{
		this.hasValue = hasValue;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + hasValue;
	}
}
