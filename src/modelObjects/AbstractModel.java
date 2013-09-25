package modelObjects;

public abstract class AbstractModel {
	
	/**
	 * Handles rendering the view
	 */
	public abstract void renderView();
	
	/**
	 * Handles the logical processing in one frame
	 */
	public abstract void logicCycle();
}
