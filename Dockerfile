# Build mərhələsi
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /build
COPY . .

# gradlew faylını icra etmək üçün icazə veririk
RUN chmod +x ./gradlew

# Gradle build əmri
RUN ./gradlew clean build --no-daemon

# Run mərhələsi
FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=builder /build/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
