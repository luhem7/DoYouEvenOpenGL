package modelObjects;

import viewObjects.InterpolatedTriangleDisplay;

public class InterpolatedTriangleModel extends AbstractModel {
	private InterpolatedTriangleDisplay myDisplay = null;

	public InterpolatedTriangleModel() {
		myDisplay = new InterpolatedTriangleDisplay();
	}

	@Override
	public void renderView(){
		float[] p = {0f, 0f};
		myDisplay.renderCycle(p, 0f);
	}

	@Override
	public void logicCycle() {
		// TODO Auto-generated method stub
		
	}
}
