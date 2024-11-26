package org.github.guzov.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected String originalWindow;

    protected AbstractPage (WebDriver driver)
    {
        this.driver = driver;
        this.js =  (JavascriptExecutor) driver;
        originalWindow = driver.getWindowHandle();
        PageFactory.initElements(driver, this);
    }

}
