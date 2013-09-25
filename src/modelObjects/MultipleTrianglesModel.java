package modelObjects;

import java.util.Random;

import viewObjects.DisplayInterface;
import viewObjects.MultipleTrianglesDisplay;

/**
 * This is the model for multiple triangles. Each triangle will have a distinct position and scale, 
 * but other wise it should have the same shape 
 * @author mehul
 *
 */
public class MultipleTrianglesModel extends AbstractModel {
	private Random rand = new Random();
	private static final float PI = (float) Math.PI; 
	
	private DisplayInterface myDisplay = null;
	private float scale;
	private float[] position = new float[2];
	
	private float angle = 0f;
	private static float angluarVelocity = 2*PI/10000000f;

	public MultipleTrianglesModel() {
		myDisplay = MultipleTrianglesDisplay.getInstance();
		
		int numberOfTriangles = 500;
		scale = 1f/(float)numberOfTriangles;
		position[0] = (rand.nextInt(3*numberOfTriangles) - 1.5f*numberOfTriangles )  * scale;
		position[1] = (rand.nextInt(3*numberOfTriangles)- 1.5f*numberOfTriangles) * scale;
	}

	@Override
	public void renderView(){
		myDisplay.renderCycle(position, scale);
	}

	@Override
	public void logicCycle() {
		angle = (angle + angluarVelocity) % (2*PI);
		float x = position[0];
		float y = position[1];
		position[0] = (float) (x*Math.cos(angle) - y*Math.sin(angle));
		position[1] = (float) (x*Math.sin(angle) + y*Math.cos(angle));
	}
}
