FROM openjdk:11
VOLUME /home/jenkins/deploy
COPY build/libs/chilipotli-service-0.0.1.jar chilipotli-service.jar
COPY /etc/keys/airsoftware-5908f25c2d2b.json /etc/keys/airsoftware-5908f25c2d2b.json
ENTRYPOINT ["java", "-jar", "/chilipotli-service.jar"]
EXPOSE 8080
