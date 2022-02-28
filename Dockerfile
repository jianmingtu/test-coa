FROM openjdk:11-jre-slim

COPY ./target/jag-coa-application.jar jag-coa-application.jar

ENTRYPOINT ["java","-jar","/jag-coa-application.jar"]
