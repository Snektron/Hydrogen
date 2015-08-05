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
		EOperator o1 = EOperator.getOperator(vcode.currentToken());
		
		if (o1 == EOperator.MINUS)
			if (vcode.prevToken().is(EToken.BOOLEAN) || 
				vcode.prevToken().is(EToken.INTEGER) || 
				vcode.prevToken().is(EToken.FLOAT) || 
				vcode.prevToken().is(EToken.VARIABLE) || 
				vcode.prevToken().is(EToken.BRACKET_CLOSE))
				vcode.currentToken().sequence = EOperator.SUBTRACT.regex;
			else
				vcode.currentToken().sequence = EOperator.NEGATION.regex;
		
		EOperator o2;
		
		while (!vcode.opStack().empty() && vcode.opStack().peek().is(EToken.OPERATOR))
		{
			o2 = EOperator.getOperator(vcode.opStack().peek());
			if (EOperator.comparePrecedence(o1, o2))
			{
				vcode.add(new Operation(EOperator.getOperator(vcode.opStack().pop())));
				continue;
			}
			break;
		}
		
		vcode.opStack().push(vcode.currentToken());
	}
	
	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
		vcode.add(new Operation(EOperator.getOperator(token)));
	}
}
