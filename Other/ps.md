### 反编译 apk
java -jar apktool.jar d xxx.apk -o out_dir
### 重新编译 apk
java -jar apktool.jar b out_dir -o xxx.apk
### 重新签名 apk
java -jar apksigner.jar sign -verbose --ks sunmi.keystore --v1-signing-enabled true --v2-signing-enabled true --ks-pass pass:sunmi388 --ks-key-alias Sunmi --out signed.apk xxx.apk
java -jar apksigner.jar sign -verbose --ks payby.keystore --v1-signing-enabled true --v2-signing-enabled true --ks-pass pass:benxerbank --ks-key-alias bankpos --out signed.apk xxx.apk