package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class Call extends Instruction
{
	/**
	 * Calls a specific label
	 */
	
	public String label;
	public int arguments;
	public boolean expectsReturn;
	
	public Call(String label, int arguments, boolean expectsReturn)
	{
		this.label = label;
		this.arguments = arguments;
		this.expectsReturn = expectsReturn;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + label + " " + arguments + " " + expectsReturn;
	}
}
