### 查看版本
rustc -V
rustc --version
cargo --version

### 更新 rust
rustup update

### 构建项目
cargo build
cargo build --release
cargo check // 检查是否可以编译

### 编译并运行
cargo run
cargo test

### 打开文档
cargo doc --open 

################################################################################################

# 编译优化 Cargo.toml
[profile.dev]
opt-level = 1  # 轻度优化, 编译更快
debug = true   # 保留调试信息

[profile.release]
opt-level = 3  # 最高优化级别
lto = true     # 链接时优化, 进一步减小二进制体积


################################################################################################

windows下运行rust报错error: linker 'link.exe' not found
报错的原因大概就是说缺少 MSVC，解决办法是安装 rust 工具链。
1. rustup toolchain install stable-x86_64-pc-windows-gnu
2. rustup default stable-x86_64-pc-windows-gnu