package org.github.guzov.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverSingleton {

    private static WebDriver driver;
    private DriverSingleton (){}

    public static WebDriver getDriver ()
    {
        if (driver == null)
        {
            switch (System.getProperty("browser"))
            {
                case ("firefox"): driver = new FirefoxDriver();
                default: {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox");
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
