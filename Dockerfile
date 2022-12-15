FROM maven:3.8.4-openjdk-17
WORKDIR /
ENV PORT 8080
EXPOSE 8080
COPY target/*.jar /app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar