package hydrogen.frontend.parser;

import hydrogen.Log;
import hydrogen.Strings;
import hydrogen.frontend.error.SyntaxError;
import hydrogen.frontend.error.UnresolvedFunctionError;
import hydrogen.frontend.token.Tokenizer;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.data.Function;
import hydrogen.vcode.instruction.Call;
import hydrogen.vcode.instruction.Instruction;

public class Parser
{
	public static VirtualCode parseCode(Tokenizer t)
	{
		VirtualCode vcode = new VirtualCode(t);
		
		vcode.nextToken();

		while(true)
		{
			parseNext(vcode);
			if (!vcode.hasCode())
				break;
		}
		
		checkFunctions(vcode);
		
		return vcode;
	}
	
	public static void parseNext(VirtualCode vcode)
	{
		vcode.currentToken().parseToken(vcode);
	}
	
	public static void checkFunctions(VirtualCode vcode)
	{		
		for (Instruction i:vcode.vcode())
			if (i instanceof Call)
			{
				Call call = (Call) i;
				String name = call.label;
				int arguments = call.arguments;
				if (!vcode.falloc().isRegistered(name, arguments))
					throw new UnresolvedFunctionError(Strings.UNRESOLVED_FUNCTION.f(name, arguments+""));
				Function f = vcode.falloc().get(vcode.falloc().getByNameAndArgs(name, arguments));
				if (call.expectsReturn && !f.returnsValue)
					throw new SyntaxError(Strings.RETURN_EXPECTED.f(name));
			}
	}
	
	public static void dump(VirtualCode vcode)
	{
		Log.d(Strings.PARSER_DUMP.msg);
		Instruction inst;
		for (int i = 0; i<vcode.vcode().size(); i++)
		{
			inst = vcode.vcode().get(i);
			Log.d(i + ": " + inst.toString(vcode));
		}
	}
}
