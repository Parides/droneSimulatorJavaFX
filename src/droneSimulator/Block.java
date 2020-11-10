package droneSimulator;

/**
 * Block Class. This is a block that does not move. Basically an obstacle
 * 
 * @author Andreas Paridis
 *
 */
public class Block extends Obstacle {

	/**
	 * Construct an obstacle of size sz at position ox,oy
	 * 
	 * @param dx X coordinate of the Block
	 * @param dy Y coordinate of the Block
	 * @param sz size of the Block
	 */
	public Block(double ox, double oy, double sz) {
		super(ox, oy, sz);
		obstacleColor = blockColor();
	}

	/**
	 * Draw drone on canvas (UI)
	 * 
	 * @param mc canvas where objects are drawn
	 */
	protected void drawObstacle(MyCanvas mc) {
		mc.showRectangle(obstacleX, obstacleY, obstacleSize, obstacleColor);
	}

	/**
	 * Defines the displayed name for ALL Blocks
	 * 
	 * @return Defined name of object
	 */
	protected String getStrType() {
		return "Block";
	}

	/**
	 * Defines the colour of ALL Blocks
	 * 
	 * @return speed
	 */
	private char blockColor() {
		return 'r';
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
