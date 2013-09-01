package viewObjects;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import utilObjects.DisplayUtils;

public class HelloTriangleDisplay {
	
	float vertexPositions[] = {
			0.75f, 0.75f, 0.0f, 1.0f,
			0.75f, -0.75f, 0.0f, 1.0f,
			-0.75f, -0.75f, 0.0f, 1.0f
		};
	
	String vertexShaderFile = "shaders/HelloTriangle_Vertex.glsl";
	String fragmentShaderFile = "shaders/HelloTriangle_Fragment.glsl";
	
	//Shader variables
	private int vsID = 0; //Vertex Shader ID
	private int fsID = 0; //Fragment Shader ID
	private int pID = 0; //Program Shader ID
	
	//The pointer to the buffer that contains the vertex position data
	private int positionBufferObject = 0;
	//The pointer to the Vertex Array Object
	private int vao = 0;

	public HelloTriangleDisplay() {
		
	}
	
	/**
	 * Loads the shader program into memory
	 */
	private void setupShaders(){		
		//***Loading Shaders information into memory
		//Load the vertex shader
		vsID = DisplayUtils.loadShader(vertexShaderFile, GL20.GL_VERTEX_SHADER);
		//Load the fragment shader
		fsID = DisplayUtils.loadShader(fragmentShaderFile, GL20.GL_FRAGMENT_SHADER);
		
		//Create a new shader program that links both shaders
		pID = GL20.glCreateProgram();
		GL20.glAttachShader(pID, vsID);
		GL20.glAttachShader(pID, fsID);
		GL20.glLinkProgram(pID);
		
		//Position information will be attribute 0
		GL20.glBindAttribLocation(pID, 0, "position");
		
		//Validating the program
		GL20.glValidateProgram(pID);
		
		DisplayUtils.exitOnGLError("Setting up Shaders");
	}
	
	/**
	 * Puts vertexPositions[] into a Buffer in memory 
	 */
	private void initializeVertexBuffer(){
		int numVertices = 3;
		int elementCount = 4; //Four values for each vertex
		
		FloatBuffer verticesBuffer;
		verticesBuffer = BufferUtils.createFloatBuffer(
				(numVertices) * elementCount);
		
		for(float f: vertexPositions){
			verticesBuffer.put(f);
		}
		verticesBuffer.flip(); //For some reason we have to flip the buffer?
		
		
	}
}
