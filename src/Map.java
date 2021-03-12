import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Map extends JFrame {

	private static final long serialVersionUID = 1L;

	private static Container contentPane;
	
	private final static int frame_length = 1100;
	private final static int frame_height = 881;
	private final static int map_length = 1000;
	private final static int map_height = 781;

	private JLayeredPane mainPanel;

	
	
	/* Constructor. Creates a panel to represent the game board and destroys
	       the panel when its window is closed.                                 */
	public Map() {
		super("Map of Canada");

		// Initialize panels.
		contentPane = getContentPane();
		mainPanel = new JLayeredPane();

		// Load Canada map.
		ImageIcon icon = new ImageIcon("canada.jpg");
		Image img = icon.getImage();
		Image scaledImage = img.getScaledInstance(map_length,map_height, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImage);
		JLabel canadaMap = new JLabel(icon);
		canadaMap.setSize(map_length, map_height);
		canadaMap.setLocation(50, 30);
		mainPanel.add(canadaMap, 0);

		addWindowListener(new WindowAdapter( ) {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}                
		}); 

		// Main panel properties.
		pack();
		setSize(frame_length, frame_height);
		setVisible(true);
		//setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		contentPane.setBackground(new Color(249, 249, 249));
		contentPane.add(mainPanel);
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		revalidate();
	}

	/**
	 * Add a marker icon to the map for the given City. 
	 * @param city
	 */
	public void addCity (City city) {
		mainPanel.add(city, city.getID());
	}

}
