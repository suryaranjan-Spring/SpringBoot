FROM openjdk:11
EXPOSE 8080
ADD target/spring-kafka.jar spring-kafka.jar
ENTRYPOINT ["java","-jar","/spring-kafka.jar"]