# Greatest Common Divisor

**Stack:** Java, Spring, Apache CXF, MyBatis, MySQL

**Skills:** SOAP, REST, relational persistence


# Features!
  - REST based API to provide input parameters
  - SOAP based API to get GCD, list of GCD and its Sum
            - using XSD-first approach
  - 20 users can access system concurrently
  - Users would lose the session if they remain idle for more than 3 minutes

# Technologies Used
 - Spring - for dependency injection
 - Apache CXF - Soap API
 - Jersey - REST API
 - MyBatis - for persistence
 - Mysql Database
 - ActiveMq for messaging between to REST server and SOAP server
 - Tomcat server (7.0)

# Packaging
### [gcdcommon](https://github.com/ynmanware/gcd/tree/master/gcdcommon) - all common components 
### [gcdrest](https://github.com/ynmanware/gcd/tree/master/gcdrest) - REST API specific artifacts, it is an web application
### [gcdsoap](https://github.com/ynmanware/gcd/tree/master/gcdsoap) - Soap API specific artifacts, it is an web application  

### [gcdear](https://github.com/ynmanware/gcd/tree/master/gcdear) - Eclipse based ear project, assist in generating EAR file.  

# Testing
   - Junit test cases are located in the standard location, mostly in the gcdcommon module and SOAP module
   - End to end to testing is performed on Tomocat Server (JBOSS does not like CXF)

# Hot to run this application?
    - download it
    - compile all modules using maven command (or from eclipse)
    - Mysql database with following table
    ```sh
    CREATE TABLE GCD(
        ID int unsigned NOT NULL auto_increment,
        API_KEY  VARCHAR (45)    NOT NULL,
        PARAM1 INT              NOT NULL,
        PARAM2 INT              NOT NULL,
        RESULT INT      ,      
        PRIMARY KEY (ID)
    );
    ```
> Mysql configuration is in gcdcommon->resource->jdbc.properties
> ActiveMq configuration is in MessageQueueHandler.java
#### Put the two web projects in the Tomcat server and start it!

# Access REST API
### Submit Parameters
Post - http://localhost:8080/gcdrest/webapi/gcd/parameters
Header parameter  'apiKey' (optional first time)

body
```sh
{
  "param1": "20",
  "param2": "14"
}
```

> response - Parameters Successfully Processed with key a8fb3b35-9f74-4aef-afd2-bd41f65da7db

To maintain the session you need to use the above key in the header having name 'apiKey' in subsequent requests. 
apiKey='a8fb3b35-9f74-4aef-afd2-bd41f65da7db' 
> if apiKey is absent in the Header, it will be considered as a new user

### Fetch Parameters
GET http://localhost:8080/gcdrest/webapi/gcd/parameters 
> with the apiKey as mentioned above

# Access SOAP API
### WSDL
http://localhost:8080/gcdsoap/services/gcd?wsdl
#### apiKey Header
```sh
<apikey xmlns="http://www.ynm.com/service/gcd/">1f1b3a9a-4f34-43ca-8051-9af2c183525e</apikey>
```

### GET GCD Result (request with apiKey, example is give above)
http://localhost:8080/gcdsoap/services/gcd

### GET GCDList (request with apiKey, example is give above)
http://localhost:8080/gcdsoap/services/gcdList

### GET GCDSum (request with apiKey, example is give above)
http://localhost:8080/gcdsoap/services/gcdSum

### Todos
    - Maven based build which woould support JBOSS
    - (ATM, To generate an EAR, you have to im
    port the projects in eclipse and export EAR using gcddear project)
    - Make application more secure
