# # Build a JAR File
# FROM maven:3.8.2-jdk-8-slim AS stage1
# # FROM maven:3.8.2-jdk-11-slim AS stage1
# WORKDIR /home/app
# COPY . /home/app/
# RUN mvn -f /home/app/pom.xml clean package
#
# # Create an Image
# FROM openjdk:8-jdk-alpine
#
# # For Java 11, try this
# #FROM adoptopenjdk/openjdk11:alpine-jre
#
# EXPOSE 8080
# COPY --from=stage1 /home/app/target/courseapi-backend-1.0.jar app.jar
# ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]

# Use an official Maven image to build the Spring Boot app
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use an official OpenJDK image to run the application
FROM eclipse-temurin:21-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/courseapi-backend-1.0.jar app.jar

# Expose port 8080
EXPOSE 8080

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]