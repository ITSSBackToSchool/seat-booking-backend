FROM openjdk:21
ADD target/*.jar course-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "course-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080