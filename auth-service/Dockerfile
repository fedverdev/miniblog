# Базовый образ с Java 21 (Amazon Corretto)
FROM amazoncorretto:21-alpine

# Рабочая директория внутри контейнера
WORKDIR /app

RUN apk add --no-cache maven

RUN apk add --no-cache curl unzip gcompat

# Копируем только pom.xml сначала (для кэширования зависимостей)
COPY pom.xml .

# Скачиваем зависимости (используется кэш Docker при неизменном pom.xml)
RUN mvn dependency:go-offline

# Копируем исходный код
COPY src ./src

# Собираем приложение
RUN mvn package -DskipTests

# Точка входа для запуска приложения
ENTRYPOINT ["java", "-jar", "target/auth-service.jar"]