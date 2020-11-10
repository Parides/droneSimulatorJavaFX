package droneSimulator;

/**
 * FlyDrone Class. This drone is small and moves around the map relatively slow
 * 
 * @author Andreas Paridis
 *
 */
public class FlyDrone extends Drone {

	private double droneAngle, droneSpeed; // speed & angle of drone

	/**
	 * Create a Flydrone at dx,dy Since the size angle speed, colour and size should
	 * be the same for ALL flying drones they are in functions
	 * 
	 * @param fx X position of the drone
	 * @param fy Y position of the drone
	 */
	public FlyDrone(double fx, double fy) {
		super(fx, fy, flySize());
		droneAngle = randAngle();
		droneSpeed = flyingSpeed();
		droneColor = flyColour();
	}

	/**
	 * Defines the displayed name for ALL FlyDrones
	 * 
	 * @return name defining the drone
	 */
	protected String getStrType() {
		return "Flying Drone";
	}

	/**
	 * Defines the Size of ALL flyDrones
	 * 
	 * @return size
	 */
	private static double flySize() {
		return 5;
	}

	/**
	 * Defines the speed of ALL flyDrones
	 * 
	 * @return speed
	 */
	private double flyingSpeed() {
		return 2;
	}

	/**
	 * Defines the colour of ALL frenzy drones
	 * 
	 * @return speed
	 */
	private char flyColour() {
		return 's';
	}

	/**
	 * Change the angle that the drone is travelling if it hit a wall/obstacle/drone
	 * 
	 * @param DroneArena b where objects are in
	 */
	@Override
	protected void checkDrone(DroneArena a) {
		droneAngle = a.checkDroneAngle(droneX, droneY, droneSize, droneAngle, droneID);
	}

	/**
	 * Adjust the drone's speed based on its speed and angle of travel
	 */
	@Override
	protected void adjustDrone() {
		double angle = droneAngle * Math.PI / 180; // //convert to radians
		droneX += droneSpeed * Math.cos(angle); // new X position
		droneY += droneSpeed * Math.sin(angle); // new Y position
	}
}
