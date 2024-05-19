### 同步库配置
flutter pub get
drat pub get

### 清除缓存
flutter pub cache clean

### 运行在浏览器
flutter run -d chrome
flutter run -d chrome --web-renderer html --profile

flutter build web 
flutter build web --web-renderer html
flutter build web --web-renderer canvaskit
flutter build web --release
flutter build web --web-renderer canvaskit --release
flutter build web --web-renderer canvaskit --release --no-tree-shake-icons
--web-renderer 可选参数值为 auto、html 或 canvaskit。
auto（默认）- 自动选择渲染器。移动端浏览器选择 HTML，桌面端浏览器选择 CanvasKit。
html - 强制使用 HTML 渲染器。
canvaskit - 强制使用 CanvasKit 渲染器。


################################################################################################

### Nginx 命令
nginx -s quit

### Npm 命令
npm install
npm run dev

### Docker 命令 http://localhost
docker pull swaggerapi/swagger-editor
docker run -d -p 80:8080 swaggerapi/swagger-editor