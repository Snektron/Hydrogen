package hydrogen.frontend.parser.expression;

import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.token.EOperator;
import hydrogen.frontend.token.EToken;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;

public class ExpressionParser
{
	public static void parse(VirtualCode vcode)
	{
		vcode.opStack().clear();
		
		while(true)
		{
			vcode.currentToken().parseExpression(vcode);
			if (!vcode.hasCode())
				break;
			Token peek = vcode.peekToken();
			
			if (vcode.currentToken().isOneOf(EToken.OPERATOR, EToken.BRACKET_OPEN, EToken.CALL, EToken.ARGUMENT_SEPERATOR) &&
				(!EOperator.allowedAfterOperator(peek) || !peek.allowedInExpression()))
				throw new SyntaxError(vcode.getErrorCode());
			else if (!peek.allowedInExpression() || peek.is(EToken.CALL))
				break;
			
			vcode.nextToken();
		}
		
		while (!vcode.opStack().empty())
			vcode.opStack().pop().closeExpression(vcode);
	}
}
