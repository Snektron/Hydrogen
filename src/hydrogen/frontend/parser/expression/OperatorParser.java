package hydrogen.frontend.parser.expression;

import hydrogen.frontend.token.EOperator;
import hydrogen.frontend.token.EToken;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.Operation;

public class OperatorParser implements IExpressionParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		EOperator o1 = vcode.currentToken().getOperator();
		
		while (!vcode.opStack().empty() && vcode.opStack().peek().is(EToken.OPERATOR) && o1.precedence <= vcode.opStack().peek().getOperator().precedence)
			vcode.add(new Operation(vcode.opStack().pop().getOperator()));
		vcode.opStack().push(vcode.currentToken());
	}

	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
		vcode.add(new Operation(token.getOperator()));
	}
}
