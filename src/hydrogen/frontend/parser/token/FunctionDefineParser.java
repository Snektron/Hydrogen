package hydrogen.frontend.parser.token;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.parser.MatchUtil;
import hydrogen.frontend.parser.ParseUtil;
import hydrogen.frontend.parser.Parser;
import hydrogen.frontend.parser.expression.ExpressionParser;
import hydrogen.frontend.token.EToken;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.data.FunctionAllocator;
import hydrogen.vcode.instruction.Jump;
import hydrogen.vcode.instruction.Jump.Condition;
import hydrogen.vcode.instruction.Label;
import hydrogen.vcode.instruction.PopVariable;
import hydrogen.vcode.instruction.Return;

public class FunctionDefineParser implements ITokenParser
{
	int functionID = 0;
	
	@Override
	public void parse(VirtualCode vcode)
	{
		if (vcode.valloc().inFunction())
			throw new ParseError(Strings.FUNCTION_INSIDE_FUNCTION.msg);
		
		String lbl = nextLabel(functionID++);
		vcode.add(new Jump(lbl, Condition.NONE));
		
		String seq = vcode.currentToken().sequence;
		seq = seq.replaceFirst("function\\s+", "");
		Matcher m = Pattern.compile(MatchUtil.NAME).matcher(seq);
		if (!m.find())
			throw new ParseError(Strings.ERROR.msg);
		String name = m.group();
		
		vcode.nextToken();
		vcode.valloc().openFunction();
		
		int arguments = 0;
		
		Stack<Integer> parameters = new Stack<Integer>();
		
		while (vcode.currentToken().is(EToken.VARIABLE))
		{
			parameters.push(vcode.valloc().register(vcode.currentToken().sequence));
			arguments++;
			vcode.nextToken();
			
			if (vcode.currentToken().is(EToken.BRACKET_CLOSE))
				break;
			ParseUtil.checkToken(vcode, EToken.ARGUMENT_SEPERATOR);
			vcode.nextToken();
		}
		ParseUtil.checkToken(vcode, EToken.BRACKET_CLOSE);
		
		
		String fname = FunctionAllocator.makeLabel(name, arguments);
		vcode.add(new Label(fname));
		vcode.nextToken();
		
		while(!parameters.empty())
			vcode.add(new PopVariable(parameters.pop()));
		
		boolean returnsValue = parseBlock(vcode);
		vcode.falloc().register(fname, arguments, returnsValue);
		
		vcode.valloc().closeFunction();
		
		ParseUtil.checkToken(vcode, EToken.END);
		
		vcode.add(new Return(false));
		vcode.add(new Label(lbl));
		if (vcode.hasCode())
			vcode.nextToken();
	}
	
	private boolean parseBlock(VirtualCode vcode)
	{
		Boolean returnsValue = null;
		
		while(!vcode.currentToken().is(EToken.END))
		{
			if (vcode.currentToken().is(EToken.RETURN_VALUE))
			{
				if (returnsValue != null && returnsValue == false)
					throw new ParseError(Strings.ERROR.msg);
				returnsValue = true;
				vcode.nextToken();
				ExpressionParser.parse(vcode);
				vcode.add(new Return(true));
				ParseUtil.checkToken(vcode, EToken.BRACKET_CLOSE);
				vcode.nextToken();
				continue;
			}else if(vcode.currentToken().is(EToken.RETURN))
			{
				if (returnsValue != null && returnsValue == true)
					throw new ParseError(Strings.ERROR.msg);
				returnsValue = false;
				vcode.add(new Return(false));
				vcode.nextToken();
				continue;
			}
			Parser.parseNext(vcode);
		}
		if (returnsValue == null)
			returnsValue = false;
		
		return returnsValue.booleanValue();
	}
	
	private String nextLabel(int id)
	{
		return "FUNCTION_END_"+id;
	}
}
