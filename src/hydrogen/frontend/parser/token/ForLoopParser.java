package hydrogen.frontend.parser.token;

import java.util.ArrayList;

import hydrogen.frontend.parser.ParseUtil;
import hydrogen.frontend.parser.Parser;
import hydrogen.frontend.parser.expression.ExpressionParser;
import hydrogen.frontend.token.EToken;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.Instruction;
import hydrogen.vcode.instruction.Jump;
import hydrogen.vcode.instruction.Jump.Condition;
import hydrogen.vcode.instruction.Label;

public class ForLoopParser implements ITokenParser
{
	int blockID = 0;
	
	@Override
	public void parse(VirtualCode vcode)
	{
		int block = blockID++;
		int blockLocal = 0;
		
		String frontLabel = nextLabel(block, blockLocal++);
		String endLabel = nextLabel(block, blockLocal++);
		
		// init
		
		vcode.valloc().blockOpen();
		vcode.nextToken();
		ParseUtil.checkToken(vcode, EToken.ASSIGNMENT);
		EToken.ASSIGNMENT.parseToken(vcode);
		ParseUtil.checkToken(vcode, EToken.ARGUMENT_SEPERATOR);
		
		// cond
		
		vcode.add(new Label(frontLabel));
		
		vcode.nextToken();
		ExpressionParser.parse(vcode);
		ParseUtil.checkToken(vcode, EToken.ARGUMENT_SEPERATOR);
		
		vcode.add(new Jump(endLabel, Condition.FALSE));
		
		// inc
		
		vcode.nextToken();
		
		ArrayList<Instruction> bvcode = new ArrayList<Instruction>();
		for (Instruction i:vcode.vcode())
			bvcode.add(i);
		
		vcode.vcode().clear();
		
		ParseUtil.checkToken(vcode, EToken.ASSIGNMENT);
		EToken.ASSIGNMENT.parseToken(vcode);
		ParseUtil.checkToken(vcode, EToken.BRACKET_CLOSE);
		vcode.nextToken();
		
		ArrayList<Instruction> ivcode = new ArrayList<Instruction>();
		for (Instruction i:vcode.vcode())
			ivcode.add(i);
		
		vcode.vcode().clear();
		for (Instruction i:bvcode)
			vcode.add(i);
		
		// increment code now in ivcode
		//code
		
		while(!vcode.currentToken().is(EToken.END))
			Parser.parseNext(vcode);
		ParseUtil.checkToken(vcode, EToken.END);
		
		for (Instruction i:ivcode)
			vcode.add(i);
		
		vcode.valloc().blockClose();
		
		vcode.add(new Jump(frontLabel, Condition.NONE));
		vcode.add(new Label(endLabel));
		
		if (vcode.hasCode())
			vcode.nextToken();
	}
	
	private String nextLabel(int id, int localid)
	{
		return "FOR_"+id+"_"+localid;
	}
}
