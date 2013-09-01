package viewObjects;

/**
 * This class represents the perspective of the user on the screen. The Camera
 * always looks down perpendicularly at the XY plane. It assumes orthographic
 * projection. 
 * @author mehul
 *
 */
public class Camera {

	private static final float PI = (float) Math.PI;

	//***Camera attribute state variables
	private float zoom = 0.02f; // The current zoom amount looking down (represents the scale at which objects are rendered)
	private static final float MAX_ZOOM = 1f;
	private static final float MIN_ZOOM = 0.01f;
	private static final float DELTA_ZOOM = 0.001f; //The rate at which the camera zooms
	
	private float[] position = new float[2]; //The position of the camera 
	private static final float MAX_DISTANCE = 2f; //The maximum distance the camera can move in either axis
	private static final float DELTA_POS = 0.01f; // The rate at which the camera can move;
	
	private float viewAngle = 0; //The starting angle of camera
	private static final float DELTA_ANGLE = PI/20; // The rate at which the angle changes every iteration
	
	//***Camera action state variables
	//Moving state variables
	private static final int MOVE_LEFT = -1;
	private static final int MOVE_RIGHT = 1;
	private static final int MOVE_UP = -1;
	private static final int MOVE_DOWN = 1;
	private static final int MOVE_NONE = 0;
	private int[] moveState = {MOVE_NONE, MOVE_NONE};
	//Zoom state variables
	private static final int ZOOM_IN = 1;
	private static final int ZOOM_OUT = -1;
	private static final int ZOOM_NONE = 0;
	private int zoomState = ZOOM_NONE;
	
	
	/**
	 * @return viewAngle
	 */
	public float getViewAngle() {
		return viewAngle;
	}
	
	/**
	 * Rotate the camera clockwise
	 */
	public void rotateCW(){
		viewAngle = viewAngle - DELTA_ANGLE;
	}
	
	/**
	 * Rotate the camera counter clockwise
	 */
	public void rotateCCW(){
		viewAngle = viewAngle + DELTA_ANGLE;
	}
	
	public float getZoom() {
		return zoom;
	}
	
	public void setZoom(float zoom) {
		if(zoom < MIN_ZOOM || zoom > MAX_ZOOM)
			return;
		this.zoom = zoom;
	}
	
	public void zoomIn(){
		zoomState = ZOOM_IN;
	}
	
	public void zoomOut(){
		zoomState = ZOOM_OUT;
	}
	
	public void stopZoom(){
		zoomState = ZOOM_NONE;
	}
	
	public float[] getPosition() {
		return position;
	}
	
	public void setPosition(float[] newPos){
		if(newPos[0] > -MAX_DISTANCE && newPos[0] < MAX_DISTANCE)
			position[0] = newPos[0];
		
		if(newPos[1] > -MAX_DISTANCE && newPos[1] < MAX_DISTANCE)
			position[1] = newPos[1];
	}
	
	public void moveLeft(){
		moveState[0] = MOVE_LEFT;
	}
	
	public void moveRight(){
		moveState[0] = MOVE_RIGHT;
	}
	
	public void stopHorizontalMove(){
		moveState[0] = MOVE_NONE;
	}
	
	public void moveUp(){
		moveState[1] = MOVE_UP;
	}
	
	public void moveDown(){
		moveState[1] = MOVE_DOWN;
	}

	public void stopVerticalMove(){
		moveState[1] = MOVE_NONE;
	}
	
	/**
	 * Processes the actions that the camera takes in one cycle
	 */
	public void loopCycle(){
		//process zoom action
		if(zoomState == ZOOM_IN){
			float resultZoom = zoom + DELTA_ZOOM;
			if(resultZoom < MAX_ZOOM)
				zoom = resultZoom;
		} else if (zoomState == ZOOM_OUT) {
			float resultZoom = zoom - DELTA_ZOOM;
			if(resultZoom > MIN_ZOOM)
				zoom = resultZoom;
		}
		
		//process horizontal move action
		if(moveState[0] == MOVE_LEFT) {
			
			float newXPos = position[0]-DELTA_POS;
			if(newXPos > -MAX_DISTANCE && newXPos < MAX_DISTANCE)
				position[0] = newXPos;
			
		} else if (moveState[0] == MOVE_RIGHT) {
			
			float newXPos = position[0]+DELTA_POS;
			if(newXPos > -MAX_DISTANCE && newXPos < MAX_DISTANCE)
				position[0] = newXPos;
		}
		
		//process vertical move action
		if(moveState[1] == MOVE_UP) {
			
			float newYPos = position[1]+DELTA_POS;
			if(newYPos > -MAX_DISTANCE && newYPos < MAX_DISTANCE)
				position[1] = newYPos;
			
		} else if (moveState[1] == MOVE_DOWN) {
			
			float newYPos = position[1]-DELTA_POS;
			if(newYPos > -MAX_DISTANCE && newYPos < MAX_DISTANCE)
				position[1] = newYPos;
		}
	}
}
