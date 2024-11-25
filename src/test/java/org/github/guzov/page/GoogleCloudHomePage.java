package org.github.guzov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudHomePage extends AbstractPage {

    private final String HOME_PAGE_URL = "https://cloud.google.com";
    private String language = "/?hl=ru";
    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchIcon;


    public GoogleCloudHomePage (WebDriver driver) {super(driver);}

    public GoogleCloudHomePage openPage () {
        driver.get(HOME_PAGE_URL+language);
        closeCookieNotification();
        return this;
    }

    public GoogleCloudSearchResultsPage searchFor (String searchItem) {
        searchIcon.click();
        searchIcon.sendKeys(searchItem);
        searchIcon.sendKeys(Keys.ENTER);
        return new GoogleCloudSearchResultsPage(driver);

    }

    private void closeCookieNotification () {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='glue-cookie-notification-bar']")));
        driver.findElement(By.xpath("//button[@class='glue-cookie-notification-bar__accept']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='glue-cookie-notification-bar']")));
    }

}


