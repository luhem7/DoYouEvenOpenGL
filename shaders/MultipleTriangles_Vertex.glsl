#version 330

layout (location = 0) in vec4 position;
layout (location = 1) in vec4 color;

uniform vec4 offset;
uniform float scale;

smooth out vec4 theColor;

void main() {
	gl_Position = position * scale + offset;
	theColor = color;
}