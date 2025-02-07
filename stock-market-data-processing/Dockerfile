FROM ubuntu:latest
LABEL maintainer="ft" 
EXPOSE 8200
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk;
COPY target/stock-market-data-processing-0.0.1-SNAPSHOT.jar /home
ENTRYPOINT ["java","-jar","/home/stock-market-data-processing-0.0.1-SNAPSHOT.jar", "8200"]