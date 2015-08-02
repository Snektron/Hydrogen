package hydrogen.frontend.tokenparser;

import hydrogen.frontend.token.EToken;
import hydrogen.vcode.VirtualCode;

public class AssignmentParser implements ITokenParser
{
	@Override
	public void parse(VirtualCode vcode)
	{
		if (vcode.currentToken().is(EToken.VARIABLE_DEFINE))
		{
			
		}
	}
}
