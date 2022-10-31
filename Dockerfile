FROM openjdk:8-jdk-alpine
VOLUME /home/jenkins/deploy
COPY build/libs/chilipotli-service-0.0.1.jar chilipotli-service.jar
ENTRYPOINT ["java", "-jar", "/chilipotli-service.jar"]
EXPOSE 8080
