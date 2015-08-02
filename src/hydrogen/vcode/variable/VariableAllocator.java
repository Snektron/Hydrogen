package hydrogen.vcode.variable;

import java.util.ArrayList;
import java.util.Stack;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.error.RedefinitionError;
import hydrogen.frontend.error.UnresolvedVariableError;

public class VariableAllocator
{
	int blockIDCounter;
	Stack<Block> blocks;
	public ArrayList<Variable> variables;
	
	public VariableAllocator()
	{
		variables = new ArrayList<Variable>();
		blockIDCounter = 0;
		blocks = new Stack<Block>();
		blocks.push(new Block());
	}
	
	// increase block depth
	public void blockOpen()
	{
		blocks.push(new Block());
	}
	
	// decrease block depth
	public void blockClose()
	{
		blocks.pop();
	}
	
	public int register(EDataType dataType, String name)
	{
		if (isReachable(name))
			throw new RedefinitionError(Strings.DUPLICATE_VARIABLE.f(name));
		
		variables.add(new Variable(dataType, name, blocks.peek().id, blocks.peek().nextLocation()));
		return variables.size()-1;
	}
	
	public boolean isReachable(String name)
	{
		Variable var = null;
		for (Variable v:variables)
			if (v.name.equals(name))
				var = v;
		
		if (var == null)
			return false;
		
		for (Block block : blocks)
			if (var.blockID == block.id)
				return true;
		
		return false;
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
		public int id, location;
		
		public Block()
		{
			id = nextID();
			location = 0;
		}
		
		private int nextLocation()
		{
			return location++;
		}
	}
}