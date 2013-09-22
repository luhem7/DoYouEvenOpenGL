package modelObjects;

import viewObjects.HelloTriangleDisplay;

public class HelloTriangleModel implements ModelInterface {
	private HelloTriangleDisplay myDisplay = null;

	public HelloTriangleModel() {
		myDisplay = new HelloTriangleDisplay();
	}

	@Override
	public void renderView(){
		myDisplay.renderCycle();
	}
}
