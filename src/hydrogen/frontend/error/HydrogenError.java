package hydrogen.frontend.error;

import hydrogen.Log;

public class HydrogenError extends RuntimeException
{
	private static final long serialVersionUID = -7583117032927390707L;
	
	public HydrogenError(String msg)
	{
		super(msg);
	}
	
	public void logError()
	{
		Log.e(getClass().getSimpleName()+": "+getMessage());
		for (int i = 0; i < getStackTrace().length; i++)
			Log.e(getStackTrace()[i].toString());
	}
}
