import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class City extends JLabel {

	private String name;
	private int cx;
	private int cy;
	int cityID;
	double earnings;
	private CityStatus status;
	public static enum CityStatus {
		DEFAULT, IN_STACK, OUT_STACK
	};
	
	private String defaultMarker = "circle.png";
	private String inStackMarker = "circle_in.png";
	private String outStackMarker = "circle_out.png";

	private final int CIRC_SIZE = 44;
	private final int HALF_SIZE = CIRC_SIZE/2;
	private ImageIcon icon;
	
	/**
	 * Constructor for the City class
	 * @param id
	 * @param name
	 * @param cityX
	 * @param cityY
	 * @param earnings
	 */
	public City (int id, String name, int cityX, int cityY, double earnings) {
		super();
		
		this.cityID = id;
		this.name = name;
		this.cx = cityX;
		this.cy = cityY;
		this.earnings = earnings;
		this.status = CityStatus.DEFAULT;

		setMarker(defaultMarker);
		setSize(CIRC_SIZE, CIRC_SIZE);
		setLocation(cx - HALF_SIZE, cy - HALF_SIZE);
		setToolTipText(name + " <" + earnings + ">");
	}
	
	/**
	 * Updates the marker icon to be displayed on the map based on the "marked" status.
	 */
	public void updateMarker() {

		String iconFile;
		
		if (this.status == CityStatus.IN_STACK) {
			iconFile = inStackMarker;
		} else if (this.status == CityStatus.OUT_STACK) {
			iconFile = outStackMarker;
		} else {
			iconFile = defaultMarker;

		}
		
		setMarker(iconFile);

	}
	
	/**
	 * Used with updateMarker to set the marker icon of the city.
	 * @param markerFile
	 */
	private void setMarker (String markerFile) {
		ImageIcon icon = new ImageIcon(markerFile);
		Image img = icon.getImage();
		Image scaledImage = img.getScaledInstance(CIRC_SIZE, CIRC_SIZE, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(scaledImage);
		
		this.icon = newIcon;
		setIcon(newIcon);
	}
	
	/**
	 * Getter for the city's ID.
	 * @return
	 */
	public int getID () {
		return cityID;
	}
	
	/**
	 * Getter for the city's name.
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Getter for the city's X position. The icon will use this value when being placed so subtract half the icon size
	 * to center it horizontally.
	 */
	@Override
	public int getX() {
		return cx - HALF_SIZE;
	}
	
	/**
	 * Getter for the city's Y position. The icon will use this value when being placed so subtract half the icon size
	 * to center it vertically.
	 */
	@Override
	public int getY() {
		return cy - HALF_SIZE;
	}
	
	/**
	 * Getter for the city's earnings for the concert.
	 * @return the earnings from this city
	 */
	public double getEarnings () {
		return earnings;
	}


	/**
	 * Add a short pause so that the algorithm can be watched at a reasonable pace.
	 */
	private void pause () {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			
		}
	}

	/**
	 * Mark the city as on the stack and update its marker accordingly.
	 */
	public void markInStack () {
		pause();
		this.status = CityStatus.IN_STACK;
		updateMarker();
	}
	
	/**
	 * Mark the city as off the stack and update its marker accordingly.
	 */
	public void markOutOfStack () {
		pause();
		this.status = CityStatus.OUT_STACK;
		updateMarker();
	}
	
	/**
	 * Check if this city is marked on the stack.
	 * @return true if the city is marked on the stack, or false otherwise.
	 */
	public boolean isMarkedInStack () {
		return this.status == CityStatus.IN_STACK;
	}
	
	
	/**
	 * Check if this city is marked off the stack.
	 * @return true if the city is marked off the stack, or false otherwise.
	 */
	public boolean isMarkedOutOfStack () {
		return this.status == CityStatus.OUT_STACK;
	}
	
	/**
	 * Check if this city and other city are the same, based on city name.
	 * @param other the other city object on which the equality is checked.
	 * @return true if the two cities have the same name, or false otherwise.
	 */
	public boolean equals (City other) {
		return this.getName().equals(other.getName());
	}
	
	/**
	 * Return the city's name for printing.
	 */
	public String toString () {
		return name;
	}
}
