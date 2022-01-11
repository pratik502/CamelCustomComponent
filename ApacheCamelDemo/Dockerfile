FROM openjdk:8-jdk-alpine
COPY target/cameldemo-jar-with-dependencies.jar cameldemo-jar-with-dependencies.jar
RUN mkdir /home/cameldemo
COPY camlvol /home/cameldemo/
ENTRYPOINT ["java","-jar","cameldemo-jar-with-dependencies.jar"]
EXPOSE 8080