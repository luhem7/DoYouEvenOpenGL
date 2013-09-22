package modelObjects;

import viewObjects.HelloTriangleDisplay;
import viewObjects.InterpolatedTriangleDisplay;

public class InterpolatedTriangleModel implements ModelInterface {
	private InterpolatedTriangleDisplay myDisplay = null;

	public InterpolatedTriangleModel() {
		myDisplay = new InterpolatedTriangleDisplay();
	}

	@Override
	public void renderView(){
		myDisplay.renderCycle();
	}
}
