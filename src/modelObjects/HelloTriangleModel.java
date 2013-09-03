package modelObjects;

import viewObjects.HelloTriangleDisplay;

public class HelloTriangleModel {
	private HelloTriangleDisplay myDisplay = null;

	public HelloTriangleModel() {
		myDisplay = new HelloTriangleDisplay();
	}

	public void renderView(){
		myDisplay.renderCycle();
	}
}
