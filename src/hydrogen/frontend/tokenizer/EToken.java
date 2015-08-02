package hydrogen.frontend.tokenizer;

import java.util.regex.Pattern;

import hydrogen.frontend.parser.IParser;
import hydrogen.frontend.parser.UnexpectedTokenParser;
import hydrogen.vcode.variable.DataType;

public enum EToken
{	
	BOOLEAN(Util.BOOLEAN, null),
	RETURN(Util.keyword("return"), null),
	IF("if\\s*\\(", null),
	ELSE(Util.keyword("else"), null),
	END(Util.keyword("end"), null),
	BRACKET_OPEN("\\(", null),
	BRACKET_CLOSE("\\)", null),
	FUNCTION_DEFINE(Util.getReturnTypes() + "\\s*" + Util.NAME + "\\s*\\(", null),
	CALL(Util.NAME + "\\s*\\(", null),
	OPERATOR(Util.OPERATOR, null),
	FLOAT(Util.FLOAT, null),
	INTEGER(Util.INT, null),
	VARIABLE_DEFINE(Util.getModifierTypes() + "\\s*" + Util.NAME + "\\s*=[^=]", null),
	ASSIGNMENT(Util.NAME + "\\s*=[^=]", null),
	VARIABLE(Util.NAME, null),
	ARGUMENT_SEPERATOR(",", null);
	
	Pattern pattern;
	IParser parser;
	
	EToken(String regex, IParser parser)
	{
		pattern = Pattern.compile("^("+regex+")");
		if (parser == null)
			this.parser = new UnexpectedTokenParser();
		else
			this.parser = parser;
	}
	
	static class Util
	{
		static String
			OPERATOR =	"(==|<=|>=|<|>|!=|\\+|-|\\*|/|%|\\^)",
			BOOLEAN =	"(true|false)\\b(?!\\.)",
			NAME =		"([a-zA-Z_][a-zA-Z0-9_\\.]*)",
			FLOAT = 	"(\\d*\\.\\d+|\\d+\\.\\d*)",
			INT = 		"\\d+";
		
		public static String getModifierTypes()
		{
			// yes, yes, bad practice
			StringBuilder sb = new StringBuilder(")");
			for (int i=0; i<DataType.values().length; i++)
				if (!DataType.values()[i].returnOnly)
					sb.insert(0, DataType.values()[i].syntax).insert(0, "|");
			return "(" + sb.substring(1).toString();
		}
		
		public static String getReturnTypes()
		{
			// yes, yes, bad practice
			StringBuilder sb = new StringBuilder(")");
			for (int i=0; i<DataType.values().length; i++)
				sb.insert(0, DataType.values()[i].syntax).insert(0, "|");
			return "(" + sb.substring(1).toString();
		}
		
		public static String keyword(String key)
		{
			return key+"\\b(?!\\.)";
		}
	}
}
