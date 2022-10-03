#FROM captain.rtf.siemens.net:8443/sinecams-dev-internal/deb-11-adpotjdk-11:11
FROM openjdk:11-jdk
COPY target/springneo4j.jar springneo4j.jar
ENTRYPOINT ["java","-jar","/springneo4j.jar"]