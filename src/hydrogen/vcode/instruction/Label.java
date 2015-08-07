package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class Label extends Instruction
{
	/**
	 * Defines a label. Doesn't add anything to execution.
	 */
	
	String name;
	
	public Label(String name)
	{
		this.name = name;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + name;
	}
}
