package modelObjects;

import viewObjects.HelloTriangleDisplay;

public class HelloTriangleModel extends AbstractModel {
	private HelloTriangleDisplay myDisplay = null;

	public HelloTriangleModel() {
		myDisplay = new HelloTriangleDisplay();
	}

	@Override
	public void renderView(){
		float[] p = {0f, 0f};
		myDisplay.renderCycle(p, 0f);
	}
}
