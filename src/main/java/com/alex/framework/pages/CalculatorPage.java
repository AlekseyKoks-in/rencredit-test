package com.alex.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CalculatorPage extends BasePage{

    @FindBy(xpath = "//label[contains(@class, 'calculator__currency-field')]")
    private List<WebElement> listCurrency;
    @FindBy(xpath = "//input[@name='amount']")
    private WebElement depositAmountField;
    @FindBy(xpath = "//select")
    private WebElement depositTerm;
    @FindBy(xpath = "//input[@name = 'replenish']")
    private WebElement monthlyReplenishmentField;
    @FindBy(xpath = "//div[@class = 'calculator__content']//div[contains(@class, 'calculator__check-row-field')]")
    private List<WebElement> listCheckBoxInCalculator;
    @FindBy(xpath = "//span[@class='js-calc-earned']")
    private WebElement accruedInterestElement;
    @FindBy(xpath = "//span[@class='js-calc-replenish']")
    private WebElement replenishmentElement;
    @FindBy(xpath = "//span[@class='js-calc-result']")
    private WebElement toWithdrawElement;


    @Step("Выбрать – '{nameCurrency}'")
    public CalculatorPage selectCurrency(String nameCurrency) {
        return (CalculatorPage) clickElement(nameCurrency, listCurrency, pageManager.getCalculatorPage(),
                "Валюта '" + nameCurrency + "' не была найден");
    }

    @Step("Сумма вклада – '{depositAmount}'")
    public CalculatorPage fillFieldDepositAmount(String depositAmount) {
        fillInputField(depositAmountField, depositAmount);
        return this;
    }

    @Step("Срок – '{term}'")
    public CalculatorPage selectDepositTerm(String term) {
        Select select = new Select(depositTerm);
        select.selectByVisibleText(term);
        return this;
    }

    @Step("Ежемесячное пополнение – '{replenishmentAmount}'")
    public CalculatorPage fillFieldMonthlyReplenishment(String replenishmentAmount){
        fillInputField(monthlyReplenishmentField, replenishmentAmount);
        return this;
    }

    @Step("Отметить – '{nameCheckbox}'")
    public CalculatorPage clickCheckboxInCalculator(String nameCheckbox) {
        for (WebElement checkBoxInCalculator : listCheckBoxInCalculator) {
            if (checkBoxInCalculator.getText().equals(nameCheckbox)) {
                if (checkBoxInCalculator.findElement(By.xpath(".//input")).getAttribute("checked") == null) {
                    waitUtilElementToBeClickable(checkBoxInCalculator).click();
                    return this;
                }
            }
        }
        Assert.fail("Чекбокс '" + nameCheckbox + "' отсутствует");
        return this;
    }

    @Step("Проверить расчеты по вкладу")
    public CalculatorPage checkFilledFields(double expectedInterest, double expectedReplenishment, double expectedToWithdraw, String totalAmount) {
        wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='js-calc-result']"), totalAmount));

        Assert.assertEquals("Начисленный % не соответствует ожидаемому",
                expectedInterest, transformationString(accruedInterestElement.getText()), 0.0);
        Assert.assertEquals("Пополнение не соответствует ожидаемому",
                expectedReplenishment, transformationString(replenishmentElement.getText()), 0.0);
        Assert.assertEquals("Сумма к снятию не соответствует ожидаемой",
                expectedToWithdraw, transformationString(toWithdrawElement.getText()), 0.0);

        return this;
    }

}
