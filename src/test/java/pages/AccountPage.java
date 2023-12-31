package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    public AccountPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "PAY BILLS")
    WebElement payBillsLink;

    public void clickPayBillsLink(){
        payBillsLink.click();
    }
}
