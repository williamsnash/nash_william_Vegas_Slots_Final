import java.util.ArrayList;

/**
 * This Class will perform the checks to see if the user got a match
 * @author William Nash
 *
 */
public class TileChecker
{
	private ArrayList<Tile> tiles;
	/**
	 * checkerTile is a function to check to see if the color of the tiles match
	 * @param drawSlots is used to access the tiles already created
	 * @return returns both( if both color and shape are match) or color(if only color is matched) and if none match is present the returns nothing
	 */
	public String checkerTile(SlotsDrawing drawSlots)
	{
    	tiles = drawSlots.getTiles();
    	ArrayList<Integer> tileColor = new ArrayList<Integer>();
    	ArrayList<Integer> tileShape = new ArrayList<Integer>();
    	for(Tile tile: tiles)
    	{
    		tileColor.add(tile.getColor());
    		tileShape.add(tile.getShape());
    	}
    	
    	for(int i = 0; i < tiles.size(); i++)
    	{
    		if(tileColor.get(0) == i)
        	{
        		if(tileColor.get(1) == i)
        		{
        			if(tileColor.get(2) == i)
        			{
        				if(tileColor.get(3) == i)
        				{
        					boolean win = shapeCheck(tileShape);
        					if(win == true)
        					{
        						return "Both";
        					}
        					else if(win == false)
        					{
        						return "Color";
        					}
        				}
        			}
        		}
        	}
    	}
    	return "none";
	}
	
	/**
	 * shapeCheck is used because I didnt like the look of the many stacked if statments, and it is used in the same way every time
	 * Operates in the same way as the color checker
	 * @param tileShape Created in the previous function and stores shape data
	 * @return true if all the shaped match, and false if not
	 */
	public boolean shapeCheck(ArrayList<Integer> tileShape)
	{
		boolean win = false;
		for(int j = 0; j < 2; j++)
		{
			if(tileShape.get(0) == j)
			{
				if(tileShape.get(1) == j)
				{
					if(tileShape.get(2) == j)
					{
						if(tileShape.get(3) == j)
    					{
    						win = true;
    						return win;
    					}
					}
				}
			}
		}
		return win;
	}

}
