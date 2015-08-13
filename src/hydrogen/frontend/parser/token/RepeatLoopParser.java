package hydrogen.frontend.parser.token;

import hydrogen.Strings;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.parser.ParseUtil;
import hydrogen.frontend.token.EToken;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.Jump;
import hydrogen.vcode.instruction.Label;
import hydrogen.vcode.instruction.Jump.Condition;

public class RepeatLoopParser implements ITokenParser
{
	int blockID = 0;
	
	@Override
	public void parse(VirtualCode vcode)
	{
		int block = blockID++;
		int blockLocal = 0;
		
		String frontLabel = nextLabel(block, blockLocal++);
		String endLabel = nextLabel(block, blockLocal++);
		
		vcode.add(new Label(frontLabel));
		ParseUtil.parseStatement(vcode);
		vcode.add(new Jump(endLabel, Condition.TRUE));
		
		ParseUtil.parseBlock(vcode, EToken.END);
		if (!vcode.currentToken().is(EToken.END))
			throw new SyntaxError(Strings.UNEXPECTED_TOKEN_EXPECTED.f(vcode.currentToken().name(), EToken.END.name()));
		
		vcode.add(new Jump(frontLabel, Condition.NONE));
		vcode.add(new Label(endLabel));
		
		if (vcode.hasCode())
			vcode.nextToken();
	}
	
	private String nextLabel(int id, int localid)
	{
		return "REPEAT_"+id+"_"+localid;
	}
}
