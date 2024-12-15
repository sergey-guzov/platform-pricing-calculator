package org.github.guzov.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class DriverSingleton {

    private static WebDriver driver;
    private DriverSingleton (){}

    public static WebDriver getDriver ()
    {
        if (driver == null)
        {
            switch (System.getProperty("browser"))
            {
                case ("firefox"):
                    FirefoxOptions option = new FirefoxOptions();
                    option.addArguments("-headless");
                    driver = new FirefoxDriver();
                    break;
                default: {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void stopDriver ()
    {
        driver.quit();
        driver = null;
    }
}
