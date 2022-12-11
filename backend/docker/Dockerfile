FROM node:lts-alpine AS frontend-builder

COPY . /build

WORKDIR /build/frontend

RUN yarn install && yarn build



FROM maven:3-eclipse-temurin-17-alpine AS backend-builder

COPY . /build

COPY --from=frontend-builder /build/frontend/dist/* /build/backend/src/main/resources/static/

WORKDIR /build

RUN mvn package -pl backend -Dmaven.test.skip=true


FROM eclipse-temurin:17-jre-alpine

COPY --from=backend-builder /build/backend/target/backend-0.0.1.jar /app/api.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "api.jar"]