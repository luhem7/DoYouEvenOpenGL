#version 330

out vec4 outputColor;

void main(){
	float lerpValue = gl_FragCoord.y / 400.0f;
	outputColor = mix(vec4(1.0f, 1.0f, 1.0f, 1.0f),
		vec4(0.2, 0.2f, 0.2f, 1.0f), lerpValue);
}