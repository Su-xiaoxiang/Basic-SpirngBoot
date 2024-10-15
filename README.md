# Spring Boot MySQL Lombok Redis MyBatis JWT Starter

基于 `Spring Boot` 的 `MySQL` + `Lombok` + `Redis` + `MyBatis` + `JWT` 脚手架模板。

## 🚀 特性

- ⚡️ **Spring Boot**: 现代化的后端开发框架
- 🗄️ **MySQL**: 高性能关系数据库
- 🧬 **Lombok**: 减少样板代码，提高开发效率
- 🐳 **Redis**: 高速缓存解决方案
- 🥷 **MyBatis**: 简化数据库操作
- 🔐 **JWT**: 安全的用户认证机制

## 📦 生态系统

在这里，你可以找到许多现成的模板，包含 `Spring Boot`、`Vite`、`Tailwind CSS` 等技术栈。推荐访问我的 GitHub 主页以获取更多资源。

## 📖 快速开始

### 1. 克隆项目

```bash
git clone git@github.com:Su-xiaoxiang/Basic-springboot.git
cd Basic-springboot
```
### 2. 配置数据源

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/Your Database Name
spring.datasource.username=Your Username
spring.datasource.password=Your Password.
spring.data.redis.host=localhost // Redis主机地址
spring.data.redis.port=6379 // Redis端口号
```

### 3. 启动项目

```bash
mvn spring-boot:run
```
### 4. 访问应用
```bash
   应用将在 http://localhost:8080 上运行
```
## 📄 许可证

本项目采用 [MIT 许可证](https://github.com/Su-xiaoxiang) 开源，具体内容如下：
```bash
MIT License

Copyright (c) [2024年] [Coder-Su]

在此许可下，任何人均可获得该软件的副本和相关文档文件（“软件”），并可以自由使用、复制、修改、合并、发布、分发、再许可和/或出售软件的副本，且在遵循以下条件的情况下：

上述版权声明和本许可声明应包含在软件的所有副本或重要部分中。

该软件是“按原样”提供的，不附带任何明示或暗示的担保，包括但不限于适销性、特定用途适用性和非侵权的担保。无论在任何情况下，作者或版权持有者均不应对因软件或软件的使用或其他交易而导致的任何索赔、损害或其他责任承担责任。
```
