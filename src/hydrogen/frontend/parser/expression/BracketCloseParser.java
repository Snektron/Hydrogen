package hydrogen.frontend.parser.expression;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.token.EToken;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;

public class BracketCloseParser implements IExpressionParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		while (true)
		{
			if (vcode.opStack().empty())
				throw new SyntaxError(vcode.getErrorCode());
			Token top = vcode.opStack().pop();
			
			if (top.is(EToken.BRACKET_OPEN))
				break;
			top.closeExpression(vcode);
		}
	}

	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
		throw new ParseError(Strings.ERROR.msg);
	}
}
