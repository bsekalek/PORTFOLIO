package pages;

import driver.Driver;
import element.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AccountPage {

    private final PageElement welcomeMessage;
    private final PageElement depositCheck;
    private final PageElement withdrawlMoney;
    private final PageElement accountButton;
    private final PageElement homePageButton;
    private final PageElement depositButton;
    private final PageElement withdrawButton;
    private final PageElement transactionButton;
    private final PageElement resetButton;
    private final PageElement backButton;
    private final PageElement amountInput;
    private final PageElement depositAccept;
    private final PageElement withdrawAccept;
    private final PageElement latestTransactionAmount;
    private final PageElement latestTransactionType;
    private final PageElement depositMessage;

    public AccountPage() {
        welcomeMessage = new PageElement(By.xpath("((//div[@ui-view])[2]//strong)[1]"));
        depositCheck = new PageElement(By.xpath("//div[contains(text(), 'Account Number')]"));
        withdrawlMoney = new PageElement(By.xpath("//div[contains(text(), 'Account Number')]"));
        accountButton = new PageElement(By.id("accountSelect"));
        homePageButton = new PageElement(By.xpath("//button[text() = 'Home']"));
        depositButton = new PageElement(By.xpath("//button[contains(text(), 'Deposit') and not(@type)]"));
        withdrawButton = new PageElement(By.xpath("//button[contains(text(), 'Withdrawl') and not(@type)]"));
        transactionButton = new PageElement(By.xpath("//button[contains(text(), 'Transaction') and not(@type)]"));
        resetButton = new PageElement(By.xpath("//button[contains(text(), 'Reset') and not(@type)]"));
        backButton = new PageElement(By.xpath("//button[contains(text(), 'Back') and not(@type)]"));
        amountInput = new PageElement(By.cssSelector("input[placeholder='amount']"));
        depositAccept = new PageElement(By.xpath("//button[contains(text(), 'Deposit') and @type='submit']"));
        withdrawAccept = new PageElement(By.xpath("//button[contains(text(), 'Withdraw') and @type='submit']"));
        latestTransactionAmount = new PageElement(By.xpath("//table/tbody/tr[last()]/td[2]"));
        latestTransactionType = new PageElement(By.xpath("//table/tbody/tr[last()]/td[3]"));
        depositMessage = new PageElement(By.cssSelector("span[ng-show=message].error"));
    }

    public void validateProperWelcomeMessageDisplayed(String firstname, String lastname) {
        Assert.assertEquals(welcomeMessage.getText(), "Welcome %s %s !!".formatted(firstname, lastname), "Should be a proper welcome message");
    }

    public void validateBankAccount(String accountNumber) {
        //  Assert.assertTrue(getAccountDropdown().getOptions().stream().anyMatch(webElement -> webElement.getText().contains(accountNumber)));
        Assert.assertTrue(accountButton.isOptionAvailableInDropdown(accountNumber));
    }

    public AccountPage depositMoney(Integer amount) throws InterruptedException {
        depositButton.click();
        amountInput.typeText(String.valueOf(amount));
        depositAccept.click();
        Assert.assertEquals(depositMessage.getText(), "Deposit Successful", "Should have a proper notification");
        Thread.sleep(1000);
        return this;
    }

    public AccountPage validateBalanceAmount(Integer amount) {
        Assert.assertTrue(depositCheck.getText().contains("Balance : " + amount), "Should display account number 1010, Balane : 560");

        return this;
    }

    public AccountPage validateBalanceReset() {
        int resetAmount = 0;
        backButton.click();
        Assert.assertTrue(depositCheck.getText().contains("Balance : " + resetAmount), "Should display account number 1010 , Balance : 0");

        return this;
    }

    public AccountPage withdrawlMoney(Integer withdrawl) {
        withdrawButton.click();
        amountInput.typeText(String.valueOf(withdrawl));
        withdrawAccept.click();
        return this;
    }

    public LoginPage moveToLoginPage() {
        homePageButton.click();
        return new LoginPage();
    }


    public AccountPage validateWithdrawlMoney(Integer amount, Integer withdrawl) {
        Assert.assertTrue(withdrawlMoney.getText().contains("Account Number : 1010 , Balance : " + (amount - withdrawl)), "Transaction Failed. You can not withdraw amount more than the balance. In this case it is : " + (amount - withdrawl));
        return this;
    }

    public AccountPage validateWithdrawlMoney(Integer withdrawl) {
        Assert.assertFalse(withdrawlMoney.getText().contains("Withdrawl succed, account balance: " + withdrawl), "Transaction Failed. You can not withdraw amount more than the balance.");
        return this;
    }


    public AccountPage openTransactions() {
        transactionButton.click();
        return this;
    }

    public AccountPage clearTransactionHistory() {
        resetButton.click();
        return this;
    }

    public AccountPage validateLatestTransaction(Integer amount, String type) {
        Assert.assertEquals(latestTransactionAmount.getText(), amount.toString());
        Assert.assertEquals(latestTransactionType.getText(), type);
        return this;
    }
    //   private Select getAccountDropdown() {
    //      return new Select(accountButton);
    //  }
}
