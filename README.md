# Password Creater and Bcrypt Encoder

Local random text (password) generator and bcrypt encoder. 

You don't need anymore an online tool that exposes your secret texts while creating a new password or credentials.
 
## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.vincentleom.utils.Application` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```
mvn spring-boot:run
```

## Usage

```
http://localhost:1115/encoding
```

* if "text" parameter is not null, return this text and its encoded
* if "text" parameter is null, it generates a alphanumeric text with given "length" parameter (default is 32) and its encoded
