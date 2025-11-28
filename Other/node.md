### Nginx 命令
nginx -s quit

### 查看 node js 版本
node -v
node --version

### Npm 命令
npm install
npm run dev

################################################################################################

### Docker http://localhost
docker pull swaggerapi/swagger-editor
docker run -d -p 80:8080 swaggerapi/swagger-editor

################################################################################################

### scrcpy
brew install scrcpy

### 如果在屏幕镜像的时候希望手机本身的屏幕是关闭的
scrcpy --turn-screen-off

### --new-display：为手机创建一个新的虚拟显示窗口。
### --start-app：指定要在窗口中运行的应用包名。
### --window-title：为窗口指定一个标题，方便识别。
### --keyboard=uhid：启用物理键盘输入，避免在窗口中输入时被遮挡。
### --display-ime-policy=local：将输入法设置为本地输入法，避免在窗口中输入时被遮挡。
scrcpy --new-display --start-app=com.tencent.weread --window-title="微信读书" 
scrcpy --new-display --keyboard=uhid  --display-ime-policy=local --start-app=com.tencent.mm --window-title="微信" 
scrcpy --new-display=1080x2000 --start-app=com.dragon.read --window-title="短剧"

################################################################################################

### Protobuf
protoc --dart_out=. *.proto --plugin ~/.pub-cache/bin/protoc-gen-dart

################################################################################################

### VERT - 多功能文件格式转换工具
brew install oven-sh/bun/bun
bun --version

### 克隆项目
git clone https://github.com/VERT-sh/VERT.git
cd VERT
### 安装依赖
bun i
### 启动开发服务器
bun dev