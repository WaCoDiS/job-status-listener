# build image
FROM maven:3.5.4-jdk-8 AS build  

COPY . /
RUN mvn -f /pom.xml clean install -DskipTests -P download-generate-models

# runnable image
FROM adoptopenjdk/openjdk8:alpine

COPY --from=build /target/job-status-listener-0.0.1-SNAPSHOT.jar /app.jar 

EXPOSE 5672
ENTRYPOINT ["java","-jar","/app.jar"]
