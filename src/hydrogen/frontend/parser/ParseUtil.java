package hydrogen.frontend.parser;

import hydrogen.Strings;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.parser.expression.ExpressionParser;
import hydrogen.frontend.token.EToken;
import hydrogen.vcode.VirtualCode;

public class ParseUtil
{
	public static void parseStatement(VirtualCode vcode)
	{
		vcode.nextToken();
		ExpressionParser.parse(vcode);
		if (!vcode.currentToken().is(EToken.BRACKET_CLOSE))
			throw new SyntaxError(Strings.UNEXPECTED_TOKEN_EXPECTED.f(vcode.currentToken().name(), EToken.BRACKET_CLOSE.name()));
	}
	
	public static void parseBlock(VirtualCode vcode, EToken... end)
	{
		vcode.nextToken();
		vcode.valloc().blockOpen();
		while(!vcode.currentToken().isOneOf(end))
			Parser.parseNext(vcode);
		vcode.valloc().blockClose();
	}
}
