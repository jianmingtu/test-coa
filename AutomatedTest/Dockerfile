FROM openjdk:11-jre-slim

COPY ./target/coa-test-runner.jar coa-test-runner.jar

ENTRYPOINT ["java","-Xss2048k","-jar","/coa-test-runner.jar"]