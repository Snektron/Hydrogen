package hydrogen.frontend.parser.expression;

import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.PushConstant;

public class ConstantParser implements IExpressionParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		vcode.add(new PushConstant(Integer.parseInt(vcode.currentToken().sequence)));
	}

	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
		
	}
}
