package hydrogen.frontend.parser.token;

import hydrogen.Strings;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.parser.Parser;
import hydrogen.frontend.parser.expression.ExpressionParser;
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
		
		parseStatement(vcode);
		
		String endLbl = nextLabel(block, blockLocal++);
		vcode.add(new Jump(endLbl, Condition.FALSE));
		
		parseBlock(vcode, EToken.END, EToken.ELSE, EToken.ELSEIF);
		
		if (vcode.currentToken().is(EToken.ELSEIF))
		{
			String absEndLbl = nextLabel(block, blockLocal++);
			
			while (vcode.currentToken().is(EToken.ELSEIF))
			{
				vcode.add(new Jump(absEndLbl, Condition.NONE));
				vcode.add(new Label(endLbl));
				
				parseStatement(vcode);
				
				endLbl = nextLabel(block, blockLocal++);
				vcode.add(new Jump(endLbl, Condition.FALSE));

				parseBlock(vcode, EToken.END, EToken.ELSE, EToken.ELSEIF);
			}
			
			if (vcode.currentToken().is(EToken.ELSE))
			{
				vcode.add(new Jump(absEndLbl, Condition.NONE));
				vcode.add(new Label(endLbl));
				
				parseBlock(vcode, EToken.END);
			}else
				vcode.add(new Label(endLbl));
			
			endLbl = absEndLbl;
		}else if (vcode.currentToken().is(EToken.ELSE))
		{
			String elselbl = new String(endLbl);
			endLbl = nextLabel(block, blockLocal++);
			vcode.add(new Jump(endLbl, Condition.NONE));
			vcode.add(new Label(elselbl));
			
			parseBlock(vcode, EToken.END);
		}
		
		if (!vcode.currentToken().is(EToken.END))
			throw new SyntaxError(Strings.UNEXPECTED_TOKEN_EXPECTED.f(vcode.currentToken().name(), EToken.END.name()));
		vcode.add(new Label(endLbl));
		if (vcode.hasCode())
			vcode.nextToken();
	}
	
	private void parseStatement(VirtualCode vcode)
	{
		vcode.nextToken();
		ExpressionParser.parse(vcode);
		if (!vcode.currentToken().is(EToken.BRACKET_CLOSE))
			throw new SyntaxError(Strings.UNEXPECTED_TOKEN_EXPECTED.f(vcode.currentToken().name(), EToken.BRACKET_CLOSE.name()));
	}
	
	private void parseBlock(VirtualCode vcode, EToken... end)
	{
		vcode.nextToken();
		vcode.valloc().blockOpen();
		while(!vcode.currentToken().isOneOf(end))
			Parser.parseNext(vcode);
		vcode.valloc().blockClose();
	}
	
	private String nextLabel(int id, int localid)
	{
		return "IF_"+id+"_"+localid;
	}
}
