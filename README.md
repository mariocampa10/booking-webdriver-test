# booking-webdriver
Example of Booking test using Selenium Webdriver

## Execute
1. Clone repo from GitHub

```
git clone https://github.com/mariocampa10/booking-webdriver.git
```
2. Execute Grade Test on root project folder
```
./gradlew clean test
```
To see output console prints
```
./gradlew clean test --debug
```
3. Report generated (with standard output)
```
build/reports/tests/test/index.html
```

## Libraries
### Selenium Webdriver for Java
Java based framework that performs all operations against UI of the web application.

### TestNG
Testing framework inspired from JUnit that provides powerful functionalities used in this example like the use of Data Providers, Tests Listeners or XML files in order to have more control on test executions.

### Hamcrest Matchers
Hamcrest Matchers provides a way to generate asserts in a more human readable.

## Project Structure Folders
### Constants
Here we will put all common relevant fixed data used in pages and tests.

### Page Object
#### Base Page
It will contain common functionalities for every new page.

#### Landing & Results Page (Inherits from Base Page)
Pages corresponding to specific pages visualization on Booking web.
They are composed mainly by these parts (always in same order to keep consistency):
1. Mapping of components of the screen
2. Action methods (click, set values ...)
3. Check methods (has dates)
4. Private methods

### Tests
#### Base Test
It will contain the driver (needed to get instance for screenshots) and also it could keep common data that could be used in different defined tests.

#### Search test (Inherits from Base Test)
It will perform a simple search on web by a range of dates and it will print results and check checkin & checkout dates.
In order to make test more robust, language is fixed when navigating to landing page. In this case, we will test with EN (UK), ES and NL languages. UI components specification are designed in order to avoid this dependency but in order to parse format dates we need to take language into account.

### Utils
#### Driver Factory
Returns an instance of ChromeDriver. This class could keep more logic in order to execute tests againts different browsers or even use Remote Driver by using Selenium Grid.

#### Listener
Provided by TestNG, it allows us to have more control on execution events. In this case we have introduced to take screenshots in screenshots/ folder when a test fail. This functionnality is very powerful and we can enhace our framework by using it, for example, to implement a retries policy.

### Resources
#### Testng XML
Here, we set the classes or methods we want to execute and also we glue our test listeners. It provides also capabilities in order to run in parallel (out of scope in this example).

