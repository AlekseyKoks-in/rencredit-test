package com.alex.tests;

import org.junit.Test;

public class RencreditScenariosTest extends BaseTest {

    @Test
    public void testFirst() {

        pageManager.getMainPage().selectChapter("Вклады")
                    .selectSubsection("Калькулятор доходности")
                    .selectCurrency("Рубли")
                    .fillFieldDepositAmount("300000")
                    .selectDepositTerm("6 месяцев")
                    .fillFieldMonthlyReplenishment("50000")
                    .clickCheckboxInCalculator("Ежемесячная капитализация")
                    .checkFilledFields(12781.28, 250000.00, 562781.28, "562 781,28");

    }

    @Test
    public void testSecond() {

        pageManager.getMainPage().selectChapter("Вклады")
                .selectSubsection("Калькулятор доходности")
                .selectCurrency("Доллары США")
                .fillFieldDepositAmount("500000")
                .selectDepositTerm("12 месяцев")
                .fillFieldMonthlyReplenishment("20000")
                .clickCheckboxInCalculator("Ежемесячная капитализация")
                .checkFilledFields(920.60, 220000.00, 720920.60, "720 920,60");

    }

}
