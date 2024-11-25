package org.github.guzov.page;

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
import java.util.logging.Logger;


public class GoogleCloudSearchResultsPage extends AbstractPage {

    private static final Logger logger = Logger.getLogger(GoogleCloudSearchResultsPage.class.getName());
    @FindBys(@FindBy(xpath = "//div[@class='gs-title']/a[@class='gs-title']"))
    private List<WebElement> searchResults;
    private final By SEARCH_RESULTS_BOX = By.xpath("//*[contains(@class, 'gsc-results gsc-webResult')]");


    public GoogleCloudSearchResultsPage (WebDriver driver) {super(driver);}

    public GoogleCloudPricingCalculatorPage selectResult (String searchItem) {
        WebElement necessaryItem = null;
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(SEARCH_RESULTS_BOX));
        for (WebElement searchResult: searchResults) {
            if (searchResult.getText().equals(searchItem)) {
                necessaryItem = searchResult;
                break;
            }
        }
        if (necessaryItem == null) {
            throw new RuntimeException("No suitable result");
        } else necessaryItem.click();
        return new GoogleCloudPricingCalculatorPage(driver);
    }

}
