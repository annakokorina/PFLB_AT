package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SortUsers {
    public WebDriver driver;
    private int ID =0;

    public SortUsers(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(css = "a[id=basic-nav-dropdown]")
    private WebElement usersButton;

    @FindBy(css = "a[href=\"#/read/users\"]")
    private WebElement readAllButton;

    @FindBy(css = "#root > div > section > div > div > button:nth-child(3)")
    private WebElement sortByIdButton;

    @FindBy(css = "table tbody tr")
    private List<WebElement> id;

    @Step("Нажать на вкладку Users")
    public void usersButton(){
        usersButton.click();
    }
    @Step("Нажать Read all")
    public void readAllButton(){
        readAllButton.click();
    }
    @Step("Сортировать по ID")
    public void sortById(){
        sortByIdButton.click();
    }
    public String getUserId(int num) {
        List<WebElement> tds = getUserRowCells(num);
        return tds.get(ID).getText();
    }
    private List<WebElement> getUserRowCells(int num) {
        WebElement tableRow = id.get(num);
        return tableRow.findElements(By.cssSelector("td"));
    }
}
