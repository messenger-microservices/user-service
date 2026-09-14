# Build Stage
FROM eclipse-temurin:25-jdk-jammy AS build

WORKDIR /app-build

COPY ./gradle ./gradle
COPY gradlew settings.gradle gradle.properties ./

RUN ./gradlew dependencies --no-daemon

COPY build.gradle.kts ./
COPY ./src ./src

RUN ./gradlew booJar -x test --no-daemon

# Execution Stage
FROM eclipse-temurin:25-jre-jammy AS execution

WORKDIR /app
COPY --from=build /app-build/build/libs/*.jar application.jar

EXPOSE 8001
ENTRYPOINT ["java", "-jar", "application.jar"]
