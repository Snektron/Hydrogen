package hydrogen.vcode;

import java.util.ArrayList;
import java.util.Stack;

import hydrogen.frontend.token.Token;
import hydrogen.frontend.token.Tokenizer;
import hydrogen.vcode.data.FunctionAllocator;
import hydrogen.vcode.data.VariableAllocator;
import hydrogen.vcode.instruction.Instruction;

public class VirtualCode
{
	Token currentToken, lastToken;
	Tokenizer tokenizer;
	ArrayList<Instruction> vcode;
	VariableAllocator valloc;
	FunctionAllocator falloc;
	Stack<Token> operators;
	
	public VirtualCode(Tokenizer t)
	{
		this.tokenizer = t;
		vcode = new ArrayList<Instruction>();
		valloc = new VariableAllocator();
		falloc = new FunctionAllocator();
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
	
	public FunctionAllocator falloc()
	{
		return falloc;
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
	
	public Token peekToken()
	{
		return tokenizer.peekToken();
	}
	
	public Token currentToken()
	{
		return currentToken;
	}
	
	public Token prevToken()
	{
		return lastToken;
	}
	
	public boolean hasCode()
	{
		return tokenizer.hasCode();
	}
	
	public String getErrorCode()
	{
		return tokenizer.getErrorCode();
	}
}
