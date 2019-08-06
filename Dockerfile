FROM openjdk:8-jdk-alpine
ADD target/docker-data-frontend.jar docker-data-frontend.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "docker-data-frontend.jar"]
