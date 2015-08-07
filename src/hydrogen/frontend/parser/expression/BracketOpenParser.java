package hydrogen.frontend.parser.expression;

import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;

public class BracketOpenParser implements IExpressionParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		vcode.opStack().push(vcode.currentToken());
	}

	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
		
	}
}
