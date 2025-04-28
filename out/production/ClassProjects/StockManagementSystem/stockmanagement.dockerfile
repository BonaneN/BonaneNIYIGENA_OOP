FROM openjdk:21-jdk-slim

WORKDIR /src

COPY ./src/StockManagementSystem ./src

RUN javac src/StockManagementSystem/Main.java -d ./out

CMD ["java", "-cp", "out", "StockManagementSystem.Main"]