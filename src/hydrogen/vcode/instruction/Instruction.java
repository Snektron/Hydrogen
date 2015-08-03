package hydrogen.vcode.instruction;

public class Instruction
{
	/**
	 * All classes extending this class are Virtual Code instructions
	 */
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName();
	}
}
