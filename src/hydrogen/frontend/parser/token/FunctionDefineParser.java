package hydrogen.frontend.parser.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.parser.MatchUtil;
import hydrogen.frontend.parser.Parser;
import hydrogen.frontend.token.EToken;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.data.FunctionAllocator;
import hydrogen.vcode.instruction.Jump;
import hydrogen.vcode.instruction.Jump.Condition;
import hydrogen.vcode.instruction.Label;
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
		
		while (vcode.currentToken().is(EToken.VARIABLE))
		{
			vcode.valloc().register(vcode.currentToken().sequence);
			arguments++;
			vcode.nextToken();
			if (vcode.currentToken().is(EToken.BRACKET_CLOSE))
				break;
			else if (!vcode.currentToken().is(EToken.ARGUMENT_SEPERATOR))
				throw new SyntaxError(Strings.UNEXPECTED_TOKEN.f(vcode.currentToken().name()));
			vcode.nextToken();
		}
		if (!vcode.currentToken().is(EToken.BRACKET_CLOSE))
			throw new SyntaxError(Strings.UNEXPECTED_TOKEN.f(vcode.currentToken().name()));
		String fname = FunctionAllocator.makeLabel(name, arguments);
		vcode.falloc().register(fname, arguments);
		vcode.add(new Label(fname));
		vcode.nextToken();
		
		while(!vcode.currentToken().is(EToken.END))
			Parser.parseNext(vcode);
		
		vcode.valloc().closeFunction();
		
		if (!vcode.currentToken().is(EToken.END))
			throw new SyntaxError(Strings.UNEXPECTED_TOKEN_EXPECTED.f(vcode.currentToken().name(), EToken.END.name()));
		vcode.add(new Return());
		vcode.add(new Label(lbl));
		if (vcode.hasCode())
			vcode.nextToken();
	}
	
	private String nextLabel(int id)
	{
		return "FUNCTION_END_"+id;
	}
}
