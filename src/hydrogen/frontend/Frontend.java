package hydrogen.frontend;

import java.io.IOException;

import hydrogen.frontend.parser.Parser;
import hydrogen.frontend.token.Tokenizer;
import hydrogen.vcode.VirtualCode;

public class Frontend
{
	public static VirtualCode pass(String file) throws IOException
	{
		return Parser.parseCode(new Tokenizer(file));
	}
}
