package hydrogen.vcode.variable;

import java.util.HashMap;
import java.util.Stack;

public class VariableAllocator
{
	// list holding the number of variables in a function.
	HashMap<String, Stack<Integer>> vars;
	
	public VariableAllocator()
	{
		vars = new HashMap<String, Stack<Integer>>();
	}
	
	/**
	 * Get a location for a variable
	 * @param base
	 *  The function the variable is in. If null, variable is global
	 * @param depth
	 * 	The block depth
	 * @return
	 *  The location
	 */
	
	public int getLoc(String base, int depth)
	{
		if (!vars.containsKey(base))
		{
			Stack<Integer> s = new Stack<Integer>();
			s.push(0);
			vars.put(base, s);
		}
		
		Stack<Integer> stack = vars.get(base);
		while (stack.size() - 1 > depth)
			stack.pop();
		while (stack.size() - 1 < depth)
			stack.push(0);
		int n = stack.pop();
		stack.push(n + 1);
		return n;
	}
}