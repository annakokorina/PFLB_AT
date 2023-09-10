package page;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static page.LoginTest.driver;

public class TestSсreenshot implements TestWatcher {
    @Override
   public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment("Скриншот фейла теста",
                "image/png", "png", ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        driver.quit();
    }

}
