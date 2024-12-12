package org.github.guzov.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.github.guzov.page.calculatorPage.GoogleCloudPricingCalculatorPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class GoogleCloudSearchResultsPage extends AbstractPage {

    private final Logger LOGGER = LogManager.getLogger();

    @FindBys(@FindBy(xpath = "//div[@class='gs-title']/a[@class='gs-title']"))
    private List<WebElement> searchResults;

    private final By SEARCH_RESULTS_BOX = By.xpath("//*[contains(@class, 'gsc-results gsc-webResult')]");
    private String searchItem;

    public GoogleCloudSearchResultsPage (WebDriver driver, String searchItem)
    {
        super(driver);
        this.searchItem = searchItem;
    }

    public GoogleCloudPricingCalculatorPage selectResult ()
    {
        WebElement necessaryItem = null;
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(SEARCH_RESULTS_BOX));
        for (WebElement searchResult: searchResults)
        {
            if (searchResult.getText().equals(searchItem))
            {
                necessaryItem = searchResult;
                break;
            }
        }
        if (necessaryItem == null)
        {
            throw new RuntimeException("No suitable result");
        } else necessaryItem.click();
        LOGGER.info("Result `{}` was found and clicked", searchItem);
        return new GoogleCloudPricingCalculatorPage(driver);
    }

}
