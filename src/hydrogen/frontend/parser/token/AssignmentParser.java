package hydrogen.frontend.parser.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.error.UnresolvedVariableError;
import hydrogen.frontend.parser.MatchUtil;
import hydrogen.frontend.parser.expression.ExpressionParser;
import hydrogen.frontend.token.EDataType;
import hydrogen.frontend.token.EToken;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.Assignment;

public class AssignmentParser implements ITokenParser
{
	@Override
	public void parse(VirtualCode vcode)
	{	
		if (vcode.currentToken().is(EToken.ASSIGNMENT))
		{
			String seq = vcode.currentToken().sequence;
			
			Matcher m = Pattern.compile(MatchUtil.NAME).matcher(seq);
			if (!m.find())
				throw new ParseError(Strings.ERROR.msg);
			String name = m.group();
			
			if (!vcode.valloc().isReachable(name))
				throw new UnresolvedVariableError(name);
			
			vcode.nextToken();
			ExpressionParser.parse(vcode);
			
			vcode.add(new Assignment(vcode.valloc().getByName(name)));
		}else if(vcode.currentToken().is(EToken.VARIABLE_DEFINE))
		{
			String seq = vcode.currentToken().sequence;
			
			Matcher m = Pattern.compile(EDataType.getModifierTypes()).matcher(seq);
			
			if (!m.find())
				throw new ParseError(Strings.ERROR.msg);
			String type = m.group();
			seq = m.replaceFirst("").replaceFirst("\\s*", "");
			
			m = Pattern.compile(MatchUtil.NAME).matcher(seq);
			if (!m.find())
				throw new ParseError(Strings.ERROR.msg);
			String name = m.group();
			
			vcode.nextToken();
			ExpressionParser.parse(vcode);
			
			vcode.valloc().register(EDataType.getDataType(type), name);
			vcode.add(new Assignment(vcode.valloc().getByName(name)));
		}else
			throw new ParseError(Strings.ERROR.msg);
	}
}
