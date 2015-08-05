package hydrogen.vcode.instruction;

public class PushVariable extends Instruction
{
	/**
	 * This instruction pushes the value of a variable to the stack
	 * @param variable
	 * 	The id of the variable, obtained by VirtualCode.registerVariable();
	 */
	
	public int variableID;
	
	public PushVariable(int variableID)
	{
		this.variableID = variableID;
	}
}
