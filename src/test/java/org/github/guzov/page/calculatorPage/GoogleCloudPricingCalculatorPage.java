package org.github.guzov.page.calculatorPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.github.guzov.page.AbstractPage;
import org.github.guzov.page.GoogleCloudCostEstimateSummaryPage;
import org.github.guzov.product.AbstractProduct;
import org.github.guzov.product.ComputeEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GoogleCloudPricingCalculatorPage extends AbstractPage {
    private final Logger LOGGER = LogManager.getLogger();

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
        try
        {
            actions.moveToElement(element).perform();
        }
        catch (MoveTargetOutOfBoundsException e)
        {
            LOGGER.info(e);
            js.executeScript(
                    "const element = arguments[0];" +
                            "const rect = element.getBoundingClientRect();" +
                            "window.scrollBy({ top: rect.top + window.scrollY - (window.innerHeight / 2), behavior: 'smooth' });",
                    element
            );
        }
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(element));
    }

    protected void selectOptionFromDropBox (WebElement dropBox, String requiredOption)
    {
        String formattedValue = requiredOption.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        driver.findElement(By.xpath("//span[text()='" + requiredOption + "']/../..")).click();
    }

    protected void activateRadioButton (WebElement radioButtonArea, String requiredOption)
    {
        scrollPageTo(radioButtonArea);
        driver.findElement(By.xpath("//label[text()='"+ requiredOption + "']/..")).click();
    }

    private ComputeEngineCalculatorPage getCorrespondingCalculatorPage (AbstractProduct product)
    {
        switch (selectedProductTitle.getText())
        {
            case "Compute Engine":
            {
                ComputeEngine computeEngine = (ComputeEngine) product;
                LOGGER.info("Product `{}` was selected to estimate", product.getProductName());
                return new ComputeEngineCalculatorPage(driver, computeEngine);
            }
            default:
            {
                throw new RuntimeException("Product is not selected");
            }
        }
    }

}
