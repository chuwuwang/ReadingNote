### 本地所有修改的, 没有的提交的, 都返回到原来的状态
git checkout . 

### 把所有没有提交的修改暂存到stash里面, 可用git stash pop 恢复
git stash

### 返回到某个节点, 不保留修改
git reset --hard HASH

### 返回到某个节点, 保留修改
git reset --soft HASH

### 提交本地test分支作为远程的master分支
git push origin test:master

### 提交本地test分支作为远程的test分支
$ git push origin test:test