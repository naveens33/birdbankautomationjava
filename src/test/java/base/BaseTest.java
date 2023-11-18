package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @Parameters({"browser"})
    @BeforeSuite
    public void beforeSuite(String browser){
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\src\\test\\java\\reports\\report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("User Name","Naveen S");
        extent.setSystemInfo("Environment","Production");
        extent.setSystemInfo("OS","Window 10");
        extent.setSystemInfo("Browser",browser);
        spark.config().setDocumentTitle("Test Report");
        spark.config().setReportName("Bird Bank - Smoke Test");
        spark.config().setTheme(Theme.STANDARD);

        switch (browser){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Wrong browser name specified...");
        }
       driver.manage().window().maximize();
       driver.get("https://birdbank.pythonanywhere.com/");
    }

    public String takeScreenShot(){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        String formattedTimestamp = dateFormat.format(currentDate);
        String filePath= System.getProperty("user.dir")+"\\src\\test\\java\\reports\\"+formattedTimestamp+".png";
        File destination = new File(filePath);
        try {
            FileHandler.copy(source, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL,result.getThrowable());
            test.fail("Testcase failed screenshot is captured"+test.addScreenCaptureFromPath(takeScreenShot()));
        }
    }

    @AfterSuite
    public void afterSuite(){
        driver.quit();
        extent.flush();
    }
}
