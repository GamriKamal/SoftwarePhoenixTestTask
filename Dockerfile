FROM openjdk:17-oracle
ARG JAR_FILE=target/*.war
COPY ./target/RestAPITestTask-0.0.1-SNAPSHOT.war app.war
#EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app.war"]