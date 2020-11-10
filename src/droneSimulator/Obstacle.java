package droneSimulator;

/**
 * Obstacle Abstract class
 * 
 * @author Andreas Paridis
 *
 */
public abstract class Obstacle {

	protected double obstacleX, obstacleY, obstacleSize; // position and size of ball
	protected char obstacleColor; // used to set colour
	static int obstacleCounter = 1; // used to give each ball a unique identifier
	protected int obstacleID; // unique identifier for item

	Obstacle() {
		this(100, 100, 80);
	}

	/**
	 * Constructor an object of size sz at position dx,dy
	 * 
	 * @param ox X coordinate of the Obstacle
	 * @param oy Y coordinate of the Obstacle
	 * @param sz Size of the Obstacle
	 */
	Obstacle(double ox, double oy, double sz) {
		obstacleX = ox;
		obstacleY = oy;
		obstacleSize = sz;
		obstacleID = obstacleCounter++;
		obstacleColor = 'c';
	}

	/**
	 * return x position
	 * 
	 * @return X coordinate of the Obstacle
	 */
	protected double getX() {
		return this.obstacleX;
	}

	/**
	 * return y position
	 * 
	 * @return Y coordinate of the Obstacle
	 */
	protected double getY() {
		return this.obstacleY;
	}

	/**
	 * return the id of obstacle
	 * 
	 * @return Obstacle ID
	 */
	protected int getID() {
		return this.obstacleID;
	}

	/**
	 * return size of obstacle
	 * 
	 * @return Size of the Obstacle
	 */
	protected double getObstacleSize() {
		return this.obstacleSize;
	}

	/**
	 * return size of obstacle
	 * 
	 * @return Size of the Obstacle
	 */
	protected double getSize() {
		return this.obstacleSize;
	}

	/**
	 * set the obstacle at position dx,dy
	 * 
	 * @param dx X position of Obstacle
	 * @param dy Y position of Obstacle
	 */
	protected void setXY(double dx, double dy) {
		obstacleX = dx;
		obstacleY = dy;
	}

	/**
	 * resets the id of the drones
	 * 
	 */
	protected void resetID() {
		Obstacle.obstacleCounter = 1;
	}

	/**
	 * Draw obstacle on canvas as the specified shape
	 * 
	 * @param mc Canvas where objects are drawn
	 */
	protected void drawObstacle(MyCanvas mc) {
		mc.showCircle(this.obstacleX, this.obstacleY, this.obstacleSize, this.obstacleColor);
	}

	/**
	 * return obstacle type
	 * 
	 * @return String defining the type
	 */
	protected String getStrType() {
		return "Obstacle";
	}

	/**
	 * String that includes the ID and position of obstacle
	 * 
	 * @return String with information about the obstacle
	 */
	public String toString() {
		return this.getStrType() + " " + this.getID() + " at " + Math.round(this.getX()) + ", "
				+ Math.round(this.getY());
	}

	/**
	 * (Abstract) Method to check the obstacles in the arena
	 * 
	 * @param mc
	 */
	protected abstract void checkObstacle(DroneArena a);

	/**
	 * (Abstract) Method for adjusting the obstacle
	 */
	protected abstract void adjustObstacle();

	/**
	 * Checks if the drone in dx,dy,sz is hitting this drone
	 *
	 * 
	 * @param ox X position of the drone
	 * @param oy Y position of the drone
	 * @param sz size of the drone
	 * @return true if the distance between obstacle and x,y < size + rad
	 */
	protected boolean hittingObstacle(double ox, double oy, double sz) {
		return (ox - obstacleX) * (ox - obstacleX) + (oy - obstacleY) * (oy - obstacleY) < (sz + obstacleSize)
				* (sz + obstacleSize);
	} // hitting if dist between ball and ox,oy < ist rad + or

	/**
	 * check if obstacle gets hit
	 * 
	 * @param randObstacle - obstacle
	 * @return true if colliding
	 */
	protected boolean obstacleHitting(Obstacle randObstacle) {
		return hittingObstacle(randObstacle.getX(), randObstacle.getY(), randObstacle.getSize());
	}
}
