import java.util.ArrayList;

/**
 * This Class will perform the checks to see if the user got a match
 * @author William Nash
 *
 */
public class TileChecker
{
	private ArrayList<Tile> tiles;
	
	public String checkerTile(SlotsDrawing drawSlots)
	{
    	tiles = drawSlots.getTiles();
    	int tileavg = 0;
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
    	//All shapes and colors = x100
    	//Just colors = x25
	}
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
