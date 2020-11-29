import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JPanel;

/**
 * This class will add the all the tiles for the user to look at
 * It creates/ randomizes the tiles so no round is like the last
 * @author William Nash
 *
 */
@SuppressWarnings("serial" )
class SlotsDrawing extends JPanel implements MouseListener
{
	private ArrayList<Tile> tiles;
	private Random ran; 
	
	/**
	 * This function allows other class to edit the private tiles arrayList
	 * @param tile and arrayList provided by another class
	 */
	public void setTiles(ArrayList<Tile> tile)
	{
		this.tiles = tile;
	}
	/**
	 * This function allows other class to read the private arrayList tiles
	 * @return an ArrayList<Tile> for other class to use
	 */
	public ArrayList<Tile> getTiles()
	{
		return tiles;
	}
	
	/**
	 * SlotsDrawing is the default constructor
	 * It creates and randomly assigns each tile a color and shape
	 */
	public SlotsDrawing()
	{
		addMouseListener(this);
		tiles = new ArrayList<Tile>();
		ran = new Random();
		Tile tile;
		for(int i = 0; i < 4; i++)
		{
			tile = new Tile();
			tile.setTileRandom(ran);
			tiles.add(tile);
		}
	}
	
	public void tileDrawRan()
	{
		Tile tile;
		for(int i = 0; i < tiles.size(); i++)
		{
			tile = new Tile();
			tile.setTileRandom(ran);
			tiles.set(i, tile);
		}
		repaint();
	}
	
	/**
	 * Taken from Solution
	 * uses the width of the cell to dynamically size the tiles
	 * loops the number of tiles and gets the color and shape then sets it
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int cellWidth = this.getWidth()/4;
		int tileSize = 4 * cellWidth/5;
		int shape;
		Color color;
		Tile tile;
		for(int i = 0; i < tiles.size(); i++)
		{
			tile = tiles.get(i);
			shape = tile.getShape();
			color = tile.getActualColor();
			g.setColor(color);
			if(shape == 0)
			{
				g.fillOval(i*cellWidth + cellWidth/10, cellWidth/10, tileSize, tileSize);
			}
			else if (shape == 1)
			{
				g.fillRect(i*cellWidth + cellWidth/10, cellWidth/10, tileSize, tileSize);
			}	
		}

	}
	/**
	 * Taken from Solution
	 * finds which tile is clicked
	 * then call the setTileRandom to randomize the tile
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int whichTile = e.getX()/(this.getWidth()/4);
		Tile tile = tiles.get(whichTile);
		tile.setTileRandom(ran);
		repaint();

	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}