FROM openjdk:23-slim-bullseye
WORKDIR /build
EXPOSE 8083
COPY target/rateservice-0.0.1-SNAPSHOT.jar rateservice.jar
ENTRYPOINT ["java", "-jar", "/build/rateservice.jar"]