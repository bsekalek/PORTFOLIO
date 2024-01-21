package pages;

import driver.Driver;
import element.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ManagerPage {

    private final PageElement addCustomerButton;
    private final PageElement firstNameInput;
    private final PageElement lastNameInput;
    private final PageElement postCodeInput;
    private final PageElement addCustomerSubmitButton;
    private final PageElement homePageButton;
    private final PageElement openAccountButton;
    private final PageElement customersButton;
    private final PageElement deleteAccountButton;
    private final PageElement customerSelectButton;
    private final PageElement currencyButton;
    private final PageElement processButton;
    private final PageElement name;

    public ManagerPage() {
        addCustomerButton = new PageElement(By.xpath("//button[contains(text(), 'Add Customer') and not(@type)]"));
        firstNameInput = new PageElement(By.cssSelector("input[placeholder='First Name']"));
        lastNameInput = new PageElement(By.cssSelector("input[placeholder='Last Name']"));
        postCodeInput = new PageElement(By.cssSelector("input[placeholder='Post Code']"));
        addCustomerSubmitButton = new PageElement(By.xpath("//button[contains(text(), 'Add Customer') and @type='submit']"));
        homePageButton = new PageElement(By.xpath("//button[text() = 'Home']"));
        openAccountButton = new PageElement(By.xpath("//button[contains(text(), 'Open Account') and not(@type)]"));
        customersButton = new PageElement(By.xpath("//button[contains(text(), 'Customers') and not(@type)]"));
        deleteAccountButton = new PageElement(By.xpath("//button[contains(text(), 'Delete') and not(@type)]"));
        customerSelectButton = new PageElement(By.id("userSelect"));
        currencyButton = new PageElement(By.id("currency"));
        processButton = new PageElement(By.xpath("//button[contains(text(), 'Process') and @type='submit']"));
        name = new PageElement(By.cssSelector("input[placeholder='Search Customer']"));
    }

    public ManagerPage createNewCustomer(String firstname, String lastname, String postcode) throws InterruptedException {
        addCustomerButton.click();
        firstNameInput.typeText(firstname);
        lastNameInput.typeText(lastname);
        postCodeInput.typeText(postcode);
        addCustomerSubmitButton.click();
        Driver.getInstance().switchTo().alert().accept();
        Thread.sleep(1000);
        return this;
    }

    public ManagerPage searchCustomer(String firstname) {
        customersButton.click();
        name.typeText(firstname);
        return this;
    }

    public ManagerPage openAccount(String firstname, String lastname, String currency) throws InterruptedException {
        openAccountButton.click();
        Assert.assertTrue(customerSelectButton.isOptionAvailableInDropdown(firstname + " " + lastname));
        customerSelectButton.selectByText(firstname + " " + lastname);
        Assert.assertTrue(currencyButton.isOptionAvailableInDropdown(currency));
        currencyButton.selectByText(currency);
        processButton.click();
        Driver.getInstance().switchTo().alert().accept();
        return this;
    }

    public ManagerPage deleteAccount() {
        deleteAccountButton.click();
        return this;
    }

    public LoginPage moveToLoginPage() {
        homePageButton.click();
        return new LoginPage();
    }

//    private Select getCustomerDropdown() {
    //       return new Select(customerSelectButton);
    //   }

//    private Select getCurrencyDropdown() {
    //       return new Select(currencyButton);
    //   }
//
}
