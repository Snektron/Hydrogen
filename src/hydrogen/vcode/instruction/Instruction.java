package hydrogen.vcode.instruction;

import hydrogen.vcode.VirtualCode;

public class Instruction
{
	/**
	 * All classes extending this class are Virtual Code instructions
	 */

	public String toString(VirtualCode vcode)
	{
		return getClass().getSimpleName();
	}
}
