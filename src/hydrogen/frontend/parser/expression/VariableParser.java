package hydrogen.frontend.parser.expression;

import hydrogen.frontend.error.UnresolvedVariableError;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.PushVariable;

public class VariableParser implements IExpressionParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		String name = vcode.currentToken().sequence;
		if (!vcode.valloc().isReachable(name))
			throw new UnresolvedVariableError(name);
		vcode.add(new PushVariable(vcode.valloc().getByName(name)));
	}

	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
		
	}
}
