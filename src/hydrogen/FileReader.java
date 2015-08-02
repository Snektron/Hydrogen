package hydrogen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader
{	
	public static String read(String file) throws IOException
	{
		String out = "";
		
		File f = new File(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line;
		while((line = reader.readLine()) != null)
			out += line + "\n";
		reader.close();
		return out;
	}
}
