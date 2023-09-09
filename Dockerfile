FROM ubuntu:latest
COPY target/sprftpfilessynch-0.0.1-SNAPSHOT.jar /synch.jar
CMD ["java", "-jar", "/synch.jar"]