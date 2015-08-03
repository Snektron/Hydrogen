package hydrogen.frontend.parser.expression;

import hydrogen.Strings;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;

public class IllegalTokenParser implements IExpressionParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		throw new SyntaxError(Strings.ILLEGAL_TOKEN.f(vcode.currentToken().name()));
	}

	@Override
	public void closeExpression(Token t, VirtualCode vcode)
	{
		throw new SyntaxError(Strings.ILLEGAL_TOKEN.f(vcode.currentToken().name()));
	}
}
