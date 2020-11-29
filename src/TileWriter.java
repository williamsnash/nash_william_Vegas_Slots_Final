import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * SlotsWriter is the code needed to write to .txt, .bin, and .xml files
 * It starts at the write function where the string is turned into a file
 * It is then passed to another function to determine the file extension.
 * Based on the extension it is passed to either the writeToText, writeToBin or the writeToXml
 * @author William Nash
 *
 */
@SuppressWarnings("unused")
public class TileWriter
{
	public boolean writeToText(String fname, ArrayList<Tile> tiles)
	{
		File f = new File(fname);
		return writeToText(f, tiles);
	}
	public boolean writeToText(File f, ArrayList<Tile> tiles)
	{
		try
		{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			for(Tile tile: tiles)
			{
				pw.println(tile);
			}
			pw.close();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean writeToBin(String fname, ArrayList<Tile> tiles)
	{
		File f = new File(fname);
		return writeToBin(f,tiles);
	}
	public boolean writeToBin(File f, ArrayList<Tile> tiles)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(tiles);
			oos.close();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public boolean writeToXml(String fname, ArrayList<Tile> tiles)
	{
		File f = new File(fname);
		return writeToXml(f, tiles);
	}
	public boolean writeToXml(File f, ArrayList<Tile> tiles)
	{
		try
		{
			XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
			enc.writeObject(tiles);
			enc.close();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	
	
	
	
	public boolean write(String fname, ArrayList<Tile> tiles)
	{
		File f = new File(fname);
		return write(f, tiles);
	}
	public boolean write(File f, ArrayList<Tile> tiles)
	{
		String fname = f.getName().toUpperCase();
		if(fname.endsWith(".TXT"))
		{
			return writeToText(f, tiles);
		}
		else if(fname.endsWith(".BIN"))
		{
			return writeToBin(f, tiles);
		}
		else if(fname.endsWith(".XML"))
		{
			return writeToXml(f, tiles);
		}
		else
		{
			return false;
		}
	}
}