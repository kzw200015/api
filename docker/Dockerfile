FROM maven:3-eclipse-temurin-17-alpine as builder

COPY . /build

WORKDIR /build

RUN gradle build -x test


FROM eclipse-temurin:17-jre-alpine

COPY --from=builder /build/build/libs/api-0.0.1.jar /app/api.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "api.jar"]