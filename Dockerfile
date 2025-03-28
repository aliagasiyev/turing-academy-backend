# Java runtime image istifadə olunur
FROM eclipse-temurin:21-jre

# App üçün qovluq
WORKDIR /app

# Lokal JAR faylını konteynerə kopyala
COPY build/libs/turing-academy-backend-0.0.1-SNAPSHOT.jar app.jar

# App-i işə sal
ENTRYPOINT ["java", "-jar", "app.jar"]
