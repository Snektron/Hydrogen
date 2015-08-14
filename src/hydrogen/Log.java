package hydrogen;

public class Log
{	
	public static void e(String msg)
	{
		System.err.println("[Error] " + msg);
	}
	
	public static void d(String msg)
	{
		System.out.println("[Debug] " + msg);
	}
}
