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
	private DisplayInterface myDisplay = null;
	private float scale;
	private float[] position = new float[2];

	public MultipleTrianglesModel() {
		myDisplay = MultipleTrianglesDisplay.getInstance();
		
		int numberOfTriangles = 50;
		scale = 1f/(float)numberOfTriangles;
		position[0] = (rand.nextInt(2*numberOfTriangles) - numberOfTriangles )  * scale;
		position[1] = (rand.nextInt(2*numberOfTriangles)- numberOfTriangles) * scale;
	}

	@Override
	public void renderView(){
		myDisplay.renderCycle(position, scale);
	}
}
