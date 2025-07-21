# Use Maven to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use a widely compatible JDK image to run the app
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/task1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"] 