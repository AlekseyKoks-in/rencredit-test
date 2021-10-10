package com.alex.framework.pages;

import com.alex.framework.managers.DriverManager;
import com.alex.framework.managers.PageManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected final DriverManager driverManager = DriverManager.getInstance();
    protected Actions action = new Actions(driverManager.getDriver());
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);
    protected PageManager pageManager = PageManager.getInstance();


    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void fillInputField(WebElement element, String value) {
        waitUtilElementToBeClickable(element);
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    public double transformationString(String string) {
        return Double.parseDouble(string.replaceAll("[^,\\d]", "").replace(",", "."));
    }

    public BasePage clickElement(Object name, List<WebElement> listWebElement, BasePage page, String errorMessage) {
        String elementText;
        for (WebElement element : listWebElement) {
            if (element.getText().contains(String.valueOf(name))) {
                waitUtilElementToBeClickable(waitUtilElementToBeVisible(element)).click();
                return page;
            }
        }
        Assert.fail(errorMessage);
        return page;
    }

}
