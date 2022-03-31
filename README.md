# AddressBookApi

### Requirements
* Gradle

### How to use
* Run ```./gradlew bootRun``` from the command line to start up the application.


* The h2 database can be accessed by /h2-console and inputing the following 
into the login page:
  * Driver Class:   org.h2.Driver
  * JDBC URL: jdbc:h2:mem:testdb
  * Username: sa
  * Password:
* This will give you access to the h2 database as well as the values preloaded via data.sql