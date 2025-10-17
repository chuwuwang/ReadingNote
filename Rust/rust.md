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