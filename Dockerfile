# Build a JAR File
FROM maven:3.8.2-jdk-8-slim AS stage1
# FROM maven:3.8.2-jdk-11-slim AS stage1
WORKDIR /home/app
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean package

# Create an Image
FROM openjdk:8-jdk-alpine

# For Java 11, try this
#FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080
COPY --from=stage1 /home/app/target/courseapi-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
