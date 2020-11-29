import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

/**
 * This class creates a new variable called Tile. 
 * It holds two integer called color and shape
 * @author William Nash
 *
 */
@SuppressWarnings("serial")
public class Tile implements Serializable
{
	//Copied from Solution
	private static final Color[] colors = {Color.YELLOW, Color.GREEN, Color.ORANGE, Color.RED, Color.BLUE};
	private static final String[] colorNames = {"yellow","green","orange","red","blue"};
	private static final String[] shapes = {"circle","square"};
	//
	private int color;
	private int shape;
	
	/**
	 * Default Constructor
	 */
	public Tile()
	{
		color = 0;
		shape = 0;
	}
	
	/**
	 * Non-Default Constructor
	 * @param color Integervalue for one of the 4 colors
	 * @param shape Integer value for one of the 2 shapes
	 */
	public Tile(int color, int shape)
	{
		setColor(color);
		setShape(shape);
	}
	
	/**
	 * Allows other classes to access the data
	 * @return returns integer
	 */
	public int getColor()
	{
		return color;
	}
	
	/**
	 * Taken From Solution
	 * Allows other classes to set the data
	 * Prevents values larger than 4 or smaller than 0
	 * @param Color int for the color of the tile
	 */
	public void setColor(int Color)
	{
		if (color < 0)
		{
			this.color = 0;
		}
		else if (color > 4)
		{
			this.color = 4;
		}else
		{
			this.color = Color;
		}

	}
	
	/**
	 * Allows other classes to access the data
	 * @return returns integer
	 */
	public int getShape()
	{
		return shape;
	}
	
	/**
	 * Taken from Solution
	 * Allows other classes to set the data
	 * Prevents values larger than 4 or smaller than 0
	 * @param color The integer value of the 4 colour options
	 */
	public void setShape(int shape)
	{
		if (shape < 0)
		{
			this.shape = 0;
		}
		else if (shape > 1)
		{
			this.shape = 1;
		}
		else
		{
			this.shape = shape;
		}
	}
	
	/**
	 * Allows println to print a text representation of the object
	 */
	@Override
	public String toString()
	{
		return String.format("%d %d",color,shape);
	}
	
	public String toStringFancy()
	{
		return String.format("%s %s", getColorName(), getShapeAsString());
	}
	
	/**
	 * Take from Solution
	 * This look at the length of the colors/ shapes array and picks a random number based on the length 
	 * @param ran A random number generator
	 */
	public void setTileRandom(Random ran)
	{
		color = ran.nextInt(colors.length);
		shape = ran.nextInt(shapes.length);
	}
	
	/**
	 * Taken from Solution
	 * @return the color in the proper use
	 */
	public Color getActualColor()
	{
		return colors[color];
	}
	
	/**
	 * Taken from Solution
	 * @return returns the name of the color as a string
	 */
	public String getColorName()
	{
		return colorNames[color];
	}
	
	/**
	 * Taken from Solution
	 * @return Gives string instead of integer
	 */
	public String getShapeAsString()
	{
		return shapes[shape];
	}
}
