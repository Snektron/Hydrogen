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
	public ArrayList<IInstruction> vcode;
	public VariableAllocator valloc;
	
	public VirtualCode(Tokenizer t)
	{
		this.tokenizer = t;
		vcode = new ArrayList<IInstruction>();
		valloc = new VariableAllocator();
	}
	
	public void add(IInstruction instruction)
	{
		vcode.add(instruction);
	}
	
	public void blockOpen()
	{
		valloc.blockOpen();
	}
	
	public void blockClose()
	{
		valloc.blockClose();
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
	
/*	public int registerVariable(EDataType dataType, String name)
	{
		Variable v = new Variable(base == null ? ELocationType.GLOBAL : ELocationType.LOCAL, dataType, base, name, valloc.getLoc(base, depth));
		variables.add(v);
		return variables.size()-1;
	}
	
	public int getVariable(String base, String name)
	{
		Variable v;
		
		for (int i=0; i<variables.size(); i++)
		{
			v = variables.get(i);
			if (v.base.equals(base) && v.name.equals(name))
				return i;
		}
		
		throw new UnresolvedVariableError(Strings.UNRESOLVED_VARIABLE.f(name));
	}
	
	public int getVariable(String name)
	{
		Variable v;
		
		for (int i=0; i<variables.size(); i++)
		{
			v = variables.get(i);
			if (v.base.equals(base) && v.name.equals(name))
				return i;
		}
		
		throw new UnresolvedVariableError(Strings.UNRESOLVED_VARIABLE.f(name));
	}*/
}
