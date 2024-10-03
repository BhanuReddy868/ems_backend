# Use an official Maven image to build the application
FROM maven:3.8.5-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use an OpenJDK runtime image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/ems-backend-docker.jar ems-backend-docker.jar

# Expose the application port (change 8080 if your app uses a different port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "ems-backend-docker.jar"]
