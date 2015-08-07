package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class Call extends Instruction
{
	/**
	 * Calls a specific label
	 */
	
	String label;
	
	public Call(String label)
	{
		this.label = label;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + label;
	}
}
