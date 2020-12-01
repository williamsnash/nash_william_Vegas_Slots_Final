import java.util.ArrayList;
import java.util.Random;

public class TileRandomizer
{
	private SlotsDrawing drawSlot;
	
	public ArrayList<Tile> tileDrawRan(ArrayList<Tile> tiles2)
	{
		drawSlot = new SlotsDrawing();
		Tile tile;
		Random ran = new Random();
		for(int i = 0; i < tiles2.size(); i++)
		{
			tile = new Tile();
			tile.setTileRandom(ran);
			tiles2.set(i, tile);
		}
		return tiles2;
	}
}
