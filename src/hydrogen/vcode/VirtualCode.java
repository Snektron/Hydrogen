package hydrogen.vcode;

import java.util.ArrayList;

import hydrogen.frontend.token.Token;
import hydrogen.frontend.token.Tokenizer;
import hydrogen.vcode.instruction.Instruction;
import hydrogen.vcode.variable.DataType;
import hydrogen.vcode.variable.LocationType;
import hydrogen.vcode.variable.Variable;
import hydrogen.vcode.variable.VariableAllocator;

public class VirtualCode
{
	Token currentToken;
	Tokenizer tokenizer;
	public ArrayList<Instruction> vcode;
	public ArrayList<Variable> variables;
	public VariableAllocator valloc;
	public int depth;
	
	public VirtualCode(Tokenizer t)
	{
		this.tokenizer = t;
		vcode = new ArrayList<Instruction>();
		variables = new ArrayList<Variable>();
		depth = 0;
	}
	
	public Token nextToken()
	{
		currentToken = tokenizer.nextToken();
		return currentToken;
	}
	
	public Token currentToken()
	{
		return currentToken;
	}
	
	public boolean hasCode()
	{
		return tokenizer.hasCode();
	}
	
	public int registerVariable(DataType dataType, String base, String name)
	{
		Variable v = new Variable(base == null ? LocationType.GLOBAL : LocationType.LOCAL, dataType, name, valloc.getLoc(base, depth));
		variables.add(v);
		return variables.size()-1;
	}
}
