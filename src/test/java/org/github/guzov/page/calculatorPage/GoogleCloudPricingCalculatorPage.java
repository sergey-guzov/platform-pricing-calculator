package org.github.guzov.page.calculatorPage;

import org.github.guzov.page.AbstractPage;
import org.github.guzov.page.GoogleCloudCostEstimateSummaryPage;
import org.github.guzov.product.AbstractProduct;
import org.github.guzov.product.ComputeEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

public class GoogleCloudPricingCalculatorPage extends AbstractPage {
    private static final Logger logger = Logger.getLogger(GoogleCloudPricingCalculatorPage.class.getName());

    @FindBy(xpath = "//div[@class='Gxwdcd']//div[@class='VfPpkd-dgl2Hf-ppHlrf-sM5MNb']")
    WebElement addToEstimateButton;

    @FindBys(@FindBy(xpath = "//div[@class= 'DzHYNd' ]//div[@class= 'VobRQb' ]//h2"))
    List<WebElement> selectProductButtons;

    By productsPopUpLocator = By.xpath("//div[@class='uW2Fw-P5QLlc']");

    @FindBy(xpath = "//h1[@aria-label='Selected product title']")
    WebElement selectedProductTitle;

    @FindBy(xpath = "//*[@aria-label='Open detailed view']")
    protected WebElement openSummaryPageButton;

    @FindBy(xpath = "//div[@class = 'xJ0wqe']//*[contains(@class, 'Z7Qi9d')]")
    protected WebElement calculationIndicator;

    @FindBy(xpath = "//div[@class='VfPpkd-gIZMF']")
    protected WebElement calculationError;

    public GoogleCloudPricingCalculatorPage (WebDriver driver) {super(driver);}

    public ComputeEngineCalculatorPage selectProduct (AbstractProduct product)
    {
        addToEstimateButton.click();
        WebElement necessaryProductButton = null;
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                visibilityOfElementLocated(productsPopUpLocator));
        for (WebElement productButton: selectProductButtons)
        {
            if (productButton.getText().toLowerCase().equals(product.getProductName().toLowerCase()))
            {
                necessaryProductButton = productButton;
                break;
            }
        }
        if (necessaryProductButton == null)
        {
            throw new RuntimeException("Product is not found in the list of products");
        }
        necessaryProductButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                invisibilityOfElementLocated(productsPopUpLocator));
        return getCorrespondingCalculatorPage(product);
    }

    public GoogleCloudCostEstimateSummaryPage openSummaryPage ()
    {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(calculationIndicator));
        openSummaryPageButton.click();
        switchToNewTab();
        return new GoogleCloudCostEstimateSummaryPage(driver);
    }

    public void switchToNewTab ()
    {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> webDriver.getWindowHandles().size() > 1
        );
        for (String windowHandle : driver.getWindowHandles())
        {
            if (!windowHandle.equals(originalWindow))
            {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    protected void scrollPageTo (WebElement element)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(element));
    }

    protected void selectOptionFromDropBox (WebElement dropBox, String requiredOption)
    {
        String formattedValue = requiredOption.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        driver.findElement(By.xpath("//span[text()='" + requiredOption + "']/../..")).click();
    }

    private ComputeEngineCalculatorPage getCorrespondingCalculatorPage (AbstractProduct product)
    {
        switch (selectedProductTitle.getText())
        {
            case "Compute Engine":
            {
                ComputeEngine computeEngine = (ComputeEngine) product;
                return new ComputeEngineCalculatorPage(driver, computeEngine);
            }
            default:
            {
                throw new RuntimeException("Product is not selected");
            }
        }
    }

}