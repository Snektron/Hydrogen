package hydrogen.vcode;

import java.util.ArrayList;
import java.util.Stack;

import hydrogen.frontend.token.Token;
import hydrogen.frontend.token.Tokenizer;
import hydrogen.vcode.instruction.Instruction;
import hydrogen.vcode.variable.VariableAllocator;

public class VirtualCode
{
	Token currentToken, lastToken;
	Tokenizer tokenizer;
	ArrayList<Instruction> vcode;
	VariableAllocator valloc;
	Stack<Token> operators;
	
	public VirtualCode(Tokenizer t)
	{
		this.tokenizer = t;
		vcode = new ArrayList<Instruction>();
		valloc = new VariableAllocator();
		operators = new Stack<Token>();
	}
	
	public Stack<Token> opStack()
	{
		return operators;
	}
	
	public VariableAllocator valloc()
	{
		return valloc;
	}
	
	public ArrayList<Instruction> vcode()
	{
		return vcode;
	}
	
	public void add(Instruction instruction)
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
