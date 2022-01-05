# TMS_SauceDemoTsvirkoNata
#libraries used in the project:

-selenium version 3.141.59;

-webdrivermanager version 5.0.3;

-testNG version 6.14.3;
#Operation commands:
In order to run tests, use the command:

-mvn clean test -DsuiteXmlFile=src/test/resources/negative.xml

command result:  Tests run: 4, Failures: 3, Errors: 0, Skipped: 0;

-mvn clean test -DsuiteXmlFile=src/test/resources/regression.xml

command result: Tests run: 21, Failures: 9, Errors: 0, Skipped: 0;

-mvn clean test -DsuiteXmlFile=src/test/resources/smoke.xml

command result:  Tests run: 8, Failures: 6, Errors: 0, Skipped: 0;

To run Login tests use the command:

-mvn -Dtest=LoginTest test

command result:  Tests run: 6, Failures: 3, Errors: 0, Skipped: 0

To run ProductPage tests use the command:

-mvn clean test -Dtest=ProductsPageTest test

command result: Tests run: 7, Failures: 0, Errors: 0, Skipped: 0

To run ShoppingCart tests use the command:

-mvn clean test -Dtest=ShoppingCartTest test

command result: Tests run: 8, Failures: 6, Errors: 0, Skipped: 0