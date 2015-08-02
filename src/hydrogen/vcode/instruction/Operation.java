package hydrogen.vcode.instruction;

import hydrogen.frontend.token.EOperator;

public class Operation implements IInstruction
{
	/**
	 * This instruction is an operator, and will execute the operator.
	 */
	
	public EOperator operator;
	
	public Operation(EOperator operator)
	{
		this.operator = operator;
	}
}
