package hydrogen.frontend.parser.expression;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.parser.MatchUtil;
import hydrogen.frontend.token.EToken;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.data.FunctionAllocator;
import hydrogen.vcode.instruction.Call;

public class CallParser implements IExpressionParser
{
	public boolean expectsValue;
	
	public CallParser(boolean expectsValue)
	{
		this.expectsValue = expectsValue;
	}
	
	@Override
	public void parse(VirtualCode vcode)
	{
		String seq = vcode.currentToken().sequence;
		
		Matcher m = Pattern.compile(MatchUtil.NAME).matcher(seq);
		if (!m.find())
			throw new ParseError(Strings.ERROR.msg);
		String name = m.group();
		
		Stack<Token> bOpStack = new Stack<Token>();
		
		for (Token t:vcode.opStack())
			bOpStack.push(new Token(t));
		
		int arguments = 0;
		
		vcode.nextToken();
		
		if (!(vcode.currentToken().is(EToken.BRACKET_CLOSE)))
		{
			ExpressionParser.parse(vcode);
			arguments = 1;
			
			while(vcode.currentToken().is(EToken.ARGUMENT_SEPERATOR))
			{
				vcode.nextToken();
				ExpressionParser.parse(vcode);
				arguments++;
			}
		
			if (!(vcode.currentToken().is(EToken.BRACKET_CLOSE)))
				throw new SyntaxError(vcode.getErrorCode());
		}
		
		vcode.opStack().clear();
		for (Token t:bOpStack)
			vcode.opStack().push(new Token(t));
		
		vcode.add(new Call(FunctionAllocator.makeLabel(name, arguments), arguments, expectsValue));
	}

	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
	
	}
}
