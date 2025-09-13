float heart(vec2 p) {
    p.y += 0.3;
    float offset = 0.3;
    // (x^2+(1.2*y-sqrt(abs(x)))^2−1)
    float k = 1.2 * p.y - sqrt(abs(p.x)*1.2 + offset);
    return 1.0 - p.x * p.x - k * k;
}

void mainImage(out vec4 fragColor, in vec2 fragCoord)
{
  vec2 uv = (2.0 * fragCoord - iResolution.xy) / min(iResolution.y,iResolution.x);

  uv.y += 0.3;   
  float d = heart(uv);
  float col = smoothstep(-0.01, 0.01, d);
  vec3 color = vec3(col) * vec3(1.0, 0.0, 0.0);

  fragColor = vec4(color,1.0);
}