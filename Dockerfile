FROM openjdk:11
ADD ./target/cacheredis-0.0.1-SNAPSHOT.jar /usr/src/cacheredis-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "cacheredis-0.0.1-SNAPSHOT.jar"]