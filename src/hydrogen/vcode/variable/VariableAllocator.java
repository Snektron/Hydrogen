package hydrogen.vcode.variable;

import java.util.ArrayList;
import java.util.Stack;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.error.RedefinitionError;
import hydrogen.frontend.error.UnresolvedVariableError;
import hydrogen.frontend.token.EDataType;
import hydrogen.frontend.token.ELocationType;

public class VariableAllocator
{
	public ArrayList<Variable> variables;
	boolean inFunction;
	
	int blockIDCounter;
	
	Stack<Block> globalBlocks;
	Stack<Block> localBlocks;
	
	public VariableAllocator()
	{
		variables = new ArrayList<Variable>();
		inFunction = false;
		
		blockIDCounter = 0;
		
		globalBlocks = new Stack<Block>();
		globalBlocks.push(new Block(0));
		localBlocks = new Stack<Block>();
	}
	
	public void openFunction()
	{
		inFunction = true;
		localBlocks.empty();
		localBlocks.push(new Block(0));
	}
	
	public void closeFunction()
	{
		inFunction = false;
	}
	
	// increase block depth
	public void blockOpen()
	{
		getBlocks().push(new Block(getBlocks().peek().startLocation));
	}
	
	// decrease block depth
	public void blockClose()
	{
		getBlocks().pop();
	}
	
	public boolean isReachable(String name)
	{
		Variable var = null;
		for (Variable v:variables)
			if (v.name.equals(name))
				var = v;
		
		if (var == null)
			return false;
		
		for (Block block : getBlocks())
			if (var.blockID == block.id)
				return true;
		
		return false;
	}
	
	public int register(EDataType dataType, String name)
	{
		if (isReachable(name))
			throw new RedefinitionError(Strings.DUPLICATE_VARIABLE.f(name));
		
		ELocationType locationType = inFunction ? ELocationType.GLOBAL : ELocationType.LOCAL;
		variables.add(new Variable(locationType, dataType, name, getBlocks().peek().id, getBlocks().peek().nextLocation()));
		return variables.size()-1;
	}
	
	public Stack<Block> getBlocks()
	{
		return inFunction ? localBlocks : globalBlocks;
	}
	
	public int getByName(String name)
	{
		if (!isReachable(name))
			throw new UnresolvedVariableError(Strings.UNRESOLVED_VARIABLE.f(name));
		
		for (int i = 0; i < variables.size(); i++)
			if (variables.get(i).name.equals(name))
				return i;
		throw new ParseError(Strings.UNRESOLVED_VARIABLE_ERROR.f(name));
	}
	
	public Variable getById(int id)
	{
		return variables.get(id);
	}
	
	private int nextID()
	{
		return blockIDCounter++;
	}
	
	private class Block
	{
		public int id, startLocation;
		
		public Block(int startLocation)
		{
			id = nextID();
		}
		
		public int nextLocation()
		{
			return startLocation++;
		}
	}
}