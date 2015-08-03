package hydrogen.frontend.parser.token;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.vcode.VirtualCode;

public class UnexpectedTokenParser implements ITokenParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		throw new ParseError(Strings.UNEXPECTED_TOKEN.f(vcode.currentToken().name()));
	}
}
