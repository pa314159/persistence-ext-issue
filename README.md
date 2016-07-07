
# Demonstrates [issue 900](https://github.com/payara/Payara/issues/900) in Payara/Glassfish

## Description

The project contains few beans in which an EntityManager is injected.

The `RegularBean` bean is provided via the normal CDI mechanism, others are provided by a CDI extension
in the `BeforeBeanDiscovery`, `AfterTypeDiscovery` phases.

Injections of EntityManager in beans provided by the CDI extension don't work on Payara-4.1.1.162; they
work on Wildfly-10

Arquillian tests are provided for both Payara and Wildfly.

## Running the test

* clone this project
* run ./gradlew
* open build/reports/all-tests/index.html in a browser
