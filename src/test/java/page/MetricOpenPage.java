package page;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.performance.Performance;
import org.openqa.selenium.devtools.v115.performance.model.Metric;


import java.util.List;
import java.util.Optional;

public class MetricOpenPage {
    public String timestamp;
    @Test
    @Description("MetricOpenPage")
    @Step
    public void openPage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTools.send(Performance.getMetrics());
        driver.get("http://77.50.236.203:4881/");
        metricList.stream().filter(m -> m.getName().equals("Timestamp")).forEach(m -> timestamp = m.getName() + " = " + m.getValue());
        metricTimeSpeedPage(timestamp);
        driver.quit();
    }
    @Step
    public void metricTimeSpeedPage(String name){
        Assert.assertNotNull(name);
        System.out.println(name);
    }
}