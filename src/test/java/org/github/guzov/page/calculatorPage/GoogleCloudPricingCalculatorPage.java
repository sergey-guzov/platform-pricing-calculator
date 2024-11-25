package org.github.guzov.page.calculatorPage;

import org.github.guzov.page.AbstractPage;
import org.github.guzov.page.calculatorPage.specifiedPages.ComputeEngineCalculatorPage;
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

public class GoogleCloudPricingCalculatorPage extends AbstractPage {
    public GoogleCloudPricingCalculatorPage (WebDriver driver) {super(driver);}
    private static final Logger logger = Logger.getLogger(GoogleCloudPricingCalculatorPage.class.getName());


    @FindBy(xpath = "//div[@class='Gxwdcd']//div[@class='VfPpkd-dgl2Hf-ppHlrf-sM5MNb']")
    WebElement addToEstimateButton;
    @FindBys(@FindBy(xpath = "//div[@class= 'DzHYNd' ]//div[@class= 'VobRQb' ]//h2"))
    List<WebElement> selectProductButtons;
    By productsPopUpLocator = By.xpath("//div[@class='uW2Fw-P5QLlc']");
    @FindBy(xpath = "//h1[@aria-label='Selected product title']")
    WebElement selectedProductTitle;

    public ComputeEngineCalculatorPage selectProduct (String productName) {
        addToEstimateButton.click();
        WebElement necessaryProductButton = null;
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                visibilityOfElementLocated(productsPopUpLocator));
        for (WebElement productButton: selectProductButtons) {
            if (productButton.getText().toLowerCase().equals(productName.toLowerCase())) {
                necessaryProductButton = productButton;
                break;
            }
        }
        necessaryProductButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                invisibilityOfElementLocated(productsPopUpLocator));
        return selectProductCalculatorPage();
    }

    private ComputeEngineCalculatorPage selectProductCalculatorPage() {
        switch (selectedProductTitle.getText()) {
            case "Compute Engine": {
                return new ComputeEngineCalculatorPage(driver);
            }
            default: {
                throw new RuntimeException("Product is not selected");
            }
        }
    }

}
