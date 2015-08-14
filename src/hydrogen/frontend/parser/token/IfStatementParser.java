package hydrogen.frontend.parser.token;

import hydrogen.frontend.parser.ParseUtil;
import hydrogen.frontend.token.EToken;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.Jump;
import hydrogen.vcode.instruction.Jump.Condition;
import hydrogen.vcode.instruction.Label;

public class IfStatementParser implements ITokenParser
{
	int blockID = 0;
	
	@Override
	public void parse(VirtualCode vcode)
	{
		int block = blockID++;
		int blockLocal = 0;
		
		ParseUtil.parseStatement(vcode);
		
		String endLbl = nextLabel(block, blockLocal++);
		vcode.add(new Jump(endLbl, Condition.FALSE));
		
		ParseUtil.parseBlock(vcode, EToken.END, EToken.ELSE, EToken.ELSEIF);
		
		if (vcode.currentToken().is(EToken.ELSEIF))
		{
			String absEndLbl = nextLabel(block, blockLocal++);
			
			while (vcode.currentToken().is(EToken.ELSEIF))
			{
				vcode.add(new Jump(absEndLbl, Condition.NONE));
				vcode.add(new Label(endLbl));
				
				ParseUtil.parseStatement(vcode);
				
				endLbl = nextLabel(block, blockLocal++);
				vcode.add(new Jump(endLbl, Condition.FALSE));

				ParseUtil.parseBlock(vcode, EToken.END, EToken.ELSE, EToken.ELSEIF);
			}
			
			if (vcode.currentToken().is(EToken.ELSE))
			{
				vcode.add(new Jump(absEndLbl, Condition.NONE));
				vcode.add(new Label(endLbl));
				
				ParseUtil.parseBlock(vcode, EToken.END);
			}else
				vcode.add(new Label(endLbl));
			
			endLbl = absEndLbl;
		}else if (vcode.currentToken().is(EToken.ELSE))
		{
			String elselbl = new String(endLbl);
			endLbl = nextLabel(block, blockLocal++);
			vcode.add(new Jump(endLbl, Condition.NONE));
			vcode.add(new Label(elselbl));
			
			ParseUtil.parseBlock(vcode, EToken.END);
		}
		
		ParseUtil.checkToken(vcode, EToken.END);
		vcode.add(new Label(endLbl));
		if (vcode.hasCode())
			vcode.nextToken();
	}
	
	private String nextLabel(int id, int localid)
	{
		return "IF_"+id+"_"+localid;
	}
}
