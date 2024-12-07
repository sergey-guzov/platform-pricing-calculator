package org.github.guzov.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class GoogleCloudHomePage extends AbstractPage {

    private final String HOME_PAGE_URL = "https://cloud.google.com";
    private final Logger LOGGER = LogManager.getLogger();

    private String language = "/?hl=ru";

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchIcon;

    @FindBy(xpath = "//button[@class='glue-cookie-notification-bar__accept']")
    private WebElement cookiesAcceptButton;

    private By cookiesPolicyPopUp = By.xpath("//div[@class='glue-cookie-notification-bar']");

    public GoogleCloudHomePage (WebDriver driver) {super(driver);}

    public GoogleCloudHomePage openPage ()
    {
        driver.get(HOME_PAGE_URL+language);
        LOGGER.info("Homepage `{}` is opened", HOME_PAGE_URL);
        closeCookieNotification();
        return this;
    }

    public GoogleCloudSearchResultsPage searchFor (String searchItem)
    {
        LOGGER.info("Search element displayed:" + searchIcon.isDisplayed());
        LOGGER.info("Search element enabled:" + searchIcon.isEnabled());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIcon);
        searchIcon.sendKeys(searchItem);
        searchIcon.sendKeys(Keys.ENTER);
        return new GoogleCloudSearchResultsPage(driver, searchItem);
    }

    private void closeCookieNotification ()
    {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).
                    until(ExpectedConditions.presenceOfElementLocated(cookiesPolicyPopUp));
            cookiesAcceptButton.click();
            new WebDriverWait(driver, Duration.ofSeconds(10)).
                    until(ExpectedConditions.invisibilityOfElementLocated(cookiesPolicyPopUp));
        } catch (NoSuchElementException | TimeoutException elementException) {
            LOGGER.info("Cookies Notification is not shown");
        }
    }

}


