package hydrogen.frontend.parser.expression;

import hydrogen.frontend.parser.token.ITokenParser;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;

public interface IExpressionParser extends ITokenParser
{
	public void closeExpression(Token token, VirtualCode vcode);
}
