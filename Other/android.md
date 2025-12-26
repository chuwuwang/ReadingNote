### 投屏助手
- Scrcpy
- QtScrcpy
- LinkAndroid

################################################################################################
### Termux https://github.com/termux/termux-app
pkg update
pkg install openssh

### 设置 Termux 的密码
passwd

### 开启 SSH 服务
sshd

### Termux root 登录命令
ssh -p 8022 root@192.168.x.x

### 免密码登录 在 macOS 生成 key
ssh-keygen
### 将公钥复制到 Termux
ssh-copy-id -p 8022 root@192.168.x.x

### 查看用户名 
用户名

################################################################################################
### issues
- Binder 开辟线程数过多导致主线程 ANR 异常