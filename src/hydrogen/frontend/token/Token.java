package hydrogen.frontend.token;

import hydrogen.Strings;
import hydrogen.frontend.error.ParseError;
import hydrogen.frontend.error.SyntaxError;
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
	
	public EOperator getOperator()
	{
		if (token != EToken.OPERATOR)
			throw new SyntaxError(Strings.OPERATOR_SYNTAX_ERROR.f(token.name()));
		
		for (int i=0; i<EOperator.values().length; i++)
			if (sequence.matches(EOperator.values()[i].regex))
				return EOperator.values()[i];
		
		// this shouldn't happen
		throw new ParseError(Strings.OPERATOR_PARSE_ERROR.f(sequence));
	}
}
