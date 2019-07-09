Contract Rest Service
==========================================

The service is implemented in Java using Spring and Spring Cloud
The implementation is exposing a REST API.


Technologies
------------

- Spring Boot
- Spring Cloud



How To Compile
--------------

The microservice can be compiled with:

```
gradle clean build
```

How To Run
----------

The microservice can be started with:

```
gradle bootRun
```



How To Test
----------

The microservice can be tested with:

```
gradle test
```

Cloud Contract
----------
### Usage
Use Gradle to test, build and publish artifacts for local development.

##### Producer tasks
In addition to test and build, there are other tasks specific to the producer:

* **install** - Install artifacts including stubs.jar into the local Maven repository


#### Links
More details can be found on [Spring-Contract-Rest](https://spring.io/guides/gs/contract-rest/) 
and [Contract DSL](https://cloud.spring.io/spring-cloud-contract/spring-cloud-contract.html#_contract_dsl)


