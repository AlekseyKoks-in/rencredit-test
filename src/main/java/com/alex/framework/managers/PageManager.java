package com.alex.framework.managers;

import com.alex.framework.pages.MainPage;
import com.alex.framework.pages.CalculatorPage;

public class PageManager {

    private static PageManager pageManager = null;
    private MainPage mainPage;
    private CalculatorPage calculatorPage;

    private PageManager(){
    }

    public static PageManager getInstance() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }

    public CalculatorPage getCalculatorPage() {
        if (calculatorPage == null) {
            calculatorPage = new CalculatorPage();
        }
        return calculatorPage;
    }

}
