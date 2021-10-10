package com.alex.tests;

import com.alex.framework.managers.DriverManager;
import com.alex.framework.managers.InitManager;
import com.alex.framework.managers.PageManager;
import com.alex.framework.managers.TestPropertiesManager;
import com.alex.framework.utils.PropConst;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseTest {

    private final DriverManager driverManager = DriverManager.getInstance();
    private final TestPropertiesManager testPropertiesManager = TestPropertiesManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();


    @BeforeClass
    public static void beforeClass() {
        InitManager.initFramework();
    }

    @Before
    public void before() {
        driverManager.getDriver().get(testPropertiesManager.getProperty(PropConst.BASE_URL));
    }

    @AfterClass
    public static void afterClass() {
        InitManager.quitFramework();
    }

}