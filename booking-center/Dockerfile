FROM azul/zulu-openjdk-alpine:21-jre-latest

WORKDIR /opt/app
ADD target/booking-center*.jar ./app.jar

EXPOSE 8081 8081
EXPOSE 8080 8080
CMD ["java", "-jar", "app.jar"]