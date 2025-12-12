### App
- Pearcleaner
https://github.com/alienator88/Pearcleaner

- OpenScreen 录屏神器
https://github.com/siddharthvaddem/openscreen

- Motrix 下载器
https://github.com/agalwood/Motrix

################################################################################################

sips -z 512 512 icon.png -o icons.iconset/icon_512x512.png
iconutil -c icns icons.iconset -o icon.icns                 // 生成 icns 图标
sudo codesign --force --deep --sign "Apple Development: bing feng (V35S97B862)" xxx.dmg

### flutter 路径
/Users/zhou/Library/Android/flutter/bin/cache/dart-sdk

### 打开所有来源
sudo spctl --master-disable

### 列出安装目录中的 JDKs
ls /Library/Java/JavaVirtualMachines

################################################################################################

### 打开微信
/Applications/WeChat.app/Contents/MacOS/WeChat
nohup /Applications/WeChat2.app/Contents/MacOS/WeChat >/dev/null 2>&1 &

Mac 版微信双开
所有步骤之前，先退掉你已经登录的所有微信，并结束掉程序（快捷键cmd+q）。不会做的就直接在程序坞上找到已经登录的微信，右键--退出。

第一步，之前有做过双开，或者复制过 wechat2.app 的（没有做过的忽略此步，直接到第二步）
打开访达 -- 应用程序 -- wechat2.app，选中，右键，移到废纸篓（有的电脑会让你验证指纹或者输入开机密码）

第二步，打开终端，复制下边的命令，粘贴进去，点回车。
sudo cp -R /Applications/WeChat.app /Applications/WeChat2.app

第三步，直接输入开机密码
注意：这个地方输入是没有任何显示的，确保自己的密码输入正确，输入完成后直接回车。

第四步，复制下边的命令，粘贴进去，点回车（很多人这里报错，就是因为没装Xcode）
sudo /usr/libexec/PlistBuddy -c "Set :CFBundleIdentifier com.tencent.xinWeChat2" /Applications/WeChat2.app/Contents/Info.plist

第五步，复制下边的命令，粘贴进去，点回车
sudo codesign --force --deep --sign - /Applications/WeChat2.app

第六步，手动打开原始的微信

第七步，复制下边的命令，打开第二个微信
nohup /Applications/WeChat2.app/Contents/MacOS/WeChat >/dev/null 2>&1 &

第八步，固定第二个微信，也就是 wechat2 这个程序到程序坞
在程序坞上，找到第二个微信，右键--选项--在程序坞中保留。
这样以后就可以不用命令来打开第二个微信，直接从程序坞点一下就把第二个微信打开了。

至此，我们的微信双开就完成了，有需要三开的，重复第二到第八步，将步骤中所有的 wechat2.app 都改成 wechat3.app，就可以实现3开了。
