package viewObjects;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import utilObjects.DisplayUtils;

public class InterpolatedTriangleDisplay {
	
	float vertexPositions[] = {
			0.75f, 0.75f, 0.0f, 1.0f,
			0.75f, -0.75f, 0.0f, 1.0f,
			-0.75f, -0.75f, 0.0f, 1.0f,
			1f, 1f, 1f, 1f,
			0.5f, 0.5f, 0f, 1f,
			0.8f, 0.3f, 1f, 1f
		};
	
	String vertexShaderFile = "shaders/InterpolatedTriangle_Vertex.glsl";
	String fragmentShaderFile = "shaders/InterpolatedTriangle_Fragment.glsl";
	
	//Shader variables
	private int vsID = 0; //Vertex Shader ID
	private int fsID = 0; //Fragment Shader ID
	private int pID = 0; //Program Shader ID
	
	//The pointer to the buffer that contains the vertex position data
	private int vertexBufferObject = 0;
	//The pointer to the Vertex Array Object
	private int vao = 0;

	public InterpolatedTriangleDisplay() {
		setupShaders();
		setupBuffers();
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
	 * Creates VBO with vertex data and puts it into a vbo. also sets up vao.
	 */
	private void setupBuffers(){
		int numVertices = 3;
		int elementCount = 8; //8 values for each vertex
		
		FloatBuffer verticesBuffer;
		verticesBuffer = BufferUtils.createFloatBuffer(
				(numVertices) * elementCount);
		
		for(float f: vertexPositions){
			verticesBuffer.put(f);
		}
		verticesBuffer.flip(); //For some reason we have to flip the buffer?
		
		vertexBufferObject = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferObject);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
		
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		DisplayUtils.exitOnGLError("Generating polygon buffers");
	}
	
	public synchronized void renderCycle(){
		int numberOfPositionAttribsPerPositionPerVertex = 4;
		int numberOfColorAttribsPerPositionPerVertex = 4;
		int bytesBetweenConsecPositions = 0;
		int bytesBetweenConsecColors = 0;
		int positionStartingIndex = 0;
		int colorStartingIndex = 12*4; //Number of position attributes total * size in bytes of a postion attribute (4 bytes for a float)
		
		GL20.glUseProgram(pID);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferObject);
		
		//Bind to the VAO that has all the information about the vertices
		//Putting information about position data (vertex position metadata) into vertex attribute list 0
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glVertexAttribPointer(0, numberOfPositionAttribsPerPositionPerVertex, GL11.GL_FLOAT, false,
				bytesBetweenConsecPositions, positionStartingIndex);
		GL20.glVertexAttribPointer(1, numberOfColorAttribsPerPositionPerVertex, GL11.GL_FLOAT, false, 
				bytesBetweenConsecColors, colorStartingIndex);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL20.glUseProgram(0);
		
		DisplayUtils.exitOnGLError("Rendering Polygon");
	}
}
