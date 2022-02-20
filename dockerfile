FROM maven:3.8.4-jdk-11-slim AS build
WORKDIR /workspace
COPY pom.xml .
COPY src /workspace/src
RUN mvn -f pom.xml clean install -DskipTests=true

FROM openjdk:11.0.14-oracle
COPY --from=build /workspace/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]