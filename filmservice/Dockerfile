FROM openjdk:23-slim-bullseye
WORKDIR /build
EXPOSE 8082
COPY target/filmservice-0.0.1-SNAPSHOT.jar filmservice.jar
ENTRYPOINT ["java", "-jar", "/build/filmservice.jar"]