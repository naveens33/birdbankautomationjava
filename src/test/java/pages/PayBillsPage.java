package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class PayBillsPage {
    WebDriverWait wait;
    public PayBillsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @FindBy(id = "add_payee")
    WebElement addNewPayeeButton;

    @FindBys({
            @FindBy(id = "hello"),
            @FindBy(className = "world")
    })
    WebElement addNewPayeeButton1;

    @FindAll({
            @FindBy(id = "hello"),
            @FindBy(className = "world")
    })
    WebElement addNewPayeeButton2;

    @FindBy(xpath = "//input[contains(@placeholder,'Name')]")
    WebElement billerNameField;

    @FindBy(xpath = "//input[contains(@placeholder,'Number')]")
    WebElement regNumberField;

    @FindBy(xpath = "//label[text()='No']")
    WebElement noRadioButton;

    @FindBy(id ="save")
    WebElement saveButton;

    @FindBy(id="confirmationMessage")
    WebElement confirmationMessageText;

    @FindBy(xpath = "//table/tbody/tr/td[1]")
    List<WebElement> billerNameElements;

    public void clickAddNewPayeeButton(){
        addNewPayeeButton.click();
    }

    public void doAddNewPayee(String name, String regNo){
        wait.until(ExpectedConditions.visibilityOf(billerNameField)).sendKeys(name);
        regNumberField.sendKeys(regNo);
        noRadioButton.click();
        saveButton.click();
    }

    public String getConfirmationText(){
       return confirmationMessageText.getText();
    }

    public List<String> getBillerNames(){
        return billerNameElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
