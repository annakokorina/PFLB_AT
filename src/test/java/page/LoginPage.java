package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
    @FindBy(css = "input[name=email]")
    private WebElement loginInput;
    @FindBy(css = "input[name=password]")
    private WebElement passwordInput;
    @FindBy(css = "button[type=submit]")
    private WebElement submitButton;

    @Step("InputLogin")
    public void inputLogin(String login){
        loginInput.sendKeys(login);
    }
    @Step("InputPassword")
    public void inputPass(String password){
        passwordInput.sendKeys(password);
    }
    @Step("ClickButton")
    public void inputButton(){
        submitButton.click();
    }
}
