FROM eclipse-temurin:17-jre-alpine

COPY ./jag-coa-application/target/jag-coa-application.jar jag-coa-application.jar

ENTRYPOINT ["java", "-Xmx1g", "-jar","/jag-coa-application.jar"]
