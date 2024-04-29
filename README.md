# java-selenium-base-repo

### Language and libs:
- Java
- Cucumber
- TestNG

### Integrations:

- Allure Reports
- Slack Notifications

## The commands to run the tests by console are:
#### All tests:
`mvn clean test`
#### All tests without disabled(ignored):
`mvn clean test -D"cucumber.filter.tags=not @Ignore"`
#### Regression tests without disabled:
`mvn clean test -D"cucumber.filter.tags=@Regression not @Ignore"`

Run the command `mvn clean compile test-compile `, then you can run any test via Cucumber or Maven configuration.

## Reports
#### For local tests result:
`allure serve target/allure-results`
