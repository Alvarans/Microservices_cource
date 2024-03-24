FROM openjdk:23-slim-bullseye
WORKDIR /build
EXPOSE 8084
COPY target/userservice-0.0.1-SNAPSHOT.jar userservice.jar
ENTRYPOINT ["java", "-jar", "/build/userservice.jar"]