package hydrogen.frontend.tokenizer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hydrogen.FileReader;
import hydrogen.Strings;
import hydrogen.frontend.error.TokenizeError;

public class Tokenizer
{	
	Pattern leadingSpace;
	String code;
	
	public Tokenizer(String file) throws IOException
	{
		code = FileReader.read(file).replaceFirst("\\s*", "");
	}
	
	public Token nextToken()
	{
		EToken[] tokens = EToken.values();
		Matcher m;

		for (int i = 0; i < tokens.length; i++)
		{
			m = tokens[i].pattern.matcher(code);
			
			if (m.find())
			{
				code = m.replaceFirst("").replaceFirst("\\s*", "");
				return new Token(tokens[i], m.group());
			}
		}	

		throw new TokenizeError(Strings.UNEXPECTED_INPUT.f(code.substring(0, code.length() > 20 ? 20 : code.length()-1)));
	}
	
	public boolean hasCode()
	{
		return !code.equals("");
	}
}