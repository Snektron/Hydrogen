package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class Call extends Instruction
{
	/**
	 * Calls a specific label
	 */
	
	public String label;
	public int arguments;
	
	public Call(String label, int arguments)
	{
		this.label = label;
		this.arguments = arguments;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + label + " " + arguments;
	}
}
