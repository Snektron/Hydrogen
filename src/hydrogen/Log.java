package hydrogen;

public class Log
{
	// level:
	// 0 = (d) Debug
	//     (de) Debug Error
	// 1 = (i) Info
	// 2 = (w) Warn
	// 3 = (e) Error (always logged)
	//     (s) Severe error
	static int level = 0;
	
	public static void setLevel(int newlevel)
	{
		level = newlevel;
	}
	
	public static void s(String msg)
	{
		System.err.println("[Severe error] " + msg);
	}
	
	public static void e(String msg)
	{
		System.err.println("[Error] " + msg);
	}
	
	public static void w(String msg)
	{
		if (level <= 2)
			System.err.println("[Warn] " + msg);
	}
	
	public static void i(String msg)
	{
		if (level <= 1)
			System.out.println("[Info] " + msg);
	}
	
	public static void de(String msg)
	{
		if (level <= 0)
			System.err.println("[Debug error] " + msg);
	}
	
	public static void d(String msg)
	{
		if (level <= 0)
			System.out.println("[Debug] " + msg);
	}
}
