FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} eurekaserviceregistry.jar

ENTRYPOINT ["java","-jar","/eurekaserviceregistry.jar"]

EXPOSE 8761