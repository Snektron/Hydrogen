package hydrogen.vcode.instruction;

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
}
