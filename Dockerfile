FROM openjdk:17-oracle

COPY ./target/*.jar banking-app.jar

CMD ["java", "-Xmx256m", "-jar", "banking-app.jar"]