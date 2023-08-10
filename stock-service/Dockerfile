FROM adoptopenjdk/openjdk11
EXPOSE 8080
ARG JAR_FILE=build/libs/demo1-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} stock.jar
ENTRYPOINT ["java", "-jar","/stock.jar"]
