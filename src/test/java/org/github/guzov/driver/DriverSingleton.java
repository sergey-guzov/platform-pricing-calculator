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
                case ("firefox"):
                    driver = new FirefoxDriver();
                    break;
                default: {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless", "--disable-gpu", "--disable-infobars", "--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage", "--window-size=1920,1080", "--remote-allow-origins=*");
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
