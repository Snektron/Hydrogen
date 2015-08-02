package hydrogen.frontend.parser;

import java.util.Stack;

import hydrogen.Strings;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.PushConstant;
import hydrogen.vcode.instruction.PushVariable;
import hydrogen.vcode.variable.Constant;
import hydrogen.vcode.variable.EDataType;

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
				vcode.add(new PushConstant(new Constant(EDataType.BOOLEAN, tok.sequence)));
				break;
			case FLOAT:
				vcode.add(new PushConstant(new Constant(EDataType.FLOAT, tok.sequence)));
				break;
			case INTEGER:
				vcode.add(new PushConstant(new Constant(EDataType.INTEGER, tok.sequence)));
				break;
			case VARIABLE:
				vcode.add(new PushVariable(vcode.valloc().getByName(tok.sequence)));
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
