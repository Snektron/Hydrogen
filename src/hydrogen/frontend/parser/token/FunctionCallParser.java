package hydrogen.frontend.parser.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.parser.MatchUtil;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.Call;

public class FunctionCallParser implements ITokenParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		String seq = vcode.currentToken().sequence;
		
		Matcher m = Pattern.compile(MatchUtil.NAME).matcher(seq);
		if (!m.find())
			throw new ParseError(Strings.ERROR.msg);
		String name = m.group();
		
		//TODO
		vcode.add(new Call(name));
	}
}
