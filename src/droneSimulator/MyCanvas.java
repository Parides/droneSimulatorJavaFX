package droneSimulator;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;

/**
 * Canvas class
 * 
 * @author Andreas Paridis
 *
 */
public class MyCanvas {
	private int canvasX = 1026; // constants for relevant sizes
	private int canvasY = 768;
	GraphicsContext gc;

	/**
	 * 
	 * Constructor that sets up the graphics with x,y canvas
	 * 
	 * @param graphics
	 * @param cs
	 */
	public MyCanvas(GraphicsContext graphics, int cx, int cy) {
		gc = graphics;
		canvasX = cx;
		canvasY = cy;
	}

	/**
	 * Gets canvas size Y
	 * 
	 * @return canvas X size
	 */
	public int getXCanvasSize() {
		return canvasX;
	}

	/**
	 * Gets canvas size Y
	 * 
	 * @return canvas Y size
	 */
	public int getYCanvasSize() {
		return canvasY;
	}

	/**
	 * Wipe the canvas
	 */
	public void clearCanvas() {
		gc.clearRect(0, 0, canvasX, canvasY); // clear canvas
	}

	// /**
	// * drawIt ... draws object defined by given image at position and size
	// *
	// *
	// * @param i
	// * image
	// * @param x
	// * xposition in range 0..1
	// * @param y
	// * @param sz
	// * size
	// */
	public void drawIt(Image i, double x, double y, double sz) {
		// to draw centred at x,y, give top left position and x,y size
		// sizes/position in range 0..1, so scale to canvassize
		gc.drawImage(i, canvasX * (x - sz / 2), canvasY * (y - sz / 2), canvasX * sz, canvasY * sz);
	}

	/**
	 * 
	 * Converts the character into the according colour
	 * 
	 * @param c character of colour
	 * @return Color
	 */
	Color colFromChar(char c) {
		Color ans = Color.BLACK;
		switch (c) {
		case 'y':
			ans = Color.YELLOW;
			break;
		case 'w':
			ans = Color.WHITE;
			break;
		case 'r':
			ans = Color.RED;
			break;
		case 'g':
			ans = Color.GREEN;
			break;
		case 'b':
			ans = Color.BLUE;
			break;
		case 'o':
			ans = Color.ORANGE;
			break;
		case 'm':
			ans = Color.MAROON;
			break;
		case 'n':
			ans = Color.BLACK;
			break;
		case 'p':
			ans = Color.BLUEVIOLET;
			break;
		case 's':
			ans = Color.SANDYBROWN;
			break;
		}
		return ans;
	}

	/**
	 * Fills the graphics shape with set c
	 * 
	 * @param c colour
	 */
	public void setFillColour(Color c) {
		gc.setFill(c);
	}

	/**
	 * Shows the graphic circle at position x,y with size sz and color col
	 * 
	 * @param x   X position of the object
	 * @param y   Y position of the object
	 * @param sz  Size of the object
	 * @param col Colour of the object
	 */
	public void showCircle(double x, double y, double sz, char col) {
		setFillColour(colFromChar(col)); // set the fill colour
		gc.fillArc(x - sz, y - sz, sz * 2, sz * 2, 0, 360, ArcType.ROUND); // Fill Circle
	}

	/**
	 * Shows the graphic rectangle at position x,y with size sz and color col
	 * 
	 * @param x  X position of the object
	 * @param y  Y position of the object
	 * @param sz Size of the object
	 */
	public void showTriangle(double x, double y, double sz, char col) {
		setFillColour(colFromChar(col));
		gc.fillRoundRect(x - sz, y - sz, sz * 2, sz * 2.6, 15, 15);
	}

	/**
	 * Shows the graphic round rectangle at position x,y with size sz and color col
	 * 
	 * @param x  X position of the object
	 * @param y  Y position of the object
	 * @param sz Size of the object
	 */
	public void showRectangle(double x, double y, double sz, char col) {
		setFillColour(colFromChar(col));
		gc.fillRoundRect(x - sz, y - sz, sz * 2, sz * 2, 15, 15);
	}

	/**
	 * Writes text from String S at position x,y
	 * 
	 * @param x X position of the object
	 * @param y Y position of the object
	 * @param s Size position of the object
	 */
	public void showText(double x, double y, String s) {
		gc.setTextAlign(TextAlignment.CENTER); // set horizontal alignment
		gc.setTextBaseline(VPos.CENTER); // vertical
		gc.setFill(Color.WHITE); // colour in white
		gc.fillText(s, x, y); // print score as text
	}

	/**
	 * Shows integer i at position x,y
	 * 
	 * @param x X position of the object
	 * @param y Y position of the object
	 * @param i Integer that should be shown
	 */
	public void showInt(double x, double y, int i) {
		showText(x, y, Integer.toString(i)); // convert to string to show
	}
}
