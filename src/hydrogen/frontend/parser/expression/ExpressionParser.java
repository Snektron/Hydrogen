package hydrogen.frontend.parser.expression;

import hydrogen.vcode.VirtualCode;

public class ExpressionParser
{
	public static void parse(VirtualCode vcode)
	{
		vcode.opStack().clear();
		
		while(vcode.hasCode())
			vcode.nextToken().parseExpression(vcode);
		
		while (!vcode.opStack().empty())
			vcode.opStack().pop().closeExpression(vcode);
	}
}
