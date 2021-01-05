# Dockerfile
FROM openjdk:11-jdk-slim
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} spring-boot-kotlin-demo.jar
ENTRYPOINT ["java","-jar","/app.jar"]