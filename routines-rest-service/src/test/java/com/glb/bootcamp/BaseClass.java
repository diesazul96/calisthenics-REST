package com.glb.bootcamp;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * To verify an API provider (the Spring controller in our case), Spring Cloud Contract automatically generates JUnit
 * tests from a given contract. In order to give these automatically generated tests a working context, we need to
 * create a base test class which is subclassed by all generated tests:
 *
 * In this base class, we’re setting up a Spring Boot application with @SpringBootTest and are mocking away the
 * CustomerService so that it always returns the person specified in the contract. Then, we set up RestAssured so that the
 * generated tests can simply use RestAssured to send requests against our controller.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RoutinesRestServiceApplication.class)
public abstract class BaseClass {
}
