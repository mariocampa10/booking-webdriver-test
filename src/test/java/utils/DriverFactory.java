package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    //Marked as syncronized for parallel execution
    public synchronized static WebDriver getDriver() {
        return new ChromeDriver();
    }
}