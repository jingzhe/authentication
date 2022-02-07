# Authentication service
## How to run the service
Use IntelliJ IDEA to open the project and run the service, or use command line bellow
### Compile
```
mvn compile
```
### Start the service
```
mvn spring-boot:run
```
### Run the test cases
```
mvn test
```

## OpenApi documentation
http://localhost:8082/webjars/swagger-ui/index.html

## How to run the service with Docker
### Create package jar
```
mvn package
```
### Build Docker image
```
docker build -t jingzheyu/authentication .
```
### Run the Docker container
```
docker run -p 8082:8082 jingzheyu/authentication

## How to update the key
keytool -genkeypair -alias building_auth -keyalg RSA -keypass testpass -storepass storepass -validity 1000 -deststoretype jks -keystore building.jks
keytool -exportcert -keystore building.jks -alias building_auth -rfc -file building_pub.crt
