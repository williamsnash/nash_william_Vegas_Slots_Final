import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SlotsReader is the code needed to read from .txt, .bin, and .xml files
 * It starts at the read function where the string is turned into a file
 * It is then passed to another function to determine the file extension.
 * Based on the extension it is passed to either the readFromText, readFromBin or the readFromXml
 * @author William Nash
 *
 */
public class TileReader
{
	public ArrayList<Tile> readFromText(String fname)
	{
		File f = new File(fname);
		return readFromText(f);
	}
	public ArrayList<Tile> readFromText(File f)
	{
		try
		{
			ArrayList<Tile> results = new ArrayList<Tile>();
			Scanner slotFile = new Scanner(f);
			String line;
			String [] parts;
			int color, shape;
			while(slotFile.hasNextLine())
			{
				line = slotFile.nextLine().trim();
				if(line.length() > 0)
				{
					parts = line.split(" ");
					color = Integer.parseInt(parts[0]);
					shape = Integer.parseInt(parts[1]);
					Tile tile1 = new Tile(color,shape);
					results.add(tile1);
				}
			}
			slotFile.close();
			return results;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Tile> readFromBin(String fname)
	{
		File f = new File(fname);
		return readFromBin(f);
	} 
	@SuppressWarnings("unchecked")
	public ArrayList<Tile> readFromBin(File f)
	{
		try
		{
			ArrayList<Tile> tileRead;
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			tileRead = (ArrayList<Tile>)ois.readObject();
			ois.close();
			return tileRead;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public ArrayList<Tile> readFromXml(String fname)
	{
		File f = new File(fname);
		return readFromXml(f);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Tile> readFromXml(File f)
	{
		try {
			ArrayList<Tile> tileRead;
			XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
			tileRead = (ArrayList<Tile>)dec.readObject();
			dec.close();
			return tileRead;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public ArrayList<Tile> read(String fname)
	{
		File f = new File(fname);
		return read(f);
	}
	public ArrayList<Tile> read(File f)
	{
		String fname = f.getName().toUpperCase();
		if(fname.endsWith(".TXT"))
		{
			return readFromText(f);
		}
		if(fname.endsWith(".BIN"))
		{
			return readFromBin(f);
		}
		if(fname.endsWith(".XML"))
		{
			return readFromXml(f);
		}
		return null;
	}
}
