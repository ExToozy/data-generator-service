FROM gradle:8.2.1-jdk17-alpine AS build
USER root
RUN chown -R gradle:gradle /home/gradle
USER gradle
WORKDIR /app
COPY src /app/src
COPY build.gradle /app
COPY settings.gradle /app
RUN gradle clean build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]