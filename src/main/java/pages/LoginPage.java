package pages;

import driver.Driver;
import element.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class LoginPage {

    private final PageElement bankManagerLoginButton;
    private final PageElement customerLoginButton;
    private final PageElement userSelect;
    private final PageElement loginButton;

    public LoginPage() {
        bankManagerLoginButton = new PageElement(By.xpath("//button[text() = 'Bank Manager Login']"));
        customerLoginButton = new PageElement(By.xpath("//button[text() = 'Customer Login']"));
        userSelect = new PageElement(By.id("userSelect"));
        loginButton = new PageElement(By.cssSelector("button[type=submit]"));
    }

    public ManagerPage loginAsBankManager() {
        bankManagerLoginButton.click();
        return new ManagerPage();
    }

    public AccountPage loginAsCustomer(String firstname, String lastname) {
        customerLoginButton.click();
        Assert.assertTrue(userSelect.isOptionAvailableInDropdown(firstname + " " + lastname));
        userSelect.selectByText(firstname + " " + lastname);
        loginButton.click();
        return new AccountPage();
    }

    public AccountPage validateDeletedCustomer(String firstname, String lastname) {
        customerLoginButton.click();
        Assert.assertFalse(userSelect.isOptionAvailableInDropdown(firstname + " " + lastname));
        return new AccountPage();
    }
}
