FROM openjdk:11
EXPOSE 8080
ADD target/Spring-Kafka.jar Spring-Kafka.jar
ENTRYPOINT ["java","-jar","/Spring-Kafka.jar"]