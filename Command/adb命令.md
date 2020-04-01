##adb命令相关

###启动系统组件相关
```
打开某个应用
adbshellamstart应用包名

发送广播
adbshellambroadcast-acom.android.test--estest_string"thisisteststring"--eitest_int100--eztest_booleantrue
说明：test_string为key。thisisteststring为value。分别为String类型。int类型。boolean类型

启动service
adbshellamstartservice-npackage名/service)名
说明：adbshellamstartservice-nwoyou.system.api/woyou.system.service.SystemAPIService
```

###测试相关
```
monkey测试
adbshellmonkey-p应用包名-s500-v-v-v300000>E:\monkey_log.txt

查看log
adblogcat-s过滤条件
adblogcat|grep"geek">log.txt
```

###查看应用信息相关
```
列出系统内应用的包名
adbshellpmlistpackages

查看系统中某个应用的信息
adbshelldumpsysmeminfo应用包名-d

清除安装包数据与缓存
adbshellpmclear<PACKAGE>

输出安装包的APK路径
adbshellpmpath<PACKAGE>

关闭应用程序
adbshellamforce-stop<PACKAGE>
```

###查看系统信息相关
```
查看屏幕大小
adbshellwmsize

查看屏幕密度
adbshellwmdensity

查看adb版本
adbversion在adbshell下进入设备，通过getprop查看所有的系统属性。
版本号：ro.build.version.release
设备芯片代号：ro.hardware
来电铃声文件：ro.config.ringtone
可以通过使用setprop命令来设置系统属性值setproppersist.sys.root.accessz

查看手机cpu类型是x86还是arm
adbshellcat/proc/cpuinfo
```