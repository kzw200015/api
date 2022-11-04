FROM maven:3-eclipse-temurin-17-alpine AS builder

COPY . /build

WORKDIR /build

RUN mvn package -pl backend -Dmaven.test.skip=true


FROM eclipse-temurin:17-jre-alpine

COPY --from=builder /build/backend/target/backend-0.0.1.jar /app/api.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "api.jar"]