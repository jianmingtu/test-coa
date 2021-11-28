FROM openjdk:11-jre-slim

COPY ./target/coa-application.jar coa-application.jar

ENTRYPOINT ["java","-jar","/coa-application.jar"]
