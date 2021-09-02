FROM maven:3.8.1-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java
COPY --from=build /usr/src/app/target/desafio-back-southsystem-0.0.1-SNAPSHOT.jar /usr/app/desafio-back-southsystem-0.0.1-SNAPSHOT.jar
EXPOSE 8030
ENTRYPOINT ["java","-jar","/usr/app/desafio-back-southsystem-0.0.1-SNAPSHOT.jar"]