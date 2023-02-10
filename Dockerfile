FROM openjdk:11
EXPOSE 8080
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw package
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
