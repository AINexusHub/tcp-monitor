# TCP Monitor 系统

基于Spring Boot和Vue.js的TCP连接监控系统，用于分析和展示网络监控数据。

## 功能特性

- ✅ TCP连接统计信息展示
- ✅ 时间范围搜索功能
- ✅ 连接时长过滤（过滤小于5秒的连接）
- ✅ 本地IP+端口组合统计
- ✅ 前后端分离架构
- ✅ Kubernetes部署支持

## 技术栈

### 后端
- Java 17
- Spring Boot 3.5.6
- Spring Data JPA
- PostgreSQL
- Maven

### 前端
- Vue.js 3
- Element Plus
- Vite
- Axios

## 项目结构

```
tcp-monitor/
├── java/tcp-monitor/          # Spring Boot后端应用
├── vue-frontend/             # Vue.js前端应用
```

## 快速开始

### 1. 环境要求

- JDK 17+
- Node.js 18+
- Maven 3.6+

### 2. 本地开发

#### 后端启动

```bash
cd java/tcp-monitor
mvn spring-boot:run
```

后端服务将在 http://localhost:8080 启动

#### 前端启动

```bash
cd vue-frontend
npm install
npm run dev
```

前端服务将在 http://localhost:3000 启动

## API接口

[tcp-monitor-api.yaml](./api-specs/tcp-monitor-api.yaml)

## 数据库设计

### tcp_connections 表结构

| 字段名 | 类型 | 描述 |
|--------|------|------|
| id | serial4 | 主键，自增ID |
| local_ip | varchar(45) | 本地IP地址 |
| local_port | int4 | 本地端口 |
| remote_ip | varchar(45) | 远程IP地址 |
| remote_port | int4 | 远程端口 |
| created_at | timestamp | 连接建立时间 |
| disconnected_at | timestamp | 连接断开时间 |
| last_active_at | timestamp | 最后活跃时间 |

## 开发说明

### 后端架构

1. **Controller层**: 处理HTTP请求和响应
2. **Service层**: 业务逻辑处理
3. **Repository层**: 数据库访问
4. **Model层**: 数据模型定义

### 前端架构

1. **Vue 3**: 使用Composition API
2. **Element Plus**: UI组件库
3. **Axios**: HTTP客户端
4. **Vite**: 构建工具
