package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PayBillsPage;
import testdata.ReadExcelData;

import java.io.IOException;

public class TestPayBills extends BaseTest {

    @BeforeTest
    public void beforeTest(){
        HomePage home = new HomePage(driver);
        home.clickLoginButton();
        LoginPage login = new LoginPage(driver);
        login.doLogin("testuser1","testpassword");
    }

    @BeforeClass
    public void beforeClass(){
        AccountPage account = new AccountPage(driver);
        account.clickPayBillsLink();
    }

    @Test(dataProvider = "dp")
    public void testAddNewPayee(String name, String regNo){
        test = extent.createTest("Add New Payee - "+name);
        PayBillsPage payBills = new PayBillsPage(driver);
        payBills.clickAddNewPayeeButton();
        test.log(Status.PASS,"Navigated successfully to Biller Information page");
        payBills.doAddNewPayee(name,regNo);
        Assert.assertEquals(payBills.getConfirmationText(),"Payee Added Successful");
        Assert.assertTrue(payBills.getBillerNames().contains(name),"Biller name is not listed");
        test.log(Status.PASS,"Biller Added successfully");
    }

    @DataProvider
    public Object[][] dp() throws IOException {
        ReadExcelData data = new ReadExcelData("TestData.xlsx","NewBiller");
        return data.getData();
    }
}
