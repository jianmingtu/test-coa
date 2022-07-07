FROM openjdk:11-jre-slim

COPY ./jag-coa-application/target/jag-coa-application.jar jag-coa-application.jar

ENTRYPOINT ["java", "-Xmx256m", "-jar","/jag-coa-application.jar"]
