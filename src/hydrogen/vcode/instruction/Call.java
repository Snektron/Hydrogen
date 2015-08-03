package hydrogen.vcode.instruction;

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
}
