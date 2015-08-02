package hydrogen.vcode.instruction;

public class PushVariable implements IInstruction
{
	/**
	 * This instruction pushes the value of a variable to the stack
	 * @param variable
	 * 	The id of the variable, obtained by VirtualCode.registerVariable();
	 */
	
	public int variable;
	
	public PushVariable(int variable)
	{
		this.variable = variable;
	}
}
