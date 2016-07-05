
# Demonstrates [issue 900](https://github.com/payara/Payara/issues/900) in Payara/Glassfish

## Description

The project contains two beans `SomeBean1` and `SomeBean2` in which an EntityManager is injected.

The first bean is provided via the normal CDI mechanism, while the second bean is provided by a CDI extension in the `AfterTypeDiscovery` phase.

Injection of EntityManager in `SomeBean2` doesn't work on Payara-4.1.1.162, but works on Wildfly-10

Arquillian tests are provided for both applications servers.

## Running the test

* clone this project
* run ./gradlew
* open build/reports/all-tests/index.html in a browser
