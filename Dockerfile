FROM eclipse-temurin:17-jre-alpine

RUN apk upgrade libexpat

COPY ./jag-coa-application/target/jag-coa-application.jar jag-coa-application.jar

ENTRYPOINT ["java", "-Xms32m", "-jar","/jag-coa-application.jar"]
