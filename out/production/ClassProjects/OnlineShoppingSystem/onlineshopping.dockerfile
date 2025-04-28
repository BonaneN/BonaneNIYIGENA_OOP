FROM openjdk:21-jdk-slim
WORKDIR /app
# Copy all files from the project folder
COPY . .

# Compile all Java files (adjust if using Maven/Gradle)
RUN javac -d ./out *.java

# Run the main class (replace with your actual main class)
CMD ["java", "-cp", "out", "Main"]