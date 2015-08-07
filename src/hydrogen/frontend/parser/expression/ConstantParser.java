package hydrogen.frontend.parser.expression;

import hydrogen.frontend.token.EDataType;
import hydrogen.frontend.token.Token;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.PushConstant;

public class ConstantParser implements IExpressionParser
{
	EDataType dataType;
	
	public ConstantParser(EDataType dataType)
	{
		this.dataType = dataType;
	}
	
	@Override
	public void parse(VirtualCode vcode)
	{
		vcode.add(new PushConstant(vcode.calloc().register(dataType, dataType.parse(vcode.currentToken()))));
	}

	@Override
	public void closeExpression(Token token, VirtualCode vcode)
	{
		
	}
}
