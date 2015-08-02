package hydrogen.vcode.variable;

public class Variable
{
	public EDataType dataType;
	public String name;
	public int blockID, location;

	public Variable(EDataType dataType, String name, int blockID, int location)
	{
		this.dataType = dataType;
		this.name = name;
		this.blockID = blockID;
		this.location = location;
	}
}
