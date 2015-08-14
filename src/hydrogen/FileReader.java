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
		BufferedReader reader = null;
		try{
			String line;
			StringBuilder out = new StringBuilder();
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
			while((line = reader.readLine()) != null)
				out.append(line+"\n");
			reader.close();
			return out.toString();
		}catch(IOException e)
		{
			if (reader != null)
				reader.close();			
			throw e;
		}
	}
}
