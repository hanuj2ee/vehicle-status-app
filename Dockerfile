# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/vehicle-status-app.jar 

# Add the application's jar to the container
ADD ${JAR_FILE} /opt/vehicle-status-app/lib/

ADD src/main/resources/data.json /opt/vehicle-status-app/lib/data.json
WORKDIR /opt/vehicle-status-app/lib

# Run the jar file 
ENTRYPOINT ["java","-cp","data.json","-jar","vehicle-status-app.jar"]