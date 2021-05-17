# build image
FROM maven:3.5.4-jdk-8-alpine AS build

COPY . /
RUN mvn -f /pom.xml clean install -DskipTests -Dapp.finalName=job-status-listener

# runnable image
FROM adoptopenjdk/openjdk8:alpine

COPY --from=build /target/job-status-listener.jar /app.jar 

EXPOSE 5672
ENTRYPOINT ["java","-jar","/app.jar"]
