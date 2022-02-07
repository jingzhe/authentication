FROM maven:3.8.4 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 17
FROM openjdk:17-alpine
ARG JAR_FILE=authentication.jar
WORKDIR /opt/app

# Copy jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

# Copy jks from the maven stage to the /opt/app directory of the current stage.
RUN mkdir conf
COPY --from=maven /usr/src/app/conf/* /opt/app/conf/

ENTRYPOINT ["java","-jar","authentication.jar"]