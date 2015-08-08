package hydrogen.frontend.parser.expression;

import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.PushConstant;

public class ConstantParser implements IExpressionParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		String seq = vcode.currentToken().sequence;
		if (seq.matches("true|false"))
			vcode.add(new PushConstant(Boolean.parseBoolean(seq) ? 1 : 0));
		else
			vcode.add(new PushConstant(Integer.parseInt(seq)));
	}

	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
		
	}
}
