# ITRex group test task

This project is a test task performance for ITRex group. I have used JAVA programming language.

## Project tools:

* **Selenium WebDriver** - to automate web application for testing purposes
* **TestNG** - to execute tests using flexible configuration
* **Maven** and its **Surefire plugin** - to download the project dependency libraries, run project 
and generate test report
* **ExtentReports** - to generate nice report

I also used the **Page Object model** design pattern. Web pages in my project are represented as 
classes, and the various elements on the page are defined as variables on the class. All possible 
user interactions are implemented as methods.
Automated tests (which are located separately) may use different pages elements and its interactions.

## How to run:

Download project to your local machine. Put chromedriver into `resources\drivers\` subfolder.
Open up a terminal and move to the project. Run the maven test command:
`mvn test`.
Test report is generated automatically after tests running. Look for report in 
the following folder: `/target/extent-reports/Report.html`.

I have not added `target` directory to VCS. So if you want to **read report** look for real example 
**in root directory**.