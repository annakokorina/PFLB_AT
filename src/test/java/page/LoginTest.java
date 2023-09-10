package page;

import config.ApplicationConfig;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@ExtendWith(TestSсreenshot.class)
public class LoginTest {
    public static LoginPage loginPage;
    public static SortUsers sortUsers;
    public static WebDriver driver;
    public  static WebDriverWait wait;
    public static ApplicationConfig config;

    @BeforeAll
    public static void configInit()
    {
        config = new ApplicationConfig();
    }

    @BeforeEach
    public void init(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(config.baseUrl);
        loginPage = new LoginPage(driver);
        sortUsers = new SortUsers(driver);
    }

    @AfterEach
    public  void driverQuit() {
        driver.quit();
    }

    @Test
    @Description("Test login")
    public void LoginTest() {

        loginPage.inputLogin(config.username);
        loginPage.inputPass(config.userPassword);
        loginPage.inputButton();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = wait.until(driver -> {
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            alert.dismiss();
            return text;
        });
        Assertions.assertTrue(alertText.contains("Successful"), "Alert text doesn't contains info about successful auth");
    }

    @Test
    @Description("Test sortById")
    public void SortTest() {
        sortUsers.usersButton();
        sortUsers.readAllButton();

        int[] usersIdBeforeSort = new int[25];
        int[] usersIdAfterSort = new int[25];

        for (int i = 0; i < usersIdBeforeSort.length; i++) {
            usersIdBeforeSort[i] = Integer.parseInt(sortUsers.getUserId(i));
        }

        sortUsers.sortById();
        for (int i = 0; i < usersIdAfterSort.length; i++) {
            usersIdAfterSort[i] = Integer.parseInt(sortUsers.getUserId(i));
        }

        if (Arrays.equals(usersIdBeforeSort, usersIdAfterSort)) {
            fail("Тест не пройден!");

        } else {
            System.out.println("Тест пройден!");
        }
        System.out.println("BeforeSort = " + Arrays.toString(usersIdBeforeSort));
        System.out.println("AfterSort = " + Arrays.toString(usersIdAfterSort));
    }
}

