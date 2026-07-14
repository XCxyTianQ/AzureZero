# AzureZero 🐋

高性能 Minecraft 服务端 —— 基于 Paper 的 Fork。

## 关于

AzureZero 是一个 Paper 服务端的 Fork，在 Paper 的性能优化和 Bug 修复基础上进行定制开发。

**上游项目:** [PaperMC/Paper](https://github.com/PaperMC/Paper)

## 构建

需要 **JDK 25** 和网络连接。

```bash
# 1. 应用补丁（生成可编辑的源码）
./gradlew applyPatches

# 2. 构建 Jar
./gradlew createPaperclipJar

# 输出: paper-server/build/libs/
```

获取完整任务列表: `./gradlew tasks`

## 开发流程

1. `./gradlew applyPatches` — 生成可编辑源码
2. 在 `paper-server/src/main/java/` 下修改代码
3. `./gradlew rebuildPatches` — 将修改打包回 patch 文件
4. `./gradlew createPaperclipJar` — 构建最终 Jar

> ⚠️ 只提交 `patches/` 目录，不提交生成的 `paper-server/src/`。

## 许可

基于 Paper 项目，遵循相同许可条款。
