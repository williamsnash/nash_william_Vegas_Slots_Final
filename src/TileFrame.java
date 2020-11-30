import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is program set the frame up for the slot machine 
 * It include the top bar options and sets the look of the frame
 * @author William Nash
 *
 */
@SuppressWarnings({ "unused", "serial" })
public class TileFrame extends JFrame
{
	private SlotsDrawing drawSlots;
	private Random ran;
	private ArrayList<Tile> tiles;
	private JTextField txtDollar;
	/**
	 * setupMenu sets up the top bar
	 * This allows users to save, load restart and exit the game
	 * It also includes a help tab if the user gets suck
	 */
	public void setupMenu(JButton max, JButton mid, JButton min)
	{
		Tile tileInfo = new Tile();
		JMenuBar slotBar = new JMenuBar();
		JMenu slotMenu = new JMenu("File");
		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				TileWriter tileWrite = new TileWriter();
				if(jfc.showSaveDialog(null)== JFileChooser.APPROVE_OPTION)
				{
					if(tileWrite.write(jfc.getSelectedFile(), drawSlots.getTiles()))
					{
						JOptionPane.showMessageDialog(null, "Wrote Tiles to file");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Failed to write Tiles");
					}
				}
			}
		});
		slotMenu.add(menuSave);
		JMenuItem menuLoad = new JMenuItem("Load");
		menuLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TileReader sr = new TileReader();
				JFileChooser jfc = new JFileChooser();
				if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					ArrayList<Tile> TileRead = sr.read(jfc.getSelectedFile());
					if(TileRead == null)
					{
						JOptionPane.showMessageDialog(null, "Could not read Tiles From File.");
					}
					else
					{
						drawSlots.setTiles(TileRead);
						repaint();
					}
				}

			}
		});
		slotMenu.add(menuLoad);
		JMenuItem menuRestart = new JMenuItem("Restart");
		menuRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDollar.setText(String.format("%s", "5"));
				max.setEnabled(true);
            	mid.setEnabled(true);
            	min.setEnabled(true);
				drawSlots.tileDrawRan();	
			}
		});
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		slotMenu.add(menuRestart);
		slotMenu.add(menuExit);
		slotBar.add(slotMenu);
		
		JMenu slotHelp = new JMenu("Help");
		JMenuItem helpAbout = new JMenuItem("About");
		helpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Slot Machine Program made by Will Nash\n https://github.com/williamsnash/nash_william_slot_machine");
			}
		});
		slotHelp.add(helpAbout);
		slotBar.add(slotHelp);
		setJMenuBar(slotBar);
	}
	/**
	 * This is the default constructor
	 */
	public TileFrame()
	{
		setLook();
	}
	
	/**
	 * The setlook function adds all the visuals
	 * It sets the look of the frame and add the containers
	 * It adds the buttons on the bottom to allow the user to bet away their life saving
	 */
	public void setLook() {
        setTitle("Vegas Baby Vegas Slot Machine");
        setBounds(100,100,800,380);
        
        Container c = getContentPane();
        
        c.setLayout(new BorderLayout());
        
        drawSlots = new SlotsDrawing();
        c.add(drawSlots, BorderLayout.CENTER);
        
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());

        //Max Button
        JButton btnMax = new JButton("Max");

        //Mid Button
        JButton btnMid = new JButton("Mid");
        
        //Min Button
        JButton btnMin = new JButton("Min");
       
        panelSouth.add(btnMax);
        panelSouth.add(btnMid);
        panelSouth.add(btnMin);
        
        //Dollar button
        JLabel labBal = new JLabel("$");
        panelSouth.add(labBal);
        txtDollar = new JTextField(8);
        txtDollar.setEditable(false);
        double startvalue = 5.00;
        txtDollar.setText(String.format("%.2f", startvalue));
        panelSouth.add(txtDollar);
	
        btnMax.addActionListener(new ActionListener() { //Max is working
            public void actionPerformed(ActionEvent e) {
            	double Dollar = Double.parseDouble(txtDollar.getText());
            	drawSlots.tileDrawRan();
            	TileChecker check = new TileChecker();
            	String win = check.checkerTile(drawSlots);
            	if(win.equalsIgnoreCase("Both"))
            	{
            		//System.out.println("YOU MATCH BOTH COLOR AND SHAPE- Max");
            		Dollar = Dollar * 100;
					txtDollar.setText(String.format("%.2f", Dollar));
            	}
            	else if(win.equalsIgnoreCase("Color"))
            	{
            		//System.out.println("You matched Color- Max");
            		Dollar = Dollar * 25;
            		txtDollar.setText(String.format("%.2f", Dollar));
            	}
            	else {
            		txtDollar.setText(String.format("%s", "0"));
                    if(Double.parseDouble(txtDollar.getText()) < 0.01)
                    {
                    	btnMax.setEnabled(false);
                    	btnMid.setEnabled(false);
                    	btnMin.setEnabled(false);
                    }
            	}
            }
        });

        btnMid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	double Dollar = Double.parseDouble(txtDollar.getText())/2;
            	drawSlots.tileDrawRan();
            	TileChecker check = new TileChecker();
            	String win = check.checkerTile(drawSlots);
            	if(win.equalsIgnoreCase("Both"))
            	{
            		//System.out.println("YOU MATCH BOTH COLOR AND SHAPE- Mid");
            		Dollar = Dollar * 50;
					txtDollar.setText(String.format("%.2f", Dollar));
            	}
            	else if(win.equalsIgnoreCase("Color"))
            	{
            		//System.out.println("You matched Color- Mid");
            		Dollar = Dollar * 10;
					txtDollar.setText(String.format("%.2f", Dollar));
            	}
            	else {
            		txtDollar.setText(String.format("%.2f", Dollar));
            		if(Double.parseDouble(txtDollar.getText()) <= 0.01)
                    {
            			txtDollar.setText("0");
                    	btnMax.setEnabled(false);
                    	btnMid.setEnabled(false);
                    	btnMin.setEnabled(false);
                    }
            	}
            }  
        });
        
        btnMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	double Dollar = Double.parseDouble(txtDollar.getText())*0.9;
            	drawSlots.tileDrawRan();
            	TileChecker check = new TileChecker();
            	String win = check.checkerTile(drawSlots);
            	if(win.equalsIgnoreCase("Both"))
            	{
            		//System.out.println("YOU MATCH BOTH COLOR AND SHAPE- Min");
            		Dollar = Dollar * 10;
					txtDollar.setText(String.format("%.2f", Dollar));
            	}
            	else if(win.equalsIgnoreCase("Color"))
            	{
            		//System.out.println("You matched Color- Min");
            		Dollar = Dollar * 5;
					txtDollar.setText(String.format("%.2f", Dollar));
            	}
            	else {
            		txtDollar.setText(String.format("%.2f", Dollar));
            		if(Double.parseDouble(txtDollar.getText()) <= 0.05)
                    {
            			txtDollar.setText("0");
                    	btnMax.setEnabled(false);
                    	btnMid.setEnabled(false);
                    	btnMin.setEnabled(false);
                    }
            	}
            }
        });
        c.add(panelSouth,BorderLayout.SOUTH);
        setupMenu(btnMax, btnMid, btnMin);

	}
}