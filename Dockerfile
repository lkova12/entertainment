FROM maven:3.5.3-jdk-8-slim as jar
WORKDIR /entertainment
COPY pom.xml ./
COPY src src
RUN mvn clean install spring-boot:repackage

FROM openjdk:8-jdk-alpine
WORKDIR /entertainment
EXPOSE 8080
COPY --from=jar /entertainment/target/entertainment-0.0.1-SNAPSHOT.jar entertainment.jar
COPY entry.sh .
USER root
ENTRYPOINT ["sh", "entry.sh"]

HEALTHCHECK --interval=15m --timeout=60s --retries=1\
  CMD wget -q -s http://localhost:8080/ || exit 1

LABEL maintainer="lesiakoval12@gmail.com"