package hydrogen.frontend.token;

import java.util.regex.Pattern;

import hydrogen.frontend.parser.MatchUtil;
import hydrogen.frontend.parser.expression.BracketCloseParser;
import hydrogen.frontend.parser.expression.BracketOpenParser;
import hydrogen.frontend.parser.expression.CallParser;
import hydrogen.frontend.parser.expression.ConstantParser;
import hydrogen.frontend.parser.expression.IExpressionParser;
import hydrogen.frontend.parser.expression.IllegalTokenParser;
import hydrogen.frontend.parser.expression.OperatorParser;
import hydrogen.frontend.parser.expression.VariableParser;
import hydrogen.frontend.parser.token.AssignmentParser;
import hydrogen.frontend.parser.token.FunctionDefineParser;
import hydrogen.frontend.parser.token.ITokenParser;
import hydrogen.frontend.parser.token.IfStatementParser;
import hydrogen.frontend.parser.token.UnexpectedTokenParser;
import hydrogen.frontend.parser.token.WhileLoopParser;
import hydrogen.vcode.VirtualCode;

public enum EToken
{	
	BOOLEAN(MatchUtil.BOOLEAN, null, new ConstantParser()),
	RETURN_VALUE("return\\s*\\(", null, null),
	RETURN("return", null, null),
	WHILE("while\\s*\\(", new WhileLoopParser(), null),
	IF("if\\s*\\(", new IfStatementParser(), null),
	ELSEIF("elseif\\s*\\(", null, null),
	ELSE("else", null, null),
	END("end", null, null),
	BRACKET_OPEN("\\(", null, new BracketOpenParser()),
	BRACKET_CLOSE("\\)", null, new BracketCloseParser()),
	FUNCTION_DEFINE("function\\s+" + MatchUtil.NAME + "\\s*\\(", new FunctionDefineParser(), null),
	CALL(MatchUtil.NAME + "\\s*\\(", new CallParser(false), new CallParser(true)),
	OPERATOR(EOperator.getOperators(), null, new OperatorParser()),
	INTEGER(MatchUtil.INT, null, new ConstantParser()),
	ASSIGNMENT(MatchUtil.NAME + "\\s*=[^=]", new AssignmentParser(), null),
	VARIABLE(MatchUtil.NAME, null, new VariableParser()),
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
}
