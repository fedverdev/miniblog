# ===== Build Stage ===== (используем JDK для сборки)
FROM amazoncorretto:21-alpine3.21 as builder

# Устанавливаем Maven (в образе Corretto его нет)
RUN apk add --no-cache maven

WORKDIR /app
# Копируем только pom.xml сначала для кэширования зависимостей
COPY pom.xml .
RUN mvn dependency:go-offline

# Копируем исходный код и собираем JAR
COPY src ./src
RUN mvn package -DskipTests

# ===== Runtime Stage ===== (используем JRE для запуска)
FROM amazoncorretto:21-alpine3.21

# Настройка времени и пользователя (без root)
RUN apk add --no-cache tzdata && \
    addgroup -S spring && adduser -S spring -G spring
USER spring

# Оптимальные JVM-флаги для контейнеров
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -Djava.security.egd=file:/dev/./urandom"

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar"]