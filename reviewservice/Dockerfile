FROM openjdk:23-slim-bullseye
WORKDIR /build
EXPOSE 8083
COPY target/reviewservice-0.0.1-SNAPSHOT.jar reviewservice.jar
ENTRYPOINT ["java", "-jar", "/build/reviewservice.jar"]