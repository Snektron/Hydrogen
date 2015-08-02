package hydrogen.vcode;

import java.util.ArrayList;

import hydrogen.frontend.token.Token;
import hydrogen.frontend.token.Tokenizer;
import hydrogen.vcode.instruction.IInstruction;
import hydrogen.vcode.variable.VariableAllocator;

public class VirtualCode extends VariableAllocator
{
	Token currentToken, lastToken;
	Tokenizer tokenizer;
	public ArrayList<IInstruction> vcode;
	
	public VirtualCode(Tokenizer t)
	{
		this.tokenizer = t;
		vcode = new ArrayList<IInstruction>();
	}
	
	public void add(IInstruction instruction)
	{
		vcode.add(instruction);
	}
	
	public Token nextToken()
	{
		lastToken = currentToken;
		currentToken = tokenizer.nextToken();
		return currentToken;
	}
	
	public Token currentToken()
	{
		return currentToken;
	}
	
	public Token lastToken()
	{
		return lastToken;
	}
	
	public boolean hasCode()
	{
		return tokenizer.hasCode();
	}
}
