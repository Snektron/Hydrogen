package hydrogen.vcode.instruction;

import hydrogen.frontend.token.EOperator;

public class Operator implements IInstruction
{
	/**
	 * This instruction is an operator, and will execute the operator.
	 */
	
	public EOperator operator;
	
	public Operator(EOperator operator)
	{
		this.operator = operator;
	}
}
