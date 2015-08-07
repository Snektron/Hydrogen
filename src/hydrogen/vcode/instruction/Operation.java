package hydrogen.vcode.instruction;

import hydrogen.frontend.token.EOperator;
import hydrogen.vcode.VirtualCode;

public class Operation extends Instruction
{
	/**
	 * This instruction is an operator, and will execute the operator:
	 * pop 2 values
	 * perform operation
	 * push result
	 */
	
	public EOperator operator;
	
	public Operation(EOperator operator)
	{
		this.operator = operator;
	}
	
	@Override
	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName() + " " + operator.name();
	}
}
