package hydrogen.frontend.parser.expression;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;

public class CallParser implements IExpressionParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		
	}

	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
		throw new ParseError(Strings.TOKEN_ERROR.f(token.name()));
	}
}
