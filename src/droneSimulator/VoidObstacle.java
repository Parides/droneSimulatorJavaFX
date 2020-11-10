package droneSimulator;

/**
 * VoidObstacle class. This is a block that does not move. 
 * 
 * It has a counter in the middle that increments everytime it gets hit
 * 
 * @author Andreas Paridis
 *
 */
public class VoidObstacle extends Obstacle{

	private int meals;
	
	/**
	 * Constructor of a voidObstacle of size sz at position ox,dy
	 * 
	 * @param ox X coordinate of the object
	 * @param oy Y coordinate of the object
	 * @param sz Size of the object
	 */
	public VoidObstacle(double ox,double oy, double sz)
	{
		super(ox,oy,sz);
		obstacleColor = voidColor();
		meals = 0;
	}
	
	/**
	 * Defines the displayed name for ALL voidObstacles
	 * 
	 * @return name
	 */
	protected String getStrType() {
		return "Void";
	}
	
	/**
	 * Returns the number of times the obstacle has been hit
	 * 
	 * @return meals
	 */
	public int showMeals() {
		return this.meals;
	}
	
	/**
	 * Defines the colour of ALL Blocks
	 * 
	 * @return speed
	 */
	private char voidColor() {
		return 'p';
	}
	
	/**
	 * Increase the number of meals every time the void gets hit
	 * 
	 * @param DroneArena b Arena where the objects are in
	 */
	protected void checkObstacle(DroneArena a) {
		if (a.checkHitObstacle(this.getX(),this.getY(),this.getSize()))
		meals++;
	}
	
	protected void eat() {
		meals++;
	}
	
	/**
	 * Draws the obstacle as the specified shaped
	 * 
	 * @param MyCanvas mc Canvas on where the objects are drawn
	 */
	protected void drawObstacle(MyCanvas mc) {
		super.drawObstacle(mc);
		mc.showInt(this.obstacleX, this.obstacleY, meals);
	}
	
	@Override
	protected void adjustObstacle() {
	}
	
}
