package hydrogen.vcode.variable;

public class Variable
{
	public ELocationType locationType;
	public EDataType dataType;
	public String name;
	public int blockID, location;

	public Variable(ELocationType locationType, EDataType dataType, String name, int blockID, int location)
	{
		this.locationType = locationType;
		this.dataType = dataType;
		this.name = name;
		this.blockID = blockID;
		this.location = location;
	}
}
