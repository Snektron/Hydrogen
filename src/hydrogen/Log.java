package hydrogen;

public class Log
{
	// level:
	// 0 = debug
	// 1 = verbose
	// 2 = warn
	// 3 = error (always logged)
	static int level = 0;
	
	public static void setLevel(int newlevel)
	{
		level = newlevel;
	}
	
	public static void e(String msg)
	{
		System.err.println("[E] " + msg);
	}
	
	public static void w(String msg)
	{
		System.err.println("[W] " + msg);
	}
	
	public static void v(String msg)
	{
		System.out.println("[V] " + msg);
	}
	
	public static void d(String msg)
	{
		System.out.println("[D] " + msg);
	}
}
