package hydrogen.vcode;

import java.util.ArrayList;

import hydrogen.frontend.token.Token;
import hydrogen.frontend.token.Tokenizer;
import hydrogen.vcode.instruction.IInstruction;
import hydrogen.vcode.variable.VariableAllocator;

public class VirtualCode
{
	Token currentToken, lastToken;
	Tokenizer tokenizer;
	ArrayList<IInstruction> vcode;
	VariableAllocator valloc;
	
	public VirtualCode(Tokenizer t)
	{
		this.tokenizer = t;
		vcode = new ArrayList<IInstruction>();
		valloc = new VariableAllocator();
	}
	
	public VariableAllocator valloc()
	{
		return valloc;
	}
	
	public ArrayList<IInstruction> vcode()
	{
		return vcode;
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
