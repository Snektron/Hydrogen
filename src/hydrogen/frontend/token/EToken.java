package hydrogen.frontend.token;

import java.util.regex.Pattern;

import hydrogen.frontend.parser.expression.ConstantParser;
import hydrogen.frontend.parser.expression.IExpressionParser;
import hydrogen.frontend.parser.expression.IllegalTokenParser;
import hydrogen.frontend.parser.expression.OperatorParser;
import hydrogen.frontend.parser.token.AssignmentParser;
import hydrogen.frontend.parser.token.ITokenParser;
import hydrogen.frontend.parser.token.UnexpectedTokenParser;
import hydrogen.vcode.VirtualCode;

public enum EToken
{	
	BOOLEAN(Util.BOOLEAN, null, new ConstantParser(EDataType.BOOLEAN)),
	RETURN(Util.keyword("return"), null, null),
	IF("if\\s*\\(", null, null),
	ELSE(Util.keyword("else"), null, null),
	END(Util.keyword("end"), null, null),
	BRACKET_OPEN("\\(", null, null),
	BRACKET_CLOSE("\\)", null, null),
	FUNCTION_DEFINE(EDataType.getReturnTypes() + "\\s*" + Util.NAME + "\\s*\\(", null, null),
	CALL(Util.NAME + "\\s*\\(", null, null),
	OPERATOR(EOperator.getOperators(), null, new OperatorParser()),
	FLOAT(Util.FLOAT, null, new ConstantParser(EDataType.FLOAT)),
	INTEGER(Util.INT, null, new ConstantParser(EDataType.INTEGER)),
	VARIABLE_DEFINE(EDataType.getModifierTypes() + "\\s*" + Util.NAME + "\\s*=[^=]", new AssignmentParser(), null),
	ASSIGNMENT(Util.NAME + "\\s*=[^=]", new AssignmentParser(), null),
	PARAMETER_DEFINE(EDataType.getModifierTypes() + "\\s*" + Util.NAME, null, null),
	VARIABLE(Util.NAME, null, null),
	ARGUMENT_SEPERATOR(",", null, null);
	
	Pattern pattern;
	ITokenParser tokParser;
	IExpressionParser exprParser;
	
	EToken(String regex, ITokenParser tokParser, IExpressionParser exprParser)
	{
		pattern = Pattern.compile("^("+regex+")");
		
		this.tokParser = tokParser == null ? new UnexpectedTokenParser() : tokParser;
		this.exprParser = exprParser == null ? new IllegalTokenParser() : exprParser;
	}
	
	public void parseToken(VirtualCode vcode)
	{
		tokParser.parse(vcode);
	}
	
	public void parseExpression(VirtualCode vcode)
	{
		exprParser.parse(vcode);
	}
	
	public void closeExpression(Token t, VirtualCode vcode)
	{
		exprParser.closeExpression(t, vcode);
	}
	
	static class Util
	{
		static String
			BOOLEAN =	"(true|false)\\b(?!\\.)",
			NAME =		"([a-zA-Z_][a-zA-Z0-9_\\.]*)",
			FLOAT = 	"(\\d*\\.\\d+|\\d+\\.\\d*)",
			INT = 		"\\d+";
				
		public static String keyword(String key)
		{
			return key+"\\b(?!\\.)";
		}
	}
}
