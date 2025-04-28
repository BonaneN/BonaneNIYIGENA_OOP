# Use a base image with Java 17 or higher
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the contents of the insuranceSystem folder into the container
COPY . /app

# Compile the Java classes in the insuranceSystem package
RUN javac -d . *.java

# Command to run the Main class from the insuranceSystem package
CMD ["java", "insuranceSystem.Main"]

