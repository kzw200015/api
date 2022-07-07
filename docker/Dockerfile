FROM maven:3-eclipse-temurin-17-alpine as builder

COPY . /build

WORKDIR /build

RUN mvn package


FROM eclipse-temurin:17-jre-alpine

COPY --from=builder /build/target/api-*.jar /app/api.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "api.jar"]