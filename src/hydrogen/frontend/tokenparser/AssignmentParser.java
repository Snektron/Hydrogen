package hydrogen.frontend.tokenparser;

import hydrogen.frontend.parser.ExpressionParser;
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
