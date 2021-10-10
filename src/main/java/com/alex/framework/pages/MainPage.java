package com.alex.framework.pages;

import com.alex.framework.managers.PageManager;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//li[contains(@class, 'nav__item nav__item_parent')]")
    private List<WebElement> listChapters;
    @FindBy(xpath = "//div[contains(@class, 'nav__item')]//li[contains(@class, 'nav__item')]")
    private List<WebElement> listSubsection;


    @Step("Перейти в меню – '{nameChapter}'")
    public MainPage selectChapter(String nameChapter) {
        return (MainPage) clickElement(nameChapter, listChapters, pageManager.getMainPage(),
                "Раздел с названием '" + nameChapter + "' не был найден");
    }

    @Step("Выбрать подменю – '{nameSubsection}'")
    public CalculatorPage selectSubsection(String nameSubsection) {
        return (CalculatorPage) clickElement(nameSubsection, listSubsection, pageManager.getCalculatorPage(),
                "Подраздел с названием '" + nameSubsection + "' не был найден");
    }

}
