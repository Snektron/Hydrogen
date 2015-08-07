package hydrogen.frontend.token;

import hydrogen.frontend.parser.expression.IllegalTokenParser;
import hydrogen.vcode.VirtualCode;

public class Token
{
	public EToken token;
	public String sequence;
	
	public Token(EToken token, String sequence)
	{
		this.token = token;
		this.sequence = sequence;
	}
	
	public Token(Token t)
	{
		this.token = t.token;
		this.sequence = t.sequence;
	}
	
	public String name()
	{
		return token.name();
	}
	
	public void parseToken(VirtualCode vcode)
	{
		token.parseToken(vcode);
	}
	
	public void parseExpression(VirtualCode vcode)
	{
		token.parseExpression(vcode);
	}
	
	public void closeExpression(VirtualCode vcode)
	{
		token.closeExpression(this, vcode);
	}
	
	public boolean is(EToken other)
	{
		return token == other;
	}
	
	public boolean isOneOf(EToken... others)
	{
		for (int i = 0; i < others.length; i++)
			if (is(others[i]))
				return true;
		return false;
	}
	
	public boolean allowedInExpression()
	{
		return !(token.exprParser instanceof IllegalTokenParser);
	}
}
