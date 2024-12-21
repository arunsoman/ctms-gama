# Use the official OpenJDK image as a base
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven build output to the container
COPY target/fi-onboarding-0.0.1-SNAPSHOT.jar fi-onboarding.jar

# Expose the application port
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "fi-onboarding.jar"]
