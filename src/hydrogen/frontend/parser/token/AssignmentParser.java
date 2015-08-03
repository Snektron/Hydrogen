package hydrogen.frontend.parser.token;

import hydrogen.frontend.parser.expression.ExpressionParser;
import hydrogen.vcode.VirtualCode;

public class AssignmentParser implements ITokenParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		//TODO
		ExpressionParser.parse(vcode);
	}
}
