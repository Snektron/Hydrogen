package hydrogen.frontend.parser;

public class MatchUtil
{
	public static String
	BOOLEAN =	"(true|false)\\b(?!\\.)",
	NAME =		"([a-zA-Z_][a-zA-Z0-9_\\.]*)",
	FLOAT = 	"(\\d*\\.\\d+|\\d+\\.\\d*)",
	INT = 		"\\d+";
		
	public static String keyword(String key)
	{
		return key+"\\b(?!\\.)";
	}
}
