FROM amazoncorretto:21-alpine as builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы Gradle Wrapper
COPY gradlew .
COPY gradle/wrapper ./gradle/wrapper

# Копируем остальные файлы проекта
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY src ./src

# Устанавливаем зависимости и даем права на gradlew
RUN apk add --no-cache curl unzip gcompat && \
    chmod +x gradlew

# Собираем проект
RUN ./gradlew clean build


FROM gcr.io/distroless/java21-debian12

COPY --from=builder /app/build/libs/profile-service.jar .

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "profile-service.jar"]