package droneSimulator;

/**
 * Frenzy Drone Class. This drone moves around the map really fast
 * 
 * @author Andreas Paridis
 *
 */
public class FrenzyDrone extends Drone {

	private double droneAngle, droneSpeed; // speed & angle of drone

	/**
	 * Create a frenzy drone at dx,dy Since the size angle speed, colour and size
	 * should be the same for ALL frenzy drones they are in functions
	 * 
	 * @param dx X position of the drone
	 * @param dy Y position of the drone
	 */
	public FrenzyDrone(double dx, double dy) {
		super(dx, dy, frenzySize());
		droneAngle = randAngle();
		droneSpeed = frenzySpeed();
		droneColor = frenzyColor();
	}

	/**
	 * Defines the displayed name for ALL frenzy drones
	 * 
	 * @return String definition of drone
	 */
	protected String getStrType() {
		return "Frenzy Drone";
	}

	/**
	 * Defines the Size of ALL frenzy drones
	 * 
	 * @return size 
	 */
	private static double frenzySize() {
		return 8;
	}

	/**
	 * Defines the speed of ALL frenzy drones
	 * 
	 * @return speed 
	 */
	private double frenzySpeed() {
		return 5;
	}

	/**
	 * Defines the colour of ALL frenzy drones
	 * 
	 * @return speed
	 */
	private char frenzyColor() {
		return 'g';
	}

	/**
	 * Change the angle that the drone is travelling if it hit a wall/obstacle/drone
	 * 
	 * @param DroneArena b where objects are in
	 */
	@Override
	protected void checkDrone(DroneArena b) {
		droneAngle = b.checkDroneAngle(droneX, droneY, droneSize, droneAngle, droneID);
	}

	/**
	 * Draws the drone as the specified shaped
	 * 
	 * @param MyCanvas mc where objects are drawn in
	 */
	protected void drawDrone(MyCanvas mc) {
		mc.showTriangle(droneX, droneY, droneSize, droneColor);

	}

	/**
	 * Adjust the drone's speed based on its speed and angle of travel
	 */
	@Override
	protected void adjustDrone() {
		double dAngle = droneAngle * Math.PI / 180; // //convert to radians
		droneX += droneSpeed * Math.cos(dAngle); // new X position
		droneY += droneSpeed * Math.sin(dAngle); // new Y position
	}

}
