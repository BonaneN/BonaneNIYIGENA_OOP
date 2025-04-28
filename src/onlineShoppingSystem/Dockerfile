# Use a base image with Java 17 or higher
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

COPY . /app

RUN javac -d . *.java

CMD ["java", "onlineShoppingSystem.Main"]

