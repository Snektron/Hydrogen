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
	
		BufferedReader reader;
		
		try {
			File f = new File(file);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String line;
			while((line = reader.readLine()) != null)
				out += line + "\n";
			reader.close();
			return out;
		} catch(IOException ioe) {
			if(reader != null) {
				reader.close();
			}
			throw ioe;
		}
	}
}
