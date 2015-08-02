package hydrogen.frontend.parser;

import java.util.Stack;

import hydrogen.Strings;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;

public class ExpressionParser
{
	public static void parse(VirtualCode vcode)
	{
		Token tok;
		Stack<Token> operatorStack = new Stack<Token>();
		
		while(vcode.hasCode())
		{
			tok = vcode.nextToken();
			if (!tok.allowedInExpression())
				throw new SyntaxError(Strings.SYNTAX_ERROR.f(vcode.currentToken().name()));
			
			switch(tok.token)
			{
			case BOOLEAN:
			case FLOAT:
			case INTEGER:
				break;
			case VARIABLE:
				break;
			case CALL:
				break;
			case ARGUMENT_SEPERATOR:
				break;
			case OPERATOR:
				break;
			case BRACKET_OPEN:
				break;
			case BRACKET_CLOSE:
				break;
			default:
				throw new SyntaxError(Strings.SYNTAX_ERROR.f(vcode.currentToken().name()));
			}
		}
	}
}
