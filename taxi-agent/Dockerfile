FROM azul/zulu-openjdk-alpine:21-jre-latest

WORKDIR /opt/app
ADD target/taxi-agent*.jar ./app.jar

CMD ["java", "-jar", "app.jar"]