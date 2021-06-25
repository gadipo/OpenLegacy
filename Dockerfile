FROM openjdk:latest
EXPOSE 8080
ADD target/spring-boot-openLegacy.jar spring-boot-openLegacy.jar
ENTRYPOINT ["java","-jar","/spring-boot-openLegacy.jar"]