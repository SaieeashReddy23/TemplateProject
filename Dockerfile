# Use the official Gradle image to build the application
FROM gradle:7.6.0-jdk17 AS build

# set working directory in the container
WORKDIR /app

#Copy gradle wrapper files and download the dependencies
COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY gradlew.bat /app/gradlew.bat
COPY settings.gradle /app/settings.gradle
RUN ./gradlew build --no-daemon

#Copy rest of the project and build the application
COPY . /app
RUN ./gradlew bootjar --no-daemon

#Using the official OpenJdk 17 image to run the application
FROM openjdk:17-jdk-slim

#Set the working directory in the container
WORKDIR /app

#Copy the jar file from the build stage to the run stage
COPY --from=build /app/build/libs/*.jar app.jar

#Expose the port of the application runs on
EXPOSE 8080

#Run the application
ENTRYPOINT ["java","-jar","app.jar"]
