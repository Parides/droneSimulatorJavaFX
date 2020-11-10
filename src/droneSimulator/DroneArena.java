package droneSimulator;

import java.util.ArrayList;

/**
 * This is the droneArena class. It is responsible for the drones listing and
 * collision checking
 * 
 * @author Andreas Paridis
 *
 */
public class DroneArena {
	double arenaX, arenaY; // arena sizes
	public ArrayList<Drone> allDrones; // drone array list
	public ArrayList<Obstacle> allObstacles; // obstacle array list
	double randX, randY; // RNG numbers

	/**
	 * Default constructor
	 */
	DroneArena() {
		this(1024, 768, 'a');
	}

	/**
	 * Constructor. Create arena size ax,ay with difficulty c
	 * 
	 * @param ax X size of the arena
	 * @param ay Y size of the arena
	 * @param c  Character defining the difficulty
	 */
	DroneArena(double ax, double ay, char c) {
		arenaX = ax;
		arenaY = ay;

		// All lists initialised
		allDrones = new ArrayList<Drone>();
		allObstacles = new ArrayList<Obstacle>();

		clearArena(); // Clears arena from everything
		showDifficulty(c); // Shows the obstacles and the pre-made difficulties
		showObstacles(c); // Shows obstacles for the certain difficulty

	}

	/**
	 * return arena X size
	 * 
	 * @return X size of arena
	 */
	protected double getX() {
		return arenaX;
	}

	/**
	 * return arena Y size
	 * 
	 * @return Y size of arena
	 */
	protected double getY() {
		return arenaY;
	}

	/**
	 * Shows the obstacles associated with the difficulty c
	 * 
	 * @param c difficulty
	 */
	public void showObstacles(char c) {
		clearArena();
		if (c == 'e') {
			// Outer
			allObstacles.add(new Block(100, 100, 10)); // top left
			allObstacles.add(new Block(900, 100, 10)); // top right
			allObstacles.add(new Block(100, 700, 10));// bottom left
			allObstacles.add(new Block(900, 700, 10)); // bottom right
			// inner
			allObstacles.add(new Block(300, 300, 10)); // top left
			allObstacles.add(new Block(700, 300, 10)); // top right
			allObstacles.add(new Block(300, 500, 10)); // bottom left
			allObstacles.add(new Block(700, 500, 10)); // bottom right
			// middle void
			allObstacles.add(new VoidObstacle(this.arenaX / 2, this.arenaY / 2, 20));
			// player
			allObstacles.add(new PlayerObstacle(this.arenaX / 2, this.arenaY / 4));
		}
		if (c == 'm') {
			// Outer
			allObstacles.add(new Block(100, 100, 10)); // top left
			allObstacles.add(new Block(900, 100, 10)); // top right
			allObstacles.add(new Block(100, 700, 10));// bottom left
			allObstacles.add(new Block(900, 700, 10)); // bottom right
			// inner
			allObstacles.add(new Block(400, 300, 10)); // top left
			allObstacles.add(new Block(400, 400, 10)); // mid left
			allObstacles.add(new Block(620, 300, 10)); // top right
			allObstacles.add(new Block(620, 400, 10)); // mid right
			allObstacles.add(new Block(400, 500, 10)); // bottom left
			allObstacles.add(new Block(620, 500, 10)); // bottom right
			// middle void
			allObstacles.add(new VoidObstacle(this.arenaX / 2, this.arenaY / 2, 20));
			// player
			allObstacles.add(new PlayerObstacle(this.arenaX / 2, this.arenaY / 4));
		}

		if (c == 'h') {
			// Outer
			allObstacles.add(new Block(100, 100, 20)); // top left
			allObstacles.add(new Block(900, 100, 20)); // top right
			allObstacles.add(new Block(100, 700, 20));// bottom left
			allObstacles.add(new Block(900, 700, 20)); // bottom right
			// inner
			allObstacles.add(new Block(400, 300, 20)); // top left
			allObstacles.add(new Block(400, 400, 20)); // mid left
			allObstacles.add(new Block(620, 300, 20)); // top right
			allObstacles.add(new Block(620, 400, 20)); // mid right
			allObstacles.add(new Block(400, 500, 20)); // bottom left
			allObstacles.add(new Block(620, 500, 20)); // bottom right
			// middle void
			allObstacles.add(new VoidObstacle(this.arenaX / 2, this.arenaY / 2, 20));
			// player
			allObstacles.add(new PlayerObstacle(this.arenaX / 2, this.arenaY / 4));
		}

		if (c == 'i') {
			// Outer
			allObstacles.add(new Block(100, 100, 30)); // top left
			allObstacles.add(new Block(900, 100, 30)); // top right
			allObstacles.add(new Block(100, 700, 30));// bottom left
			allObstacles.add(new Block(900, 700, 30)); // bottom right
			// inner
			allObstacles.add(new Block(400, 300, 30)); // top left
			allObstacles.add(new Block(400, 400, 30)); // mid left
			allObstacles.add(new Block(620, 300, 30)); // top right
			allObstacles.add(new Block(620, 400, 30)); // mid right
			allObstacles.add(new Block(400, 500, 30)); // bottom left
			allObstacles.add(new Block(620, 500, 30)); // bottom right
			allObstacles.add(new Block(510, 200, 30)); // middle middle top
			allObstacles.add(new Block(510, 600, 30)); // middle middle bottom
			// middle void
			allObstacles.add(new VoidObstacle(this.arenaX / 2, this.arenaY / 2, 20));
			// player
			allObstacles.add(new PlayerObstacle(this.arenaX / 2, this.arenaY / 4));
		}

	}

	/**
	 * Clears all drones & Obstacles from the arena and resets all ID
	 */
	public void clearArena() {

		for (Drone d : allDrones) {
			d.resetID();
		}
		for (Obstacle c : allObstacles) {
			c.resetID();
		}
		allObstacles.clear(); // clear obstacles
		allDrones.clear(); // clear drones
	}

	/**
	 * Clears ONLY drones from the arena and resets all ID
	 */
	public void clearDrones() {
		for (Drone d : allDrones) {
			d.resetID();
		}
		allDrones.clear(); // clear drones
	}

	/**
	 * Displays the obstacles associated with the level given by the parameter c
	 * 
	 * @param diff difficulty of selection
	 */
	public void showDifficulty(char diff) {
		switch (diff) {
		case 'd':
			showObstacles(diff);
			break;
		case 'e':
			showObstacles(diff);
			for (int i = 0; i < 8; i++) { // Loop 8 times
				addFlyDrone(); // Spawn 2 FlyDrones
			}
			for (int i = 0; i < 2; i++) { // Loop 2 times
				addFrenzyDrone(); // Spawn 2 FrenzyDrones
			}
			break;
		case 'm':
			showObstacles(diff);
			for (int i = 0; i < 11; i++) {
				addFlyDrone();
			}
			for (int i = 0; i < 3; i++) {
				addFrenzyDrone();
			}
			break;
		case 'h':
			showObstacles(diff);
			for (int i = 0; i < 15; i++) {
				addFlyDrone();
			}
			for (int j = 0; j < 5; j++) {
				addFrenzyDrone();
			}
			break;
		case 'i':
			showObstacles(diff);
			for (int i = 0; i < 15; i++) {
				addFlyDrone();
			}
			for (int j = 0; j < 10; j++) {
				addFrenzyDrone();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Gets the score from the voidObstacle
	 * 
	 * @return the score
	 */
	public int showScore() {
		int score = 0;
		for (Obstacle b : allObstacles) { // go through all Obstacles
			if (b instanceof VoidObstacle) { // If its a voidObstacle
				score = ((VoidObstacle) b).showMeals(); // Get the meals
			}
		}
		return score;
	}

	/**
	 * Go through all objects of the array lists and draw them on the canvas mc
	 * 
	 * @param mc canvas
	 */
	public void drawArena(MyCanvas mc) {
		for (Drone b : allDrones)
			b.drawDrone(mc); // draw all Drones
		for (Obstacle a : allObstacles)
			a.drawObstacle(mc); // draw all Obstacles
	}

	/**
	 * Goes through all balls & sees if change is needed (angle etc)
	 */
	public void checkDrones() {
		for (Drone b : allDrones)
			b.checkDrone(this); // check all balls
	}

	/**
	 * Goes through all drones checks and makes required changes
	 */
	public void adjustDrones() {
		for (Drone b : allDrones)
			b.adjustDrone();
	}

	/**
	 * Set the playerObstacle at px,py
	 * 
	 * @param px X position of player
	 * @param py Y position of player
	 */
	public void setPlayer(double px, double py) {
		for (Obstacle b : allObstacles)
			if (b instanceof PlayerObstacle) // if its a playerObstacle
				b.setXY(px, py); // Set on px,py
	}

	/**
	 * Goes though all objects in ALL lists
	 * 
	 * @return String containing all objects in arena and their positions
	 */
	public ArrayList<String> showStatus() {
		ArrayList<String> ans = new ArrayList<String>(); // Initialise array List to store all info
		ans.add("Drones:");
		ans.add(" "); // Empty Line
		if (!allDrones.isEmpty()) { // if there are drones
			for (Drone b : allDrones)
				ans.add(b.toString()); // Store all drones
			ans.add(" ");
		} else {
			ans.add("NO DRONES YET!"); // Show message on right pane
			ans.add(" ");
		}

		ans.add("Obstacles:");
		ans.add(" ");
		if (!allObstacles.isEmpty()) { // if there are obstacles
			for (Obstacle d : allObstacles) {
				if (d instanceof PlayerObstacle) { // If player Separate from rest
					ans.add(" ");
					ans.add("Player: ");
					ans.add(d.toString());
				} else {
					ans.add(d.toString()); // Store all obstacles
				}
			}
		} else {
			ans.add("NO OBSTACLES YET"); // Show message on right pane
		}

		ans.add(" ");
		if (!allObstacles.isEmpty()) {
			ans.add("Your current score is " + showScore()); // show Score
		} else {
			ans.add("GL HF");
		}

		return ans; // Return List
	}

	/**
	 * Checks if the drone is about to hit a wall, another drone or an obstacle
	 * 
	 * @param x     X position of drone
	 * @param y     Y position of drone
	 * @param rad   size of drone
	 * @param ang   angle of travel of drone
	 * @param notID - Used to remote the drone being checked from the check itself
	 * @return the angle that is needed for the drone to "Bounce off"
	 */
	public double checkDroneAngle(double x, double y, double rad, double ang, int notID) {
		double ans = ang;

		// Wall Hit

		// if drone tries to go through X axis (LEFT-RIGHT Walls) mirror angle
		if (x < rad + 15 || x > arenaX - rad - 15) {
			ans = 180 - ans; // mirrored angle
		}

		// if drone tries to go through X axis (TOP-BOT Walls) mirror angle
		if (y < rad + 15 || y > arenaY - rad - 15) {
			ans = -ans; // mirrored angle
		}

		// Drone Hit

		for (Drone b : allDrones) {
			for (Obstacle d : allObstacles) {
				// Check collision with all drones except itself
				if (b.getID() != notID && b.hitting(x, y, rad)) {
					ans = 180 * Math.atan2(y - b.getY(), x - b.getX()) / Math.PI; // Reflect approriate angle
				}
				// Check collision for the SPECIFIC drone
				if (b.getID() == notID && b.hittingObstacle(x, y, rad, d)) {
					ans = 180 * Math.atan2(y - d.getY(), x - d.getX()) / Math.PI;
					if (d instanceof VoidObstacle) { // if void obstacle
						((VoidObstacle) d).checkObstacle(this); // check if it got hit by a drone
					}
				}
			}
		}
		return ans; // return the angle
	}

	/**
	 * Check if the drones collide with any obstacles
	 * 
	 * @param ox X position of object
	 * @param oy Y position of object
	 * @param or SIZE of object
	 * @return true if they collide
	 */
	public boolean checkHitObstacle(double ox, double oy, double or) {
		boolean ans = false;
		for (Drone b : allDrones) {
			for (Obstacle d : allObstacles) {
				if (b.hittingObstacle(ox, oy, or, d)) { // checks if drone collides with obstacle
					ans = true;
					break;
				}
			}

		}
		return ans;
	}

	/**
	 * Generates a random number between 0 and arenaX
	 * 
	 * @return the RNG
	 */
	public double randX() {
		do {
			randX = Math.round((Math.random() * arenaX));
		} while (randX < 50 || randX > arenaX - 50);
		// Must be >50 and <arenaX-50 in order to avoid drones spawning inside borders
		return randX;
	}

	/**
	 * Generates a random number between 0 and arenaY
	 * 
	 * @return the RNG
	 */
	public double randY() {
		do {
			randY = Math.round((Math.random() * arenaY));
		} while (randY < 50 || randY > arenaY - 50);
		// Must be >50 and <arenaY-50 in order to avoid drones spawning inside borders
		return randY;
	}

	/**
	 * Creates a new FlyDrone and gives it the random X and Y values
	 */
	public void addFlyDrone() {
		allDrones.add(new FlyDrone(this.randX(), this.randY()));
	}

	/**
	 * Creates a new FrenzyDrone and gives it the random X and Y values
	 */
	public void addFrenzyDrone() {
		allDrones.add(new FrenzyDrone(this.randX(), this.randY()));
	}
}
