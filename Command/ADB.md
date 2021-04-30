## adb命令相关

### 启动系统组件相关
```
打开某个应用
adb shell am start 应用包名

发送广播
adb shell am broadcast -a com.android.test --es test_string "this is test string" --ei test_int 100 --ez test_boolean true
说明：test_string为key。this is test string为value。分别为String类型。int类型。boolean类型

启动服务
adb shell am startservice -n package名/service名
说明：adb shell am startservice -n woyou.system.api/woyou.system.service.SystemAPIService
```

***

### 测试相关
```
monkey测试
adb shell monkey -p 应用包名 -s 500 -v-v-v 300000>E:\monkey_log.txt

查看log
adb logcat -s 过滤条件
adb logcat | grep"geek">log.txt

如何获取启动时间
adb shell am start -S -W 包名/启动类的全名
Stopping: xxx
Starting: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=xxx/xxx }
Status: ok
Activity: xxx/xxx
ThisTime: 770
TotalTime: 770
WaitTime: 848
Complete
ThisTime: 表示最后一个 Activity 启动时间
TotalTime: 表示启动过程中, 所有的 Activity 的启动时间
WaitTime: 表示应用进程的创建时间 +TotalTime
一般我们关注TotalTime就好了. 另外, 谷歌在 Android4.4（API 19）上也提供了测量方法, 在 logcat 中过滤 Displayed 字段也可以看到启动时间
> 2021-04-06 19:25:52.803 2210-2245 I/ActivityManager: Displayed xxx/xxx: +623ms
> +623ms 就是 Activity 的启动时间
```

***

### 查看应用信息相关
```
列出系统内应用的包名
adb shell pm list packages

查看系统中某个应用的信息
adb shell dumpsys meminfo 应用包名-d

清除安装包数据与缓存
adb shell pm clear<PACKAGE>

输出安装包的APK路径
adb shell pm path<PACKAGE>

关闭应用程序
adb shell am force-stop<PACKAGE>
```

***

### 查看系统信息相关
```
查看屏幕大小
adb shell wm size

查看屏幕密度
adb shell wm density

查看adb版本
adb version

在adb shell下进入设备，通过getprop查看所有的系统属性。
版本号：ro.build.version.release
设备芯片代号：ro.hardware
来电铃声文件：ro.config.ringtone

getprop 列出所有配置属性值
getprop [key] 取得对应的key的属性值

可以通过使用setprop命令来设置系统属性值
setprop [key] [value] 设置指定key的属性值

watchprops 监听系统属性的变化，如果期间系统的属性发生变化则把变化的值显示出来

查看手机cpu类型是x86还是arm
adb shell cat/proc/cpuinfo
```

***