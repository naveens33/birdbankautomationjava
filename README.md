# BirdBank Automation

### Dependencies
* Selenium
* TestNg
* Extent Report
* Apache POI

### Plugin 
* maven-surefire-plugin

### Folder/Package Structure
* base
  * BaseTest class
    * BeforeSuite, AfterSuite & Common methods (like, AfterMethod -> on failure create screenshot). 
    * creating/quiting driver
    * configuring the extent report
    * on failure capture screenshot
* pages (POM/Page Factory)
  * Page classes like, HomePage, LoginPage, AccountPage. And all page classes going to inherit BasePage. These page class has 2 important things 
    * Page Objects like, LoginButton, By.id("signin_button")
    * Page Actions like, clickLoginButton()
* testdata
  * Excel files - testdata files
  * ReadExcelData class
    * responsible to read the data from the Excel file
* reports 
  * extent report - html reports captured under this package
* tests
  * TestNG Classes and test methods are written
  * And TestNg classes should inherit the BaseTest
* Note:
  * testNg.xml file -> will be placed in the root directory of the project