FROM amazoncorretto:21-alpine3.18-jdk
WORKDIR /app
COPY . .
RUN ./mvnw clean install -DskipTests

FROM amazoncorretto:21-alpine3.18
WORKDIR /app
COPY --from=0 /app/target/datastream-0.0.1-SNAPSHOT.jar ./datastream.jar
ENTRYPOINT java -jar datastream.jar
