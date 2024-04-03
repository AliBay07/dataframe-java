FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y openjdk-17-jdk
RUN apt-get install -y maven
COPY . .
RUN mvn clean
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]
