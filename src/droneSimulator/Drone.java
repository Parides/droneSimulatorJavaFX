package droneSimulator;

import java.util.Random;

/**
 * Drone Abstract class
 * 
 * @author Andreas Paridis
 *
 */
public abstract class Drone {
	protected double droneX, droneY, droneSize; // x,y position and size(radius) of drone
	protected char droneColor; // colour variable
	static int droneCounter = 1; // used to increment & track drones
	protected int droneID; // unique drone ID

	Drone() {
		this(100, 100, 10);
	}

	/**
	 * Constructor a drone of size sz at position dx,dy
	 * 
	 * @param dx X position of the drone
	 * @param dy Y position of the drone
	 * @param sz size of the drone
	 */
	Drone(double dx, double dy, double sz) {
		droneX = dx;
		droneY = dy;
		droneSize = sz;
		droneID = droneCounter++; // set the identifier and increment class static
		droneColor = 'm';
	}

	/**
	 * return x position
	 * 
	 * @return X position of the drone
	 */
	protected double getX() {
		return this.droneX;
	}

	/**
	 * return y position
	 * 
	 * @return Y position of the drone
	 */
	protected double getY() {
		return this.droneY;
	}

	/**
	 * return size of drone
	 * 
	 * @return size of the drone
	 */
	protected double getSize() {
		return this.droneSize;
	}

	/**
	 * return the id of drone
	 * 
	 * @return ID of the drone
	 */
	protected int getID() {
		return this.droneID;
	}

	/**
	 * resets the id of the drones
	 * 
	 */
	protected void resetID() {
		droneCounter = 1;
	}

	/**
	 * Draws the drone as the specified shaped
	 * 
	 * @param mc where objects are drawn
	 */
	protected void drawDrone(MyCanvas mc) {
		mc.showCircle(droneX, droneY, droneSize, droneColor);
	}

	/**
	 * return drone type
	 * 
	 * @return String Defining object type
	 */
	protected String getStrType() {
		return "Drone";
	}

	/**
	 * String that includes the ID and position of drone
	 * 
	 * @return String showing drone information 
	 */
	public String toString() {
		return this.getStrType() + " " + this.getID() + " at " + Math.round(this.getX()) + ", "
				+ Math.round(this.getY());
		// used math.round since x,y are double numbers (0-1)
	}

	/**
	 * Generates a random angle for the drone to fly to
	 * 
	 * @return random angle
	 */
	protected double randAngle() {
		Random rand = new Random();
		return rand.nextInt(360);// generates a random number between 1-360
	}

	/**
	 * (Abstract) Method to check the drones in the arena
	 * 
	 * @param mc where objects are in
	 */
	protected abstract void checkDrone(DroneArena mc);

	/**
	 * (Abstract) Method for moving the drone around
	 */
	protected abstract void adjustDrone();

	/**
	 * Checks if the drone in dx,dy,sz is hitting this drone
	 *
	 * 
	 * @param dx X position of the drone
	 * @param dy Y position of the drone
	 * @param sz size of the drone
	 * @return true if the distance between drone and x,y < size + rad
	 */
	protected boolean hitting(double dx, double dy, double sz) {
		return (dx - droneX) * (dx - droneX) + (dy - droneY) * (dy - droneY) < (sz + droneSize) * (sz + droneSize);
	}

	/**
	 * check if drone hits another drone
	 * 
	 * @param randDrone - the other drone
	 * @return true if colliding
	 */
	protected boolean hitting(Drone randDrone) {
		return hitting(randDrone.getX(), randDrone.getY(), randDrone.getSize());
	}

	/**
	 * Checks if the drone in ox,sz,or is hitting this obstacle randObstacle
	 * 
	 * @param dx X position of the drone
	 * @param dy Y position of the drone
	 * @param sz Size of the drone
	 * @return true if the distance between drone and x,y < size + rad
	 */
	protected boolean hittingObstacle(double ox, double oy, double sz, Obstacle randObstacle) {
		return (ox - randObstacle.obstacleX) * (ox - randObstacle.obstacleX) + (oy - randObstacle.obstacleY)
				* (oy - randObstacle.obstacleY) < (sz + randObstacle.obstacleSize) * (sz + randObstacle.obstacleSize);
	} // true if distance between drone and x,y < size + rad

	/**
	 * check if drone hits anothe obstacle
	 * 
	 * @param randObstacle - the other drone
	 * @return true if colliding
	 */
	protected boolean hittingObstacle(Obstacle randObstacle) {
		return hitting(randObstacle.getX(), randObstacle.getY(), randObstacle.getSize());
	}
}
