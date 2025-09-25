### 查看当前 Flutter 版本
dart --version
flutter --version

### 更新 Flutter 到最新版本
flutter upgrade

### 检查环境是否配置正确
flutter doctor

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

### 编译APK
flutter build apk --debug
flutter build apk --release
生成的APK会在 build/app/outputs/flutter-apk/app-release.apk

### 编译 profile 版本 APK（用于性能分析）
flutter build apk --profile

### 编译分离的多架构 APK（每个架构一个 apk）
flutter build apk --split-per-abi

###  编译指定架构（可减小包体积）
flutter build apk --release --target-platform android-arm
支持的架构参数：
android-arm（32位）
android-arm64（64位）
android-x64（模拟器）

################################################################################################

### Nginx 命令
nginx -s quit

### 查看 node js 版本
node -v
node --version

### Npm 命令
npm install
npm run dev

### Docker 命令 http://localhost
docker pull swaggerapi/swagger-editor
docker run -d -p 80:8080 swaggerapi/swagger-editor

################################################################################################