package hydrogen.frontent.error;

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
		Log.e(getClass().getSimpleName() + ": " + getMessage());
	}
}
