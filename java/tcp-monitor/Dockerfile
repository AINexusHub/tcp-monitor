FROM openjdk:17-jdk-slim

WORKDIR /app

# 复制Maven构建的jar文件
COPY target/tcp-monitor-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口
EXPOSE 8080

# 运行应用
ENTRYPOINT ["java", "-jar", "app.jar"]
