package hydrogen.frontend.error;

import hydrogen.Log;

public class ParseError extends HydrogenError
{
	private static final long serialVersionUID = -2671421386095074361L;

	public ParseError(String msg)
	{
		super(msg);
	}

	@Override
	public void logError()
	{
		Log.s(getClass().getSimpleName() + ": " + getMessage());
		for (int i = 0; i < getStackTrace().length; i++)
			Log.de(getStackTrace()[i].toString());
	}
}
