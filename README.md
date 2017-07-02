# symprap-server
Server side for symptom raporting software

### Testing
###### Requirements:
- Java 8+
- Maven 3.2+
- Lombok plugin for Eclipse (https://projectlombok.org/download.html)

###### Program arguments
- keystore.file: location of the keystore
- keystore.pass: password of the keystore
- testDataEnabled: use this to inject test data (see TestDataInjector.java) into the database
- adminPass: pass for the admin user that is created when booting application

###### Running the application from the command line: 
  mvn spring-boot:run -Dkeystore.file=src/main/resources/private/keystore -Dkeystore.pass=changeit -DtestDataEnabled=true -DadminPass=adpass

###### Running application from Eclipse

First import application as Maven Project. Then go to Run -> Run configurations -> Java Application -> New. Set symprap-server as project and fi.ukkosnetti.symprap.SymprapApplication as Main class. Add program arguments listed above in the arguments tab. Click Run.
