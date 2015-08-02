package hydrogen.frontend.token;

import java.util.regex.Pattern;

import hydrogen.frontend.tokenparser.AssignmentParser;
import hydrogen.frontend.tokenparser.ITokenParser;
import hydrogen.frontend.tokenparser.UnexpectedTokenParser;
import hydrogen.vcode.variable.DataType;

public enum EToken
{	
	BOOLEAN(Util.BOOLEAN, null, true),
	RETURN(Util.keyword("return"), null, false),
	IF("if\\s*\\(", null, false),
	ELSE(Util.keyword("else"), null, false),
	END(Util.keyword("end"), null, false),
	BRACKET_OPEN("\\(", null, true),
	BRACKET_CLOSE("\\)", null, true),
	FUNCTION_DEFINE(Util.getReturnTypes() + "\\s*" + Util.NAME + "\\s*\\(", null, false),
	CALL(Util.NAME + "\\s*\\(", null, true),
	OPERATOR(Util.getOperators(), null, true),
	FLOAT(Util.FLOAT, null, true),
	INTEGER(Util.INT, null, true),
	VARIABLE_DEFINE(Util.getModifierTypes() + "\\s*" + Util.NAME + "\\s*=[^=]", new AssignmentParser(), false),
	ASSIGNMENT(Util.NAME + "\\s*=[^=]", new AssignmentParser(), false),
	VARIABLE(Util.NAME, null, true),
	ARGUMENT_SEPERATOR(",", null, false);
	
	Pattern pattern;
	ITokenParser parser;
	boolean allowInExpr;
	
	EToken(String regex, ITokenParser parser, boolean allowInExpr)
	{
		pattern = Pattern.compile("^("+regex+")");
		if (parser == null)
			this.parser = new UnexpectedTokenParser();
		else
			this.parser = parser;
		this.allowInExpr = allowInExpr;
	}
	
	static class Util
	{
		static String
			BOOLEAN =	"(true|false)\\b(?!\\.)",
			NAME =		"([a-zA-Z_][a-zA-Z0-9_\\.]*)",
			FLOAT = 	"(\\d*\\.\\d+|\\d+\\.\\d*)",
			INT = 		"\\d+";
		
		public static String getModifierTypes()
		{
			StringBuilder sb = new StringBuilder(")");
			for (int i=0; i<DataType.values().length; i++)
				if (!DataType.values()[i].returnOnly)
					sb.insert(0, DataType.values()[i].syntax).insert(0, "|");
			return "(" + sb.substring(1).toString();
		}
		
		public static String getReturnTypes()
		{
			StringBuilder sb = new StringBuilder(")");
			for (int i=0; i<DataType.values().length; i++)
				sb.insert(0, DataType.values()[i].syntax).insert(0, "|");
			return "(" + sb.substring(1).toString();
		}
		
		public static String getOperators()
		{
			StringBuilder sb = new StringBuilder(")");
			for (int i=0; i<EOperator.values().length; i++)
				sb.insert(0, EOperator.values()[i].regex).insert(0, "|");
			System.out.println("(" + sb.substring(1).toString());
			return "(" + sb.substring(1).toString();
		}
		
		public static String keyword(String key)
		{
			return key+"\\b(?!\\.)";
		}
	}
}
