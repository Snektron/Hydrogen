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
	
	public boolean allowedInExpression()
	{
		return !(token.exprParser instanceof IllegalTokenParser);
	}
}
