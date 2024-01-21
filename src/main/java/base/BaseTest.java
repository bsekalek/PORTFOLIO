package base;

import config.Configuration;
import driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.time.Duration;

public abstract class BaseTest {

    @BeforeMethod
    public void setup() {
        Driver.getInstance().manage().window().maximize();
        Driver.getInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Driver.getInstance().get(Configuration.getUrl());
    }

    protected LoginPage getLoginPage() {
        return new LoginPage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeInstance();
    }

}
