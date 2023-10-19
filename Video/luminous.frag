float getGlow(float dist, float radius, float intensity){
    return pow(radius/dist, intensity);
}

void main() {
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv -= 0.5;
    uv.x *= iResolution.x/iResolution.y; 
    vec3 color = vec3(0.);
    float glow = 0.05 * getGlow(length(uv), 1., 2.);
    color += glow;
    gl_FragColor = vec4(color,1.);
}