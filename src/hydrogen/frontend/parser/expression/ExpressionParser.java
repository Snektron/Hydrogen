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
			Token next = vcode.nextToken();

			if (vcode.prevToken().isOneOf(EToken.OPERATOR, EToken.BRACKET_OPEN, EToken.CALL, EToken.ARGUMENT_SEPERATOR))
			{
				if (!EOperator.allowedAfterOperator(next) || !next.allowedInExpression())
					throw new SyntaxError(vcode.getErrorCode());
			}
			else if (!next.allowedInExpression() || next.is(EToken.CALL))
				break;
			
			if ((next.is(EToken.BRACKET_CLOSE) && !hasOpStackOpenBracket(vcode)) || next.is(EToken.ARGUMENT_SEPERATOR))
				break;
		}
		
		while (!vcode.opStack().empty())
			vcode.opStack().pop().closeExpression(vcode);
	}
	
	private static boolean hasOpStackOpenBracket(VirtualCode vcode)
	{
		for (Token t:vcode.opStack())
			if (t.token == EToken.BRACKET_OPEN)
				return true;
		return false;
	}
}
