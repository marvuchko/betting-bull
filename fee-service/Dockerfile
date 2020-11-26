FROM openjdk:11-alpine
WORKDIR /app
COPY /target/*.jar /app/main.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/main.jar"]
