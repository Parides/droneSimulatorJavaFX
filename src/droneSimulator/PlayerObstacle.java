package droneSimulator;

/**
 * PlayerObstacle class. This is a block that doesn't not fly around but is
 * controlled by the players mouse click
 * 
 * 
 * @author Andreas Paridis
 *
 */
public class PlayerObstacle extends Obstacle {

	/**
	 * Constructor of a playerObstacle of size sz at position ox,dy
	 * 
	 * @param ox X coordinate of the player
	 * @param oy Y coordinate of the player
	 * @param sz Size of the player
	 */
	public PlayerObstacle(double x, double y) {
		super(x, y, 10);
		obstacleColor = playerColour();
	}

	/**
	 * return obstacle type
	 * 
	 * @return Type
	 */
	protected String getStrType() {
		return "Player";
	}

	/**
	 * String that ONLY includes X AND Y positions of player obstacle
	 * 
	 * @return String containing player information
	 */
	public String toString() {
		return this.getStrType() + " at " + Math.round(this.getX()) + ", " + Math.round(this.getY());
		// used math.round since x,y are double numbers (0-1)
	}

	/**
	 * Defines the colour of ALL playerObstacles
	 * 
	 * @return speed
	 */
	private char playerColour() {
		return 'y';
	}

	@Override
	protected void checkObstacle(DroneArena a) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void adjustObstacle() {
		// TODO Auto-generated method stub

	}

}
