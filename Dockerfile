# Use Maven to build the application
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK image to run the application
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
