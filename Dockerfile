FROM eclipse-temurin:11-jre-alpine

COPY ./jag-coa-application/target/jag-coa-application.jar jag-coa-application.jar

ENTRYPOINT ["java", "-Xmx256m", "-jar","/jag-coa-application.jar"]
