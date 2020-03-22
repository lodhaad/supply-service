# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

EXPOSE 80

# The application's jar file
ARG JAR_FILE=target/supply-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ${JAR_FILE} supply-service.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/supply-service.jar"]