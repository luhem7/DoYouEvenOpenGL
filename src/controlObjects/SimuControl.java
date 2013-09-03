package controlObjects;

import java.util.LinkedList;
import java.util.Random;

import modelObjects.HelloTriangleModel;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import viewObjects.Camera;
import viewObjects.HelloTriangleDisplay;

/**
 * Takes care of initializing the world and the display
 * @author mehul
 *
 */
public class SimuControl {
	//Setup variables
	private final String WINDOW_TITLE = "Hello Triangle!";
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	private Random rand = new Random();
	
	//Test Objects
	private LinkedList<HelloTriangleModel> polyModelList = new LinkedList<HelloTriangleModel>();
	private float initMouseY = 0;
	
	public SimuControl(){
		//Initialize OpenGL (Display)
		this.setupOpenGL();
		
		//Setting up world items
		addPoly(0, 0);
		
		while(!Display.isCloseRequested()){
			this.loopCycle();
			
			Display.sync(60);
			Display.update();
		}
		
		this.tearDownOpenGL();
	}
	
	public void setupOpenGL(){
		//Setup OpenGL context with API version 3.2
		try{
			PixelFormat pixelformat = new PixelFormat();
			ContextAttribs contextAttribs = new ContextAttribs(3, 2)
			.withForwardCompatible(true)
			.withProfileCore(true);
			
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(WINDOW_TITLE);
			Display.create(pixelformat, contextAttribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		//Setup background color
		GL11.glClearColor(0f, 0f, 0f, 0);
		
		//Map the interal openGL coordinate system
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	public void loopCycle(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		//**Rendering the objects in the scene
		for(HelloTriangleModel p : polyModelList){
			p.renderView();
		}
		
		//**Processing inputs in queue
	}
	
	/**
	 * Adds a polygon at the specific screen position. If it is not possible to add the polygon at this position, it will return null
	 */
	private HelloTriangleModel addPoly(float screenx, float screeny){
		
		HelloTriangleModel newModel = new HelloTriangleModel();
	    
		polyModelList.add(newModel);
		
		return newModel;
	}
	
	public void tearDownOpenGL(){
		Display.destroy();
	}
}
