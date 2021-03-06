package hydrogen.frontend.parser.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.parser.MatchUtil;
import hydrogen.frontend.parser.expression.ExpressionParser;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.DefineVariable;
import hydrogen.vcode.instruction.PopVariable;

public class AssignmentParser implements ITokenParser
{
	@Override
	public void parse(VirtualCode vcode)
	{	
		String name = getName(vcode);
		vcode.nextToken();
		ExpressionParser.parse(vcode);
		
		if (!vcode.valloc().isReachable(name))
		{
			int id = vcode.valloc().register(name);
			vcode.add(new DefineVariable(id));
		}
		
		vcode.add(new PopVariable(vcode.valloc().getByName(name)));
	}
	
	public static String getName(VirtualCode vcode)
	{
		String seq = vcode.currentToken().sequence;
		
		Matcher m = Pattern.compile(MatchUtil.NAME).matcher(seq);
		if (!m.find())
			throw new ParseError(Strings.ERROR.f());
		return m.group();
	}
}
