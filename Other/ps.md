### 反编译 apk
java -jar apktool.jar d xxx.apk
java -jar apktool.jar d xxx.apk -o out_dir
### 重新编译 apk
java -jar apktool.jar b out_dir -o xxx.apk
### 重新签名 apk
java -jar apksigner.jar sign -verbose --ks sunmi.keystore --v1-signing-enabled true --v2-signing-enabled true --ks-pass pass:sunmi388 --ks-key-alias Sunmi --out signed.apk xxx.apk
java -jar apksigner.jar sign -verbose --ks payby.keystore --v1-signing-enabled true --v2-signing-enabled true --ks-pass pass:benxerbank --ks-key-alias bankpos --out signed.apk xxx.apk

### 编译 apk 为 jar 文件
sh d2j-dex2jar.sh -f xxx.apk
.\d2j-dex2jar.bat -f xxx.apk

################################################################################################

### APK 签名
v2SigningEnabled true
enableV3Signing = true
enableV4Signing = true

### ADB 命令
adb shell pm list packages

### 包名/Activity类的类名
adb shell am start -n 
### 打开系统设置界面
adb shell am start -n com.android.settings/.Settings
### 回到桌面
adb shell am start -n com.android.launcher3/.Launcher

### 查看 App Links
https://域名/.well-known/assetlinks.json
adb shell dumpsys package domain-preferred-apps
adb shell pm get-app-links xxxx

### 截图并保存到手机
adb shell screencap /sdcard/screenshot.png

### 截图并直接拉取到电脑
adb exec-out screencap -p > screenshot.png

### 录制屏幕并保存到手机
adb shell screenrecord /sdcard/demo.mp4
adb shell screenrecord --time-limit 10 /sdcard/demo_10s.mp4
adb shell screenrecord --size 720x1280 /sdcard/demo_720p.mp4
adb pull /sdcard/my_bug.mp4 .

### 查看屏幕分辨率（宽x高, 单位: 像素）
adb shell wm size
### 查看屏幕密度（DPI）
adb shell wm density
### 将分辨率修改为指定值（例如: 宽度720像素, 高度1280像素）
adb shell wm size 720x1280
### 将屏幕密度修改为指定值（例如: 320 DPI）
adb shell wm density 320
### 将分辨率恢复为设备物理默认值
adb shell wm size reset
### 将屏幕密度恢复为设备物理默认值
adb shell wm density reset

### 查看所有可用的系统服务
adb shell dumpsys -l
### 查看特定系统服务的信息
adb shell dumpsys <service_name>
### 查看所有进程的内存信息（信息量大）
adb shell dumpsys meminfo
### 查看指定包名的应用的内存详情（最常用）
adb shell dumpsys meminfo <PACKAGE_NAME>
### 查看当前聚焦的 Activity（最常用）输出信息极多, 必须过滤
adb shell dumpsys activity activities |grep -E "mCurrentFocus|mFocusedActivity"
### 查看所有 Activity 的回退栈（Task）信息
adb shell dumpsys activity activities |grep -A 10 -B 2"TaskRecord"
### 重置电池统计信息
adb shell dumpsys batterystats --reset
### ...执行你的测试场景（例如，玩10分钟游戏）... 导出耗电统计信息
adb shell dumpsys batterystats > batterystats.txt
### 生成Bug报告, 包含详细的电源信息（操作较慢）
adb bugreport
### 或者使用新版本命令生成 HTML 格式的电池分析报告
adb shell dumpsys batterystats --checkin
### 查看指定应用的详细信息, 包含权限、组件等
adb shell dumpsys package <PACKAGE_NAME>

################################################################################################

### Protobuf 命令
protoc --dart_out=. *.proto --plugin ~/.pub-cache/bin/protoc-gen-dart

################################################################################################