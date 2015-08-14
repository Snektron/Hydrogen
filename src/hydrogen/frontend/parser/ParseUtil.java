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
		checkToken(vcode, EToken.BRACKET_CLOSE);
	}
	
	public static void parseBlock(VirtualCode vcode, EToken... end)
	{
		vcode.nextToken();
		vcode.valloc().blockOpen();
		while(!vcode.currentToken().isOneOf(end))
			Parser.parseNext(vcode);
		vcode.valloc().blockClose();
	}
	
	public static void checkToken(VirtualCode vcode, EToken token)
	{
		if (!vcode.currentToken().is(token))
			throw new SyntaxError(Strings.UNEXPECTED_TOKEN_EXPECTED.f(vcode.currentToken().name(), token.name()));
	}
}
