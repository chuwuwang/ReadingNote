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

### 禁用移动数据
adb shell svc data disable
### 开启移动数据
adb shell svc data enable
### 禁用 Wi-Fi
adb shell svc wifi disable
### 开启 Wi-Fi
adb shell svc wifi enable
### 设置全局网络策略（限制应用在后台使用网络）
adb shell cmd netpolicy set restrict-background true
### 恢复正常
adb shell cmd netpolicy set restrict-background false
### 设置为 2G 网络模式
adb shell settings put global settings_global_network_preference 1
### 设置为 3G网络模式  
adb shell settings put global settings_global_network_preference 2
### 恢复为正常模式
adb shell settings put global settings_global_network_preference 0
### 为移动数据网络（rmnet0）添加200ms延迟
adb shell tc qdisc add dev rmnet0 root netem delay 200ms
### 为 Wi-Fi 网络（wlan0）添加500ms延迟
adb shell tc qdisc add dev wlan0 root netem delay 500ms
### 模拟 5% 的丢包率
adb shell tc qdisc add dev wlan0 root netem loss 5%
### 模拟 10% 的丢包率，同时有 30% 的相关性（连续丢包）
adb shell tc qdisc add dev wlan0 root netem loss 10% 30%
### 清除所有网络限制
adb shell tc qdisc del dev wlan0 root
adb shell tc qdisc del dev rmnet0 root

反向代理
### 建立反向代理, 在您的手机 App 或浏览器里, 您只需要访问 http://localhost:8080, 就相当于访问了您电脑上运行的 http://localhost:3000 服务
adb reverse tcp:8080 tcp:3000
### 取消特定的映射
adb reverse --remove tcp:8080
### 取消所有映射
adb reverse --remove-all

### 提前在电脑上创建好 log 文件夹, 拉取应用私有日志文件
adb pull /data/data/com.example.demoapp/files/log/ ./log/
### 拉取 SD 卡上的公共日志
adb pull /sdcard/Android/data/com.example.demoapp/files/log/ ./log/
### 将测试 APK 包发送到手机的 Download 目录
adb push app-debug.apk /sdcard/Download/

### 快速抓取当前 Activity
adb shell dumpsys activity | grep "mResumedActivity"
### 查看 Activity 任务栈
adb shell dumpsys activity activities | grep "Run #"
### 查看应用安装路径和版本
adb shell pm path <package_name>
adb shell dumpsys package <package_name>| grep "versionName"
### 模糊搜索你关心的应用
adb shell pm list packages | grep "t"

### 在 top 交互界面中（运行 top 命令后）
按 q: 退出
按 M: 按内存使用率（%MEM）排序
按 P: 按CPU使用率（%CPU）排序, 这是最常用的
按 R: 反转排序顺序
adb shell top
### 快速查看进程的CPU实时占用率
-n 1 代表只刷新一次, 避免刷屏。去掉此参数可进行简单实时监控, 按 Ctrl+C 退出。
adb shell top -n 1 | grep <package_name>

### 向设备发送 500 个随机事件
adb shell monkey -v 500
### 只测试微信
adb shell monkey -p com.tencent.mm 500
### 测试微信和支付宝
adb shell monkey -p com.tencent.mm -p com.eg.android.AlipayGphone 1000
### 发送 1000 个事件，其中 80% 是触摸，20% 是启动应用。
adb shell monkey -p com.example.myapp --pct-touch 80 --pct-appswitch 20 1000
# 第一次运行, 记录下日志开头显示的种子值
adb shell monkey -p com.example.myapp -s 12345500
# 第二次运行, 使用相同的种子, 事件序列会完全一样
adb shell monkey -p com.example.myapp -s 12345500
### 如何停止 Monkey 测试
adb shell ps|grep monkey
adb shell kill <pid>
adb shell killall com.android.commands.monkey

### 普通重启
adb reboot
### 重启到 Recovery 模式
adb reboot recovery
### 重启到 Bootloader 模式
adb reboot bootloader
### 重启到 Download 模式
adb reboot download
### 重启到 Fastboot 模式
adb reboot fastboot
### 重启并进入 Safe Mode
adb shell reboot -p
### 使用 -f 选项（强制重启，跳过正常关闭流程） ⚠️ 危险：可能导致数据损坏
adb reboot -f
### 延迟重启（部分设备支持）30秒后重启
adb shell reboot -d 30

### 使用getprop查看所有属性
adb shell getprop
### 设备所有信息存储到执行指令的目录下
adb shell getprop > device_info.txt
### 设备型号
adb shell getprop ro.product.model
### Android 版本
adb shell getprop ro.build.version.release
### 设备品牌
adb shell getprop ro.product.brand
### 制造商信息
adb shell getprop ro.product.manufacturer
### 查看设备序列号
adb get-serialno
### 查看设备名称
adb shell getprop ro.product.name
### 查看所有 build.prop 属性
adb shell cat /system/build.prop
### 查看 CPU 架构和核心数
adb shell cat /proc/cpuinfo
### 内存使用情况
adb shell cat /proc/meminfo
### 更直观的内存信息
adb shell dumpsys meminfo
### 电池详细信息
adb shell dumpsys battery
### 屏幕分辨率 输出：Physical size: 1080x2340
adb shell wm size
### 屏幕密度（DPI） 输出：Physical density: 440
adb shell wm density
### 实际显示区域（考虑状态栏、导航栏）
adb shell dumpsys window displays
### 查看 IP 地址和网络接口
adb shell ifconfig
adb shell ip addr
### WiFi 详细信息
adb shell dumpsys wifi
### 网络连接状态
adb shell netstat