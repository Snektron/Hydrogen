package hydrogen.frontend.parser;

import hydrogen.Strings;
import hydrogen.frontent.error.ParseError;
import hydrogen.vcode.VirtualCode;

public class UnexpectedTokenParser implements IParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		throw new ParseError(Strings.UNEXPECTED_TOKEN.f(vcode.currentToken().name()));
	}
}
