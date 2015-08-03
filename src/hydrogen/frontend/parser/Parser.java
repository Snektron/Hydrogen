package hydrogen.frontend.parser;

import hydrogen.Log;
import hydrogen.Strings;
import hydrogen.frontend.token.Tokenizer;
import hydrogen.vcode.VirtualCode;
import hydrogen.vcode.instruction.Instruction;

public class Parser
{
	public static VirtualCode parseCode(Tokenizer t)
	{
		VirtualCode vcode = new VirtualCode(t);
		
		while(vcode.hasCode())
			parseNext(vcode);

		return vcode;
	}
	
	public static void parseNext(VirtualCode vcode)
	{
		Log.d("Parsing "+vcode.nextToken().name() + " " + vcode.currentToken().sequence);
		vcode.currentToken().parseToken(vcode);
	}
	
	public static void dump(VirtualCode vcode)
	{
		Log.d(Strings.PARSER_DUMP.msg);
		Instruction inst;
		for (int i = 0; i<vcode.vcode().size(); i++)
		{
			inst = vcode.vcode().get(i);
			Log.d(i + ": " + inst.toString());
		}
	}
}
