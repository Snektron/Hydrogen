package hydrogen;

import java.io.IOException;

import hydrogen.frontend.Frontend;
import hydrogen.frontend.error.HydrogenError;

public class Hydrogen
{
	public static void compile(String file)
	{
		try {
			Frontend.pass(file);
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (HydrogenError he)
		{
			he.logError();
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
