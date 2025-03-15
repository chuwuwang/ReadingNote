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

################################################################################################

/Users/zhou/Library/Android/flutter/bin/cache/dart-sdk

### Protobuf 命令
protoc --dart_out=. *.proto --plugin ~/.pub-cache/bin/protoc-gen-dart