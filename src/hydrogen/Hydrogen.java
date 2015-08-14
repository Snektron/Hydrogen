package hydrogen;

import java.io.IOException;

import hydrogen.frontend.Frontend;
import hydrogen.frontend.error.HydrogenError;
import hydrogen.frontend.parser.Parser;

public class Hydrogen
{
	public static void compile(String file)
	{		
		try {
			Parser.dump(Frontend.pass(file));
		} catch (HydrogenError he)
		{
			he.logError();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		if (args.length >= 1)
			compile(args[0]);
		else
			System.out.println("Usage: Hydrogen <file>");
	}
}
