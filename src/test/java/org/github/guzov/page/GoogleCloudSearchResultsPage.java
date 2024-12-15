package org.github.guzov.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.github.guzov.page.calculatorPage.GoogleCloudPricingCalculatorPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class GoogleCloudSearchResultsPage extends AbstractPage {

    private final Logger LOGGER = LogManager.getLogger();

    @FindBy(xpath = "//div/a[@href='https://cloud.google.com/products/calculator']")
    private WebElement calculatorLink;

    private String searchItem;

    public GoogleCloudSearchResultsPage (WebDriver driver, String searchItem)
    {
        super(driver);
        this.searchItem = searchItem;
    }

    public GoogleCloudPricingCalculatorPage selectResult ()
    {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(calculatorLink));
        calculatorLink.click();

        LOGGER.info("Result `{}` was found and clicked", searchItem);
        return new GoogleCloudPricingCalculatorPage(driver);
    }

}
