package com.alex.framework.utils;

import com.alex.framework.managers.DriverManager;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MyAllureListener extends AllureJunit4 {

    @Override
    public void testFailure(final Failure failure) {
        byte[] screenshot = ((TakesScreenshot) DriverManager.getInstance()
                .getDriver()).getScreenshotAs(OutputType.BYTES);
        getLifecycle().addAttachment("Screenshot", "image/png", "png", screenshot);
        super.testFailure(failure);
    }

}
