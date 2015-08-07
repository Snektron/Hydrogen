package hydrogen.vcode.data;

import hydrogen.frontend.token.ELocationType;

public class Variable
{
	public ELocationType locationType;
	public String name;
	public int blockID, location;

	public Variable(ELocationType locationType, String name, int blockID, int location)
	{
		this.locationType = locationType;
		this.name = name;
		this.blockID = blockID;
		this.location = location;
	}
}
