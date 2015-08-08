package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class Jump extends Instruction
{
	/**
	 * Jumps to a label
	 */
	
	String label;
	Condition condition;
	
	public Jump(String label, Condition condition)
	{
		this.label = label;
		this.condition = condition;
	}
	
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + condition.name() + " " + label;
	}
	
	public enum Condition
	{
		NONE, FALSE, TRUE;
	}
}
