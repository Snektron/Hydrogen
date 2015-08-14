package hydrogen.frontend.token;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hydrogen.FileReader;
import hydrogen.Strings;
import hydrogen.frontend.error.EndOfFileError;
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
	
	public Token peekToken()
	{
		if (!hasCode())
			throw new EndOfFileError(Strings.END_OF_FILE.f());
		
		EToken[] tokens = EToken.values();
		Matcher m;

		for (int i = 0; i < tokens.length; i++)
		{
			m = tokens[i].pattern.matcher(code);
			
			if (m.find())
				return new Token(tokens[i], m.group());
		}
		
		throw new TokenizeError(Strings.UNEXPECTED_INPUT.f(getErrorCode()));
	}
	
	public String getErrorCode()
	{
		return code.substring(0, code.length() > 20 ? 20 : code.length() > 0 ? code.length()-1 : 0);
	}
	
	public boolean hasCode()
	{
		return !code.equals("");
	}
}
