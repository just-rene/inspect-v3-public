FROM ubuntu:latest
LABEL maintainer="ft" 
EXPOSE 8081
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk;
COPY target/demo-0.0.1-SNAPSHOT.jar /home
ENTRYPOINT ["java","-jar","/home/demo-0.0.1-SNAPSHOT.jar", "8081"]