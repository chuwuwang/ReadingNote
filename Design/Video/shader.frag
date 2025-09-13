#define PI 3.1415926535897932384626433832795

float polygonSDF(vec2 uv, float radius, float sides){
  //  原点设置到中心位置
   uv = uv * 2.0 - 1.0;
   // 相对于原点的 atan，范围[-PI/2,PI/2]
   float angle = atan(uv.x, uv.y) + PI / 2.0;
   // 多边形每一条边占用的弧度
   float slice = PI * 2.0 / sides;
   // floor 向下取整来构造多边形的边
   // smoothstep 作为边缘平滑过渡
   return smoothstep(radius-0.005, radius + 0.005, cos(floor(0.5 + angle / slice) * slice - angle) * length(uv));
}

void mainImage(out vec4 fragColor, in vec2 fragCoord)
{
    vec2 uv = fragCoord / iResolution.x;
    float n = ceil(mod(iTime, 8.0)) + 2.0;

    vec3 color = vec3(0.0);

    float val = polygonSDF(uv,0.3,n);
    color = vec3(val);
    fragColor = vec4(color,1.0);
}