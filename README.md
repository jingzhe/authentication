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

## How to update the key
keytool -genkeypair -alias building_auth -keyalg RSA -keypass testpass -storepass storepass -validity 1000 -deststoretype jks -keystore building.jks
keytool -exportcert -keystore building.jks -alias building_auth -rfc -file building_pub.crt
